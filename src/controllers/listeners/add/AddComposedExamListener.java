/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.add
 */
package controllers.listeners.add;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import controllers.listeners.remove.RemovePartialExamListener;
import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddComposedExamDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for add composed exam button of the application menu.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class AddComposedExamListener implements ActionListener {
    /**
     * Application frame
     */
    private AppFrame frame;

    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame      Application frame
     * @param model      Table model
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public AddComposedExamListener(AppFrame frame, ExamsTableModel model, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.frame = frame;
        this.model = model;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Creates a new {@link views.dialogs.AddComposedExamDialog} and sets it to add
     * a new {@link models.exam.ComposedExam}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AddComposedExamDialog dialog = new AddComposedExamDialog(frame);

        dialog.getButton().addActionListener(new AddExamDialogListener(dialog, model, isSaved, isFiltered));
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
