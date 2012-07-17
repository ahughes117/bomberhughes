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
 * A generic smtp mail class, to be used with unsecured smtp servers. (rare)
 * @author Alex Hughes
 */
public class UnauthMail extends Mail {
    
    @Override
    public void sendMail(MyMessage aMessage, SMTPServer aServer, DBStruct aDbs)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        
        props = new Properties();
        props.put("mail.smtp.host", aServer.getHost());
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.port", "25");
        
        super.sendMail(aMessage, aServer, aDbs);
    }
}
