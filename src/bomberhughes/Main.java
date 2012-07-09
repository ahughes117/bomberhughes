/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import entities.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
            MyMessage mm = new MyMessage();
            mm.setAddress("alexhughes117@gmail.com");
            mm.setSubject("test");
            mm.setUUID("117");
            mm.setContent(EmailParser.parseContent(new File("message.html")));
            
            new GMail().sendMail(mm, new Credentials("base117.tester@gmail.com", 
                    "base117.tester@gmail.com", "AineGifi117"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AddressException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
