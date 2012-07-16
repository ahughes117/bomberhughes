package mail;

import entities.MyMessage;
import java.io.IOException;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;

/**
 * The generic abstract mail class. Might make development in the future easier.
 *
 * @author Alex Hughes
 */
public abstract class Mail {

    protected static Properties props;
    protected static Session session;
    protected static Message msg;
    //
    protected static MailCred cre;
    protected MyMessage message;

    public void sendMail(MyMessage aMessage, MailCred aCredentials)
            throws AddressException, MessagingException {
        message = aMessage;
        cre = aCredentials;

        //creating a session, if username and password exists, we have to 
        //use an authenticator.
        if (cre.username != null) {
            session = Session.getInstance(props, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(cre.username, cre.password);
                }
            });
        } else {
            session = Session.getInstance(props);
        }
        
        //creating the message
        msg = new MimeMessage(session);

        //set the from address
        msg.setFrom(new InternetAddress(cre.fromAddress));

        //setting the to address and inserting the uuid
        msg.setRecipient(Message.RecipientType.TO, message.getAddress());
        message.setContent(message.getContent());

        //set the subject, content and encoding
        msg.setSubject(aMessage.getSubject());
        msg.setContent(aMessage.getContent(), "text/html; charset=utf-8");

        //finally sending the message
        Transport.send(msg);
        System.out.println("Message Sent");
    }
}
