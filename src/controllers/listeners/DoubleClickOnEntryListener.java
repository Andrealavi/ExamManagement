/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners
 */
package controllers.listeners;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import controllers.listeners.modify.ModifyExamListener;
import models.ExamsTableModel;
import models.exam.*;
import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.ModifySimpleExamDialog;

/**
 * Extends {@link java.awt.event.WindowAdapter} to create a custom event
 * listener for when the user is closing the application window
 * 
 * @see views.dialogs.ModifyComposedExamDialog
 * @see views.dialogs.ModifySimpleExamDialog
 * @see models.ExamsTableModel
 * @see models.exam.SimpleExam
 * @see models.exam.ComposedExam
 * @see java.awt.event.MouseAdapter
 */
public class DoubleClickOnEntryListener extends MouseAdapter {
    /**
     * Application frame
     */
    private JFrame frame;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param frame      Application frame
     * @param isSaved    Boolean used to check whether the exam entries are saved or
     *                   not
     * @param isFiltered Boolean used to check whether the exam entries are filtered
     *                   or not
     */
    public DoubleClickOnEntryListener(JFrame frame, AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Gets the index of the clicked table row and opens a modify dialog in case the
     * entry is clicked twice. It also sets the action listeners
     */
    @Override
    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point p = e.getPoint();
        int row = table.convertRowIndexToModel(table.rowAtPoint(p));

        if (e.getClickCount() == 2) {
            ExamsTableModel model = (ExamsTableModel) table.getModel();
            AbstractExam entry = model.getEntryAtRow(row);
            AbstractExamDialog dialog;

            if (entry.getClass().getSimpleName().equals("SimpleExam")) {
                dialog = new ModifySimpleExamDialog(frame, model.getColumns());
                ((ModifySimpleExamDialog) dialog).setEntryFields(entry.toStringArray());

                ((ModifySimpleExamDialog) dialog).getModifyButton()
                        .addActionListener(new ModifyExamListener(dialog, model, row, isSaved, isFiltered));
            } else {
                dialog = new ModifyComposedExamDialog(frame);
                ((ModifyComposedExamDialog) dialog).setEntryFields(entry.toStringArray(),
                        ((ComposedExam) entry).getPartialExamsGrades(),
                        ((ComposedExam) entry).getPartialExamsWeights());

                ((ModifyComposedExamDialog) dialog).getModifyButton()
                        .addActionListener(new ModifyExamListener(dialog, model, row, isSaved, isFiltered));
            }

            dialog.getButton().addActionListener(new CloseButtonListener(dialog));
            dialog.getRootPane().setDefaultButton(dialog.getButton());
        }
    }
}
