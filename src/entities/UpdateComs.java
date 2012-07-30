
package entities;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 * Class UpdateComs
 * The class that contains the updatable components while emails are sent
 * @author Alex Hughes <alexhughes117@gmail.com>
 */
public class UpdateComs {
    
    private JLabel sentL;
    private JLabel leftL;
    private JLabel startL;
    private JLabel avgL;
    private JLabel finishL;
    private JLabel statusL;
    
    private JButton startBtn;
    private JProgressBar progressB;

    public UpdateComs(JLabel sentL, JLabel leftL, JLabel startL, JLabel avgL, JLabel finishL, JButton startBtn, JProgressBar progressB, JLabel statusL) {
        this.sentL = sentL;
        this.leftL = leftL;
        this.startL = startL;
        this.avgL = avgL;
        this.finishL = finishL;
        this.startBtn = startBtn;
        this.progressB = progressB;
        this.statusL = statusL;
    }

    public UpdateComs() {
    }

    public JLabel getAvgL() {
        return avgL;
    }

    public void setAvgL(JLabel avgL) {
        this.avgL = avgL;
    }

    public JLabel getFinishL() {
        return finishL;
    }

    public void setFinishL(JLabel finishL) {
        this.finishL = finishL;
    }

    public JLabel getLeftL() {
        return leftL;
    }

    public void setLeftL(JLabel leftL) {
        this.leftL = leftL;
    }

    public JProgressBar getProgressB() {
        return progressB;
    }

    public void setProgressB(JProgressBar progressB) {
        this.progressB = progressB;
    }

    public JLabel getSentL() {
        return sentL;
    }

    public void setSentL(JLabel sentL) {
        this.sentL = sentL;
    }

    public JButton getStartBtn() {
        return startBtn;
    }

    public void setStartBtn(JButton startBtn) {
        this.startBtn = startBtn;
    }

    public JLabel getStartL() {
        return startL;
    }

    public void setStartL(JLabel startL) {
        this.startL = startL;
    }

    public JLabel getStatusL() {
        return statusL;
    }

    public void setStatusL(JLabel statusL) {
        this.statusL = statusL;
    }
}
