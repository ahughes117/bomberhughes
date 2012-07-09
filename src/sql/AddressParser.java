/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import entities.Email;
import java.util.ArrayList;
import java.sql.*;

/**
 * Class responsible for parsing email addresses and UUIDs...
 * @author Alex Hughes
 */
public class AddressParser {
    
    public static ArrayList<Email> fetchAddresses (Connector aCon) 
            throws SQLException {
        ArrayList<Email> emails = new ArrayList<Email>();
        ResultSet addressesR =  aCon.sendQuery(""
                + "SELECT EmailAddress, UUID "
                + "FROM Email "
                + "WHERE Unsubscribed = 0 AND EmailAddress = 'alexhughes117@gmail.com'" //hard-coded my email address for testing
                + "LIMIT 100000 ");
        
        while(addressesR.next()){
            emails.add(new Email(addressesR.getString(1), addressesR.getString(2)));
        }
        return emails;
    }
    
}
