package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class CloseButtonListener implements ActionListener {
    private JDialog dialog;

    public CloseButtonListener(JDialog dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent e) {
        dialog.dispose();
    }
}
