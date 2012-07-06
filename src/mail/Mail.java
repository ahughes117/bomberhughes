package mail;

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
    protected static InternetAddress fromAddress;
    protected static String username;
    protected static String password;

    public void sendMail(String recipients[], String subject,
            String message) throws AddressException, MessagingException {

        //creating a session
        session = Session.getInstance(props, new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        //creating the message
        msg = new MimeMessage(session);

        //set the from address
        if (fromAddress == null) {
            throw new AddressException("fromAddress was not set succesfully");
        } else {
            msg.setFrom(fromAddress);
        }

        //set the bcc addresses
        InternetAddress[] toAddresses = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            toAddresses[i] = new InternetAddress(recipients[i]);
        }
        msg.setRecipients(Message.RecipientType.BCC, toAddresses);

        //set the subject, content and encoding
        msg.setSubject(subject);
        msg.setContent(message, "text/html; charset=utf-8");

        //finally sending the message
        Transport.send(msg);
    }

    public static void setCredentials(String aFromAddress, String aUsername,
            String aPassword) throws AddressException {
        fromAddress = new InternetAddress(aFromAddress);
        username = aUsername;
        password = aPassword;
    }
}
