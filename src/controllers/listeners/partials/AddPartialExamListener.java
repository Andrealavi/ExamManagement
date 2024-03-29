/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.add 
 */
package controllers.listeners.partials;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.dialogs.AddComposedExamDialog;
import views.dialogs.ModifyComposedExamDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for add partial exam button of composed exam dialog.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see views.dialogs.ModifyComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class AddPartialExamListener implements ActionListener {
    /**
     * Dialog with composed exam data
     */
    private AddComposedExamDialog dialog;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param dialog Dialog in which the button is contained
     */
    public AddPartialExamListener(AddComposedExamDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * Adds a new {@link views.dialogs.AddComposedExamDialog.PartialExamView} to
     * {@link views.dialogs.AddComposedExamDialog}. It also manages to
     * remove and add action listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.getLastPartialExam().getAddButton()
                .removeActionListener(dialog.getLastPartialExam().getAddButton().getActionListeners()[0]);
        dialog.getLastPartialExam().getRemoveButton()
                .removeActionListener(dialog.getLastPartialExam().getRemoveButton().getActionListeners()[0]);

        dialog.addPartialExam();
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.refreshButton();

        if (dialog.getClass().getSimpleName().equals("ModifyComposedExamDialog")) {
            ((ModifyComposedExamDialog) dialog).refreshModifyButton();
            ((ModifyComposedExamDialog) dialog).refreshRemoveButton();
        }

        dialog.pack();
    }
}
