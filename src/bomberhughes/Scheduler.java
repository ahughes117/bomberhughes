/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import java.util.*;
import java.io.*;
import entities.*;
import java.sql.*;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import mail.*;
import sql.*;

/**
 *
 * @author Alex Hughes
 */
public class Scheduler implements Serializable {

    private ArrayList<MyMessage> messages;
    private ArrayList<Email> addresses;
    private ArrayList<SMTPServer> servers;
    private Connector con;
    private DBStruct dbs;
    private MailWorker mw;

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

    public void run(UpdateComs aUc) {
        mw = new MailWorker(this, aUc);
        mw.execute();
    }

    public void cancel() {
        mw.cancel(true);
        mw.done();
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

    public DBStruct getDbs() {
        return dbs;
    }

    public void setDbs(DBStruct dbs) {
        this.dbs = dbs;
    }
}
