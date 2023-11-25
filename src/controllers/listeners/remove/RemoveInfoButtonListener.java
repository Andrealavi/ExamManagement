/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.remove
 */
package controllers.listeners.remove;

import java.awt.event.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for the info button of {@link views.dialogs.RemoveEntryDialog}
 * 
 * @see views.dialogs.RemoveEntryDialog
 * @see javax.swing.JDialog
 * @see javax.swing.JOptionPane
 */
public class RemoveInfoButtonListener implements ActionListener {
    /**
     * Remove entries dialog
     */
    private JDialog removeDialog;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param removeDialog Remove entries dialog
     */
    public RemoveInfoButtonListener(JDialog removeDialog) {
        this.removeDialog = removeDialog;
    }

    /**
     * Shows a {@link javax.swing.JOptionPane} with an info message regarding how to
     * format the remove expression
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String message = "It is possible to remove either single entries or interval of entries.\n" +
                "The remove expression must follow the following pattern:\n" + "\tindex\n\tstart-finish\n"
                + "Examples:\n" + "the expression 1,2,3 removes the first three entries\n"
                + "the expression 1-5 removes the first 5 entries";

        JOptionPane.showMessageDialog(removeDialog, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
