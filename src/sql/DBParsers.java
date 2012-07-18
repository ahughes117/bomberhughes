/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import entities.*;
import java.util.ArrayList;
import java.sql.*;

/**
 * Class responsible for parsing email addresses and UUIDs...
 *
 * @author Alex Hughes
 */
public class DBParsers {

    public static ArrayList<Email> fetchAddresses(Connector aCon, DBStruct aDbs)
            throws SQLException {

        ArrayList<Email> emails = new ArrayList<Email>();
        ResultSet addressesR = aCon.sendQuery(""
                + "SELECT " + aDbs.getAddressF() + ", " + aDbs.getUidF()
                + " FROM " + aDbs.getEmailT()
                + " WHERE " + aDbs.getUnsubscribedF() + " = 0 " 
                + " LIMIT 100000 ");

        while (addressesR.next()) {
            emails.add(new Email(addressesR.getString(1), addressesR.getString(2)));
        }
        return emails;
    }

    public static ArrayList<SMTPServer> fetchServers(Connector aCon, DBStruct aDbs) 
            throws SQLException {

        ArrayList<SMTPServer> servers = new ArrayList<SMTPServer>();
        ResultSet serversR = aCon.sendQuery(""
                + "SELECT * "
                + " FROM " + aDbs.getServerT()
                + " LIMIT 100000 ");

        while (serversR.next()) {
            servers.add(new SMTPServer(serversR.getString(aDbs.getUserF()),
                    serversR.getString(aDbs.getPassF()), serversR.getString(aDbs.getHostF()),
                    serversR.getString(aDbs.getTypeF())));
        }
        return servers;
    }
}
