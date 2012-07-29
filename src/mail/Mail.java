package mail;

import entities.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
    protected static SMTPServer server;
    protected MyMessage message;

    public void sendMail(MyMessage aMessage, SMTPServer aServer, DBStruct aDbs)
            throws AddressException, MessagingException, UnsupportedEncodingException {
        message = aMessage;
        server = aServer;

        //creating a session, if username and password exists, we have to 
        //use an authenticator.
        if (server.getUser() != null) {
            session = Session.getInstance(props, new Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(server.getUser(), server.getPass());
                }
            });
        } else {
            session = Session.getInstance(props);
        }
        
        //creating the message
        msg = new MimeMessage(session);

        //set the from address
        InternetAddress ia = new InternetAddress(aDbs.getEmailPref().getFromAddress());
        ia.setPersonal(aDbs.getEmailPref().getFromName(), "utf-8");
        msg.setFrom(ia);

        //setting the to address and inserting the uuid
        msg.setRecipient(Message.RecipientType.TO, message.getAddress());
        message.setContent(message.getContent());

        //set the subject, content and encoding
        msg.setSubject(aMessage.getSubject());
        msg.setContent(aMessage.getContent(), "text/html; charset=utf-8");

        //finally sending the message
        Transport.send(msg);
        System.out.println("Message Sent - " + server.getHost() + " || " + message.getAddress().getAddress());
    }
}
