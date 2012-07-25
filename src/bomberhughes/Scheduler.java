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
    private Mail mail;

    /**
     * Construct the messages and prepares them before sending.
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
        //Fething email addresses
        addresses = DBParsers.fetchAddresses(aCon, aDbs);
        
        //Fetching servers
        servers = DBParsers.fetchServers(aCon, aDbs);
        for(int i=0; i<servers.size(); i++){
            System.out.println(servers.get(i).getHost());
        }

        //Creating email messages
        messages = new ArrayList<MyMessage>();
        for (int i = 0; i < addresses.size(); i++) {
            messages.add(new MyMessage(addresses.get(i).getEmail(), aDbs.getEmailPref().getSubject(),
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Connector getCon() {
        return con;
    }

    public ArrayList<MyMessage> getMessages() {
        return messages;
    }
}
