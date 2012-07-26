/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import java.util.*;
import java.io.*;
import entities.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import mail.*;
import sql.*;

/**
 *
 * @author Alex Hughes
 */
public class Scheduler implements Serializable, Runnable {

    private ArrayList<MyMessage> messages;
    private ArrayList<Email> addresses;
    private ArrayList<SMTPServer> servers;
    private Connector con;
    private Mail mailer;
    private DBStruct dbs;

    /**
     * Construct the messages and prepares them before sending.
     *
     * @param aContentFile
     * @param aCon
     * @param aDbs
     * @throws SQLException
     * @throws AddressException
     * @throws IOException
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public Scheduler(File aContentFile, Connector aCon, DBStruct aDbs)
            throws SQLException, AddressException, IOException {
        dbs = aDbs;
        con = aCon;

        //Fething email addresses
        addresses = DBParsers.fetchAddresses(con, dbs);

        //Fetching smtp servers
        servers = DBParsers.fetchServers(con, dbs);

        //Creating email messages
        messages = new ArrayList<MyMessage>();
        for (int i = 0; i < addresses.size(); i++) {
            messages.add(new MyMessage(addresses.get(i).getEmail(), dbs.getEmailPref().getSubject(),
                    addresses.get(i).getUid()));
        }

        //Setting subject and content
        String content = EmailParser.parseContent(aContentFile);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).setContent(content.replace("#aUUID",
                    messages.get(i).getUUID()));
        }
    }

    @Override
    public void run() {
        boolean finish = false;
        int i, j;

        i = 3;
        j = 0;
        while (i < servers.size() && !finish) {

            if (servers.get(i).getType().equals("Gmail")) {
                mailer = new GMail();
            } else if (servers.get(i).getType().equals("SimpleMail")) {
                mailer = new SimpleMail();
            } else if (servers.get(i).getType().equals("UnauthMail")) {
                mailer = new UnauthMail();
            }

            while (j < messages.size() && !finish) {
                try {
                    mailer.sendMail(messages.get(j), servers.get(i), dbs);
                    if (DBParsers.sendNewsUpdate(con, dbs, messages.get(j).getUUID()) == 1) {
                        finish = true;
                    }
                    j++;
                    if (messages.size() == j) {
                        finish = true;
                    }
                } catch (AddressException ex) {
                    System.out.println("Address: " + messages.get(i).getAddress().getAddress() + " is invalid ");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                    j++;
                } catch (MessagingException ex) {
                    System.out.println("Probably there is sth wrong with current server: "
                            + servers.get(i).getHost() + ". Switching server...");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("Wrong Encoding");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                    j++;
                }
            }
        }
    }

    public Connector getCon() {
        return con;
    }

    public ArrayList<MyMessage> getMessages() {
        return messages;
    }

    public ArrayList<SMTPServer> getServers() {
        return servers;
    }

    public void setServers(ArrayList<SMTPServer> servers) {
        this.servers = servers;
    }
}
