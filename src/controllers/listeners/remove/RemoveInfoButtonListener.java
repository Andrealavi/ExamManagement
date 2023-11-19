package controllers.listeners.remove;

import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class RemoveInfoButtonListener implements ActionListener {
    private JDialog removeDialog;

    public RemoveInfoButtonListener(JDialog removeDialog) {
        this.removeDialog = removeDialog;
    }

    public void actionPerformed(ActionEvent e) {
        String message = "It is possible to remove either single entries or interval of entries.\n" +
                "The remove expression must follow the following pattern:\n" + "\tindex\n\tstart-finish\n"
                + "Examples:\n" + "the expression 1,2,3 removes the first three entries\n"
                + "the expression 1-5 removes the first 5 entries";

        JOptionPane.showMessageDialog(removeDialog, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
