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
public class UserPref implements Serializable {
    
    private String fromAddress;
    private String fromName;
    private String subject;
    private String whereParam;
    private String limitParam;
    private String fileAddress;

    
    public UserPref() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFileAddress() {
        return fileAddress;
    }

    public void setFileAddress(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public String getLimitParam() {
        return limitParam;
    }

    public void setLimitParam(String limitParam) {
        this.limitParam = limitParam;
    }

    public String getWhereParam() {
        return whereParam;
    }

    public void setWhereParam(String whereParam) {
        this.whereParam = whereParam;
    }
    
}
