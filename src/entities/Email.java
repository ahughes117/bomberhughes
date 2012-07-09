package entities;

/**
 * This is the Email entity class. I use only the address and the UUID of the
 * database for now.
 *
 * @author Alex Hughes
 */
public class Email implements Comparable {

    private String address;
    private String uid;

    /**
     * Full Constructor
     *
     * @param anEmail
     * @param aUid
     */
    public Email(String anEmail, String aUid) {
        address = anEmail;
        uid = aUid;
    }

    /**
     * Empty Constructor
     */
    public Email() {
    }

    public String getEmail() {
        return address;
    }

    public void setEmail(String email) {
        this.address = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Method to define how Email entity should be compared. Used by TreeSet.
     * Email entities are compared using ADDRESS.
     * @param anObject 
     * @return 
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Email) {
            //needed the minus sign to compare a-z. Dunno why.
            return -((Email) o).address.compareToIgnoreCase(this.address);
        } else {
            return 0;
        }
    }
}
