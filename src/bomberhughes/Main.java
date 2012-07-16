/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import GUI.*;
import entities.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import mail.*;
import sql.*;


/**
 * Bulk Mail Utility.
 *
 * @author Alex Hughes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connector c = new Connector(new DBCredentials("10.0.0.117", "ahughes", "AineGifi117", "h4dMailDummy"));
            new Scheduler("Test στα ελληνικά", new File("message.html"), c);
            //new LoginFrame();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException x){
            
        } /*catch (UnsupportedEncodingException x){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, x);
        }*/
            
    }
}
