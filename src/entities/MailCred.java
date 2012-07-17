/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Credentials;
import java.io.Serializable;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Alex Hughes
 */
public class MailCred extends Credentials implements Serializable {

    protected String smtpHost;
    protected String fromAddress;
    protected String fromName;
    protected String username;
    protected String password;
    protected String type;

    /**
     * Constructor for authenticated SMTP servers.
     * Creates the Internet Address from a String
     * 
     * @param aFromAddress
     * @param aUsername
     * @param aPassword
     * @param aSmtpHost 
     */
    public MailCred(String aFromAddress, String aUsername, String aPassword, 
            String aSmtpHost, String aType, String aFromName) {
        fromAddress = aFromAddress;
        fromName = aFromName;
        username = aUsername;
        password = aPassword;
        smtpHost = aSmtpHost;
        type = aType;
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

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}