/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Alex Hughes
 */
public class MailCred {
    
    protected InternetAddress fromAddress;
    protected String username;
    protected String password;
    
    /**
     * Creates the Internet Address from a String
     * @param aFromAddress
     * @param aUsername
     * @param aPassword
     * @throws AddressException 
     */
    public MailCred(String aFromAddress, String aUsername, 
            String aPassword) throws AddressException{
        fromAddress = new InternetAddress(aFromAddress);
        username = aUsername;
        password = aPassword;
    }
    
    /**
     * Empty Constructor
     */
    public MailCred(){
        
    }

    public InternetAddress getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(InternetAddress fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
