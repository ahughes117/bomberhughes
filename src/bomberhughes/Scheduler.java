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
import mail.*;
import sql.*;

/**
 *
 * @author Alex Hughes
 */
public class Scheduler implements Serializable {

    private ArrayList<MyMessage> messages;
    private ArrayList<Email> addresses;
    private Connector con;
    private Mail mail;

    public Scheduler(String aSubject, File aContentFile, Connector aCon)
            throws SQLException, AddressException, IOException, MessagingException {
        //Fething email addresses
        addresses = AddressParser.fetchAddresses(aCon);

        //Creating email messages
        messages = new ArrayList<MyMessage>();
        for (int i = 0; i < addresses.size(); i++) {
            messages.add(new MyMessage(addresses.get(i).getEmail(), "", "",
                    addresses.get(i).getUid()));
        }

        //Setting subject and content
        String content = EmailParser.parseContent(aContentFile);
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).setSubject(aSubject);
            messages.get(i).setContent(content.replace("#aUUID",
                    messages.get(i).getUUID()));
        }

        //Sending the messages. Replace with a quota algorithm
        for (int i = 0; i < messages.size(); i++) {
            new GMail().sendMail(messages.get(i), new mail.MailCred("base117.tester@gmail.com",
                    "base117.tester@gmail.com", "AineGifi117"));
        }

    }
}
