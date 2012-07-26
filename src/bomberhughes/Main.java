/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberhughes;

import GUI.*;
import sql.Connector;

/**
 * Bulk Mail Utility.
 *
 * @author Alex Hughes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connector.AUTOCOMMIT = false;
        Connector.LOGGER = true;
        Connector.QUERY = true;
        new LoginFrame();
    }
}
