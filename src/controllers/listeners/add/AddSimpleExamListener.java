/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.add 
 */
package controllers.listeners.add;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddSimpleExamDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for add composed exam button of the application menu.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class AddSimpleExamListener implements ActionListener {
    /**
     * Application frame
     */
    private AppFrame frame;

    /**
     * Exam table column names
     */
    private final String[] COL_NAMES;

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
     * @param frame       Application frame
     * @param columnNames Array with column names
     * @param model       Table model
     * @param isSaved     Boolean containing save state of the exam table data
     * @param isFiltered  Boolean containing filter state of the exam table data
     */
    public AddSimpleExamListener(AppFrame frame, String[] columnNames, ExamsTableModel model, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.frame = frame;
        this.COL_NAMES = columnNames;
        this.model = model;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Creates a new {@link views.dialogs.AddSimpleExamDialog} and sets it to add
     * a new {@link models.exam.SimpleExam}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AddSimpleExamDialog dialog = new AddSimpleExamDialog(frame, COL_NAMES);

        dialog.getButton().addActionListener(new AddExamDialogListener(dialog, model, isSaved, isFiltered));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
