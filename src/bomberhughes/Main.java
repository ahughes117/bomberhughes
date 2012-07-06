/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import mail.*;

/**
 * Bulk Mail Utility.
 * @author Alex Hughes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String[] recipients = {"alexhughes117@gmail.com"};
            Mail.setCredentials("base117.tester@gmail.com", 
                    "base117.tester@gmail.com", "AineGifi117");
            new GMail().sendMail(recipients, "test", "This is a simple test message");
        } catch (AddressException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
