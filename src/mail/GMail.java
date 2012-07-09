package mail;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import entities.MyMessage;

/**
 * This class is specialized into sending mail using instructions given by
 * GMail.
 *
 * @author Alex Hughes
 */
public class GMail extends Mail {
   
    @Override
    public void sendMail(MyMessage aMessage, MailCred aCredentials) 
            throws AddressException, MessagingException {
        
        props = new Properties();
        //Sending message using SSL. For an unknown reason TLS failed miserably.
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        super.sendMail(aMessage, aCredentials);
    }
}
