/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import entities.Credentials;
import java.io.Serializable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Alex Hughes
 */
public class MailCred extends Credentials implements Serializable{

    protected String fromAddress;
    protected String username;
    protected String password;

    /**
     * Creates the Internet Address from a String
     *
     * @param aFromAddress
     * @param aUsername
     * @param aPassword
     * @throws AddressException
     */
    public MailCred(String aFromAddress, String aUsername, String aPassword) {
        fromAddress = aFromAddress;
        username = aUsername;
        password = aPassword;
    }

    /**
     * Empty Constructor
     */
    public MailCred() {
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
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
