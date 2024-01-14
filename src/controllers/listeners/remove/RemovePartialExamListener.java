/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.remove
 */
package controllers.listeners.remove;

import java.awt.event.*;

import controllers.listeners.add.AddPartialExamListener;
import views.dialogs.AddComposedExamDialog;
import views.dialogs.ModifyComposedExamDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for remove partial exam button of
 * {@link views.dialogs.AddComposedExamDialog}.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see views.dialogs.ModifyComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class RemovePartialExamListener implements ActionListener {

    private AddComposedExamDialog dialog;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param dialog Dialog in which the button is contained
     */
    public RemovePartialExamListener(AddComposedExamDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * Removes the last {@link views.dialogs.AddComposedExamDialog.PartialExamView}
     * from
     * {@link views.dialogs.AddComposedExamDialog}. It also manages to
     * remove and add action listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.getLastPartialExam().getAddButton()
                .removeActionListener(dialog.getLastPartialExam().getAddButton().getActionListeners()[0]);
        dialog.getLastPartialExam().getRemoveButton()
                .removeActionListener(dialog.getLastPartialExam().getRemoveButton().getActionListeners()[0]);

        dialog.removePartialExam();
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.refreshButton();

        if (dialog.getClass().getSimpleName().equals("ModifyComposedExamDialog")) {
            ModifyComposedExamDialog dialog = (ModifyComposedExamDialog) this.dialog;
            dialog.refreshModifyButton();
            dialog.refreshRemoveButton();
        }

        dialog.pack();
    }
}
