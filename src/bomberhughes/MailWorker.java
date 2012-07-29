package bomberhughes;

import entities.UpdateComs;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.SwingWorker;
import mail.GMail;
import mail.Mail;
import mail.SimpleMail;
import mail.UnauthMail;
import sql.DBParsers;

/**
 * Class MailWorker
 *
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class MailWorker extends SwingWorker {

    private Scheduler sche;
    private Mail mailer;
    private UpdateComs uc;
    private DateFormat startTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private DateFormat nowTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private DateFormat endTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private int sentN;

    public MailWorker(Scheduler aScheduler, UpdateComs aUpdateComs) {
        sche = aScheduler;
        uc = aUpdateComs;
    }

    @Override
    protected Object doInBackground() throws Exception {
        int i = 0;
        int j = 0;
        sentN = j;
        status("start");

        while (i < sche.getServers().size() && j < sche.getMessages().size()) {

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

            try {
                mailer.sendMail(sche.getMessages().get(j), sche.getServers().get(i), sche.getDbs());
                if (DBParsers.sendNewsUpdate(sche.getCon(), sche.getDbs(), sche.getMessages().get(j).getUUID()) == 1) {
                    break;
                }
                j++;
                sentN = j;
                status("middle");
            } catch (AddressException ex) {
                System.out.println("Address: " + sche.getMessages().get(i).getAddress().getAddress() + " is invalid ");
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                j++;
            } catch (MessagingException ex) {
                System.out.println("***Probably there is sth wrong with current server: "
                        + sche.getServers().get(i).getHost() + ". Switching server...***");
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                i++;
            } catch (UnsupportedEncodingException ex) {
                System.out.println("Wrong Encoding");
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
                j++;
            }

        }
        return null;
    }

    private void status(String aPoint) {
        if (aPoint.equals("start")) {
            uc.getStartBtn().setEnabled(false);
            uc.getProgressB().setIndeterminate(true);
            uc.getStartL().setText(startTime.format(Calendar.getInstance().getTime()));
            uc.getLeftL().setText(Integer.toString(sche.getMessages().size()));
            uc.getSentL().setText(Integer.toString(sentN));
        } else if (aPoint.equals("middle")) {
            uc.getSentL().setText(Integer.toString(sentN));
            uc.getLeftL().setText(Integer.toString(sche.getMessages().size() - sentN));

            /*
             * Calculating average till now
             */
            long elapsed = Calendar.getInstance().getTimeInMillis()
                    - startTime.getCalendar().getTimeInMillis();
            int avg = (int) ((sentN / (elapsed / 1000)) * 60);

            uc.getAvgL().setText(Integer.toString(avg));

        } else if (aPoint.equals("end")) {
            uc.getProgressB().setIndeterminate(false);
            uc.getStartBtn().setEnabled(true);
            uc.getFinishL().setText(endTime.format(Calendar.getInstance().getTime()));

            /*
             * Calculating final average.
             */
            long elapsed = endTime.getCalendar().getTimeInMillis()
                    - startTime.getCalendar().getTimeInMillis();
            int avg = (int) ((sche.getMessages().size() / (elapsed / 1000)) * 60);
            uc.getAvgL().setText(Integer.toString(avg));
        } else if (aPoint.equals("interrupted")) {
            uc.getProgressB().setIndeterminate(false);
            uc.getStartBtn().setEnabled(true);
            uc.getFinishL().setText(endTime.format(Calendar.getInstance().getTime()));

            /*
             * Calculating average for sent messages.
             */
            long elapsed = Calendar.getInstance().getTimeInMillis()
                    - startTime.getCalendar().getTimeInMillis();
            int avg = (int) ((sentN / (elapsed / 1000)) * 60);

            uc.getAvgL().setText(Integer.toString(avg));
        }
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            status("interrupted");
        } else {
            status("end");
        }
    }
}
