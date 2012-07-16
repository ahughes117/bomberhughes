/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import entities.*;
import java.util.ArrayList;
import java.sql.*;
import mail.MailCred;

/**
 * Class responsible for parsing email addresses and UUIDs...
 *
 * @author Alex Hughes
 */
public class DBParsers {

    public static ArrayList<Email> fetchAddresses(Connector aCon)
            throws SQLException {

        ArrayList<Email> emails = new ArrayList<Email>();
        ResultSet addressesR = aCon.sendQuery(""
                + "SELECT " + DBStruct.addressF + ", " + DBStruct.uidF
                + " FROM " + DBStruct.emailT
                + " WHERE " + DBStruct.unsubscribedF + " = 0 AND "
                + DBStruct.addressF + " LIKE '%base117%' " //hard-coded my email address for testing
                + " LIMIT 100000 ");

        while (addressesR.next()) {
            emails.add(new Email(addressesR.getString(1), addressesR.getString(2)));
        }
        return emails;
    }

    public static ArrayList<MailCred> fetchServers(Connector aCon,
            String aFromAddress, String aFromName) throws SQLException {

        ArrayList<MailCred> servers = new ArrayList<MailCred>();
        ResultSet serversR = aCon.sendQuery(""
                + "SELECT * "
                + " FROM " + DBStruct.serverT
                + " LIMIT 100000 ");

        while (serversR.next()) {
            servers.add(new MailCred(aFromAddress, serversR.getString(DBStruct.userF),
                    serversR.getString(DBStruct.passF), serversR.getString(DBStruct.hostF),
                    serversR.getString(DBStruct.typeF), aFromName));
        }
        return servers;
    }
}
