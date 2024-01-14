/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.modify
 */
package controllers.listeners.modify;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import controllers.listeners.ExamDialogListener;
import views.dialogs.*;
import models.exam.*;
import models.ExamsTableModel;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener to modify an exam entry in the exam table.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see views.AppFrame
 * @see models.ExamsTableModel
 * @see models.SimpleExam
 * @see models.ComposedExam
 * @see controllers.listeners.filter.ClearFilterListener
 * @see controllers.listeners.filter.ShowStatsButtonListener
 * @see java.awt.event.ActionListener
 */
public class ModifyExamDialogListener extends ExamDialogListener implements ActionListener {
    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Row index of the entry to modify
     */
    private int row;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with exam data
     * @param model      Table model
     * @param row        Row index
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public ModifyExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, int row,
            AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        super(dialog, isFiltered);

        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
    }

    /**
     * Updates the entry in the exam table
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            AbstractExam examEntry;

            if (super.getDialog().getClass().getSimpleName().equals("ModifySimpleExamDialog")) {
                examEntry = createSimpleExamEntry();
            } else {
                examEntry = createComposedExamEntry();
            }

            model.updateEntryAtRow(examEntry, row);

            isSaved.set(false);

            if (super.getFilterBoolean().get()) {
                updateFilter();
            }

            super.getDialog().dispose();
        } catch (ExamInfoException err) {
            JOptionPane.showMessageDialog(super.getDialog(), err.getMessage(), "Error message",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(super.getDialog(),
                    "Invalid value inserted.\nPlease make sure you have inserted valid number values for grade and credits fields.",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }
}