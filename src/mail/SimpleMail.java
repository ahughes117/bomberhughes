/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import entities.*;
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
    public void sendMail(MyMessage aMessage, SMTPServer aServer, DBStruct aDbs)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        
        props = new Properties();
        
        props.put("mail.smtp.host", aServer.getHost());
        props.put("mail.smtp.auth", "true");
        
        super.sendMail(aMessage, aServer, aDbs);
    }
}
