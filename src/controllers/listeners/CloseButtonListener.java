/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners
 */
package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import views.dialogs.ModifyExamDialogInterface;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for close button of dialogs
 */
public class CloseButtonListener implements ActionListener {
    /**
     * Dialog containing close button
     */
    private JDialog dialog;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param dialog Modify Dialog Interface
     */
    public CloseButtonListener(ModifyExamDialogInterface dialog) {
        this.dialog = (JDialog) dialog;
    }

    /**
     * Closes the dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.dispose();
    }
}
