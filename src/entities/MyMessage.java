/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This is the message object, it is exactly what will be sent by the mail
 * package classes.
 *
 * @author Alex Hughes
 */
public class MyMessage {

    private InternetAddress address;
    private String subject;
    private String content;
    private String UUID;

    /**
     * Full Constructor
     * @param anAddress
     * @param aSubject
     * @param aContent
     * @param aUUID 
     */
    public MyMessage(String anAddress, String aSubject, String aContent, 
            String aUUID) throws AddressException {
        address = new InternetAddress(anAddress);
        subject = aSubject;
        content = aContent;
        UUID = aUUID;
    }

    /**
     * Empty Constructor
     */
    public MyMessage() {
        
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public InternetAddress getAddress() {
        return address;
    }

    public void setAddress(String address) throws AddressException {
        this.address = new InternetAddress(address);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
