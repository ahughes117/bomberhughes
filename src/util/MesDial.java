/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import GUI.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex Hughes
 */
public class MesDial {

    private static String conError = "Σφάλμα κατά την προσπάθεια σύνδεσης με τη Βάση Δεδομένων. "
            + "Ελέγξτε την κατάσταση ή τα στοιχεία της σύνδεσης και προσπαθήστε ξανά.";
    private static String conSuccess = "Επιτυχία Σύνδεσης!";
    private static String saveSuccess = "Η Βάση Δεδομένων ενημερώθηκε επιτυχώς!";
    private static String noRowSelected = "Πρέπει να επιλέξετε μία γραμμή.";
    private static String multipleRowsSelected = "Δεν μπορείτε να διορθώσετε/διαγράψετε ταυτόχρονα πάνω από μία γραμμή.";
    private static String programError = "Παρουσιάστηκε Εσωτερικό Σφάλμα Προγράμματος. Παρακαλώ επανεκινήστε το πρόγραμμα.";
    private static String dateError = "Εισάγετε μία έγκυρη ημερομηνία. (π.χ. 5/10/1972)";
    private static String timeError = "Εισάγετε μία έγκυρή μορφή ώρας. (π.χ. 13:26)";
    private static String noCustomer = "Πρέπει να επιλέξετε κάποιο πελάτη.";
    private static String related = "Η συσχέτιση που θέλετε να εκτελέσετε ήδη υπάρχει.";
    private static String doubleError = "Εισάγετε έγκυρους δεκαδικούς. (πχ 9.2)";
    private static String intError = "Εισάγετε έναν έγκυρο ακέραιο. (πχ 117)";
    private static String mailError = "Παρουσιάστηκε σφάλμα στο σύστημα αποστολής email";
    //
    private static String deleteQuestion = "Διαγραφή;";

    public static void conSuccess(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, conSuccess, "Επιτυχία!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void saveSuccess(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, saveSuccess, "Επιτυχία!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void conError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, conError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void noRowSelected(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, noRowSelected, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void multipleRowsSelected(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, multipleRowsSelected, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void programError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, programError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void dateError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, dateError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void noCustomer(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, noCustomer, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void related(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, related, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void doubleError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, doubleError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static int deleteQuestion(GUI aFrame) {
        Object[] options = {"ΟΚ", "Άκυρο"};

        return JOptionPane.showOptionDialog(null, deleteQuestion, "Επιβεβαίωση", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static void timeError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, timeError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void mailError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, mailError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }

    public static void intError(GUI aFrame) {
        JOptionPane.showMessageDialog(aFrame, intError, "Σφάλμα", JOptionPane.ERROR_MESSAGE);
    }
}
