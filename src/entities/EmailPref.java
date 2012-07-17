/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Alex Hughes
 */
public class EmailPref implements Serializable {
    
    private String fromAddress;
    private String fromName;

    public EmailPref(String fromAddress, String fromName) {
        this.fromAddress = fromAddress;
        this.fromName = fromName;
    }

    public EmailPref() {
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
