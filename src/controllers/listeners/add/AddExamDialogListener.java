/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.add 
 */
package controllers.listeners.add;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import controllers.listeners.ExamDialogListener;
import views.AppFrame;
import views.dialogs.*;
import models.exam.*;
import models.ExamsTableModel;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener to add a new exam to the exam table.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class AddExamDialogListener extends ExamDialogListener implements ActionListener {
    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with exam data
     * @param model      Table model
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public AddExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        super(dialog, isFiltered);
        this.model = model;
        this.isSaved = isSaved;
    }

    /**
     * Creates a new exam entry and adds it to the exam table
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            AbstractExam examEntry;

            if (super.getDialog().getClass().getSimpleName().equals("AddSimpleExamDialog")) {
                examEntry = createSimpleExamEntry();
            } else {
                examEntry = createComposedExamEntry();
            }

            model.addEntry(examEntry);

            model.addRowNumber(((AppFrame) super.getDialog().getParent()).getTablePanel().getTable()
                    .convertRowIndexToView(model.getRowCount() - 1));

            isSaved.set(false);

            if (super.getFilterBoolean().get()) {
                updateFilter();
            }

            super.getDialog().dispose();
        } catch (ExamInfoException err) {
            JOptionPane.showMessageDialog(super.getDialog(), err.getMessage(), "Error message",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(super.getDialog(), "Invalid value inserted.\n" +
                    "Please make sure you have inserted valid number values for grade and credits fields.",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }

    }
}