/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 * Contains all the info about the table and field names of the DB that are
 * needed
 *
 * @author Alex Hughes
 */
public class DBStruct extends Credentials implements Serializable {

    private String serverT;
    private String emailT;
    //
    private String addressF;
    private String uidF;
    private String unsubscribedF;
    //
    private String hostF;
    private String userF;
    private String passF;
    private String typeF;
    //
    private UserPref emailPref;
    
    
    public String getAddressF() {
        return addressF;
    }

    public void setAddressF(String addressF) {
        this.addressF = addressF;
    }

    public String getEmailT() {
        return emailT;
    }

    public void setEmailT(String emailT) {
        this.emailT = emailT;
    }
    
    public String getHostF() {
        return hostF;
    }

    public void setHostF(String hostF) {
        this.hostF = hostF;
    }

    public String getPassF() {
        return passF;
    }

    public void setPassF(String passF) {
        this.passF = passF;
    }

    public String getServerT() {
        return serverT;
    }

    public void setServerT(String serverT) {
        this.serverT = serverT;
    }

    public String getTypeF() {
        return typeF;
    }

    public void setTypeF(String typeF) {
        this.typeF = typeF;
    }

    public String getUidF() {
        return uidF;
    }

    public void setUidF(String uidF) {
        this.uidF = uidF;
    }

    public String getUnsubscribedF() {
        return unsubscribedF;
    }

    public void setUnsubscribedF(String unsubscribedF) {
        this.unsubscribedF = unsubscribedF;
    }

    public String getUserF() {
        return userF;
    }

    public void setUserF(String userF) {
        this.userF = userF;
    }

    public UserPref getEmailPref() {
        return emailPref;
    }

    public void setEmailPref(UserPref emailPref) {
        this.emailPref = emailPref;
    }
}
