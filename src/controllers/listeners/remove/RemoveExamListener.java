/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.remove
 */
package controllers.listeners.remove;

import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;

import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyExamDialogInterface;
import views.AppFrame;

import java.awt.event.ActionEvent;

/**
 * Extends {@link controllers.listeners.remove.RemoveAction} to implement an
 * event listener that removes a single exam entry
 */
public class RemoveExamListener extends RemoveAction {
    /**
     * Dialog in which the remove button is present
     */
    private AbstractExamDialog dialog;

    /**
     * Selected row. The entry to remove is at this row
     */
    private int row;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Modify Dialog Interface
     * @param table      Exam table
     * @param row        Index of the row to remove
     * @param isSaved    Boolean that checks whether the data are saved or not
     * @param isFiltered Boolean that checks whether the table is filtered or not
     */
    public RemoveExamListener(ModifyExamDialogInterface dialog, JTable table, int row, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        super((AppFrame) ((AbstractExamDialog) dialog).getParent(), table, isSaved, isFiltered);
        this.dialog = (AbstractExamDialog) dialog;
        this.row = row;
    }

    /**
     * Removes a single exam entry and disposes the dialog in which the remove
     * button is present
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        dialog.dispose();
    }
}
