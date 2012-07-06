/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;


import java.io.*;

/**
 *
 * @author Alex Hughes
 */
public class Credentials implements Serializable {

    private String driver = "jdbc:mysql://";
    private String URL;
    private String username;
    private String password;
    private String port = ":3306/";
    private String schema;

    public Credentials(String aURL, String aUsername, String aPassword,
            String aSchema) {
        URL = aURL;
        username = aUsername;
        password = aPassword;
        schema = aSchema;
    }

    public String getDriver() {
        return driver;
    }

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPort() {
        return port;
    }

    public String getSchema() {
        return schema;
    }

    public void setURL(String aURL) {
        URL = aURL;
    }

    public void setUsername(String aUsername) {
        username = aUsername;
    }

    public void setPassword(String aPassword) {
        password = aPassword;
    }
    
    public void setSchema(String aSchema) {
        schema = aSchema;
    }

    public void clear() {
        URL = null;
        username = null;
        password = null;
        schema = null;
    }

    public static void saveCredentials(Credentials cre) {
        try {
            FileOutputStream fileOut = new FileOutputStream("credentials.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            out.writeObject(cre);

            out.close();
            fileOut.close();
            
            if(Connector.LOGGER){
                System.out.println("Credentials Saved Succesfully");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("*** Credentials not found ***");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("*** Error while saving credentials ***");
        }
    }

    public static Credentials loadCredentials() {
        Credentials cre = new Credentials("", "", "", "");

        try {
            FileInputStream fileIn = new FileInputStream("credentials.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            Object obj = in.readObject();

            if (obj instanceof Credentials) {
                cre = (Credentials) obj;
            }

            in.close();
            fileIn.close();
            
            if(Connector.LOGGER){
                System.out.println("Credentials loaded succesfully");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("*** Credentials not found ***");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("*** Error while loading credentials ***");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("*** Credentials file probably corrupted ***");
        }
        return cre;
    }
}
