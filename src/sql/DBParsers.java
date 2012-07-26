/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;

import entities.*;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                + " WHERE " + aDbs.getUnsubscribedF() + " = 0 " + aDbs.getEmailPref().getWhereParam()
                + " LIMIT " + aDbs.getEmailPref().getLimitParam());

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
            servers.add(new SMTPServer(serversR.getString(aDbs.getHostF()),
                    serversR.getString(aDbs.getUserF()), serversR.getString(aDbs.getPassF()),
                    serversR.getString(aDbs.getTypeF())));
        }
        return servers;
    }

    public static int sendNewsUpdate(Connector aCon, DBStruct aDbs, String aUUID) {
        int success = 0;
        int news = -1;
        try {
            ResultSet newsR = aCon.sendQuery(
                    "SELECT " + aDbs.getNewsF()
                    + " FROM " + aDbs.getEmailT()
                    + " WHERE " + aDbs.getUidF() + " = '" + aUUID + "' ");

            while (newsR.next()) {
                news = newsR.getInt(1) + 1;
            }
            if (news != -1) {
                aCon.sendUpdate("UPDATE " + aDbs.getEmailT() + " SET " + aDbs.getNewsF()
                        + " = " + Integer.toString(news)
                        + " WHERE " + aDbs.getUidF() + " = '" + aUUID + "' ");
                
                if(!Connector.AUTOCOMMIT){
                    aCon.commit();
                }
            }
        } catch (SQLException ex) {
            success = 1;
            Logger.getLogger(DBParsers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return success;
    }
}
