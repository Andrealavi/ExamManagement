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
import controllers.listeners.remove.RemoveExamListener;
import models.ExamsTableModel;
import models.exam.*;
import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.ModifyExamDialogInterface;
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

        if (e.getClickCount() == 2 && table.rowAtPoint(p) != -1) {
            int row = table.convertRowIndexToModel(table.rowAtPoint(p));

            ExamsTableModel model = (ExamsTableModel) table.getModel();
            AbstractExam entry = model.getEntryAtRow(row);
            ModifyExamDialogInterface dialog;

            if (entry.getClass().getSimpleName().equals("SimpleExam")) {
                dialog = new ModifySimpleExamDialog(frame, model.getColumns());
            } else {
                dialog = new ModifyComposedExamDialog(frame);
            }

            dialog.setEntryFields(entry.toStringArray());

            dialog.getModifyButton()
                    .addActionListener(
                            new ModifyExamListener(((AbstractExamDialog) dialog), model, row, isSaved, isFiltered));

            dialog.getRemoveButton()
                    .addActionListener(
                            new RemoveExamListener((AbstractExamDialog) dialog, table, row, isSaved, isFiltered));

            ((AbstractExamDialog) dialog).getButton()
                    .addActionListener(new CloseButtonListener(((AbstractExamDialog) dialog)));
            ((AbstractExamDialog) dialog).getRootPane().setDefaultButton(((AbstractExamDialog) dialog).getButton());
        }
    }
}
