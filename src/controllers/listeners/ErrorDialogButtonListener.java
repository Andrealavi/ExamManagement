package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class ErrorDialogButtonListener implements ActionListener {
    private JDialog d;

    public ErrorDialogButtonListener(JDialog d) {
        this.d = d;
    }

    public void actionPerformed(ActionEvent e) {
        d.dispose();
    }
}
