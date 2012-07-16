/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import entities.MyMessage;
import java.io.UnsupportedEncodingException;

/**
 * A class for sending mail using a simple smtp server without encryption
 * but authentication.
 * @author Alex Hughes
 */
public class SimpleMail extends Mail {
    
    @Override
    public void sendMail(MyMessage aMessage, MailCred aCredentials)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        
        props = new Properties();
        
        props.put("mail.smtp.host", aCredentials.getSmtpHost());
        props.put("mail.smtp.auth", "true");
        
        super.sendMail(aMessage, aCredentials);
    }
}
