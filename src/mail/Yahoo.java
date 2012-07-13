/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import entities.MyMessage;

/**
 * The properties for sending messages via Yahoo addresses.
 *
 * @author Alex Hughes
 */
public class Yahoo extends Mail {

    @Override
    public void sendMail(MyMessage aMessage, MailCred aCredentials)
            throws AddressException, MessagingException {
        
        props = new Properties();
        
        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.auth", "true");
        
        super.sendMail(aMessage, aCredentials);
    }
}
