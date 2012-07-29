package bomberhughes;

import entities.Email;
import entities.MyMessage;
import entities.SMTPServer;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import mail.GMail;
import mail.Mail;
import mail.SimpleMail;
import mail.UnauthMail;
import sql.DBParsers;

/**
 * Class MyWorker
 *
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class MyWorker extends SwingWorker {

    private Scheduler sche;
    private Mail mailer;
    private JButton btn;
    private JProgressBar pb;

    public MyWorker(Scheduler aScheduler, JButton aButton, JProgressBar aProgressBar) {
        sche = aScheduler;
        btn = aButton;
        pb = aProgressBar;
    }

    @Override
    protected Object doInBackground() throws Exception {
        boolean finish = false;
        btn.setEnabled(false);
        pb.setIndeterminate(true);
        
        int i, j;

        i = 0;
        j = 0;
        while (i < sche.getServers().size() && !finish) {

            switch (sche.getServers().get(i).getType()) {
                case "Gmail":
                    mailer = new GMail();
                    break;
                case "SimpleMail":
                    mailer = new SimpleMail();
                    break;
                case "UnauthMail":
                    mailer = new UnauthMail();
                    break;
            }

            while (j < sche.getMessages().size() && !finish) {
                try {
                    mailer.sendMail(sche.getMessages().get(j), sche.getServers().get(i), sche.getDbs());
                    if (DBParsers.sendNewsUpdate(sche.getCon(), sche.getDbs(), sche.getMessages().get(j).getUUID()) == 1) {
                        finish = true;
                    }
                    j++;
                    if (sche.getMessages().size() == j) {
                        finish = true;
                    }
                } catch (AddressException ex) {
                    System.out.println("Address: " + sche.getMessages().get(i).getAddress().getAddress() + " is invalid ");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                    j++;
                } catch (MessagingException ex) {
                    System.out.println("Probably there is sth wrong with current server: "
                            + sche.getServers().get(i).getHost() + ". Switching server...");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println("Wrong Encoding");
                    Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                    j++;
                }
            }
        }
        pb.setIndeterminate(false);
        btn.setEnabled(true);
        return null;
    }

    protected void done() {
    }
}
