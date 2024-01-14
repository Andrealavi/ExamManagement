/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.modify
 */
package controllers.listeners.modify;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import controllers.listeners.add.AddPartialExamListener;
import controllers.listeners.remove.RemovePartialExamListener;
import models.ExamsTableModel;
import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.AddComposedExamDialog.PartialExamView;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for the modify button in
 * {@link views.dialogs.ModifySimpleExamDialog} and
 * {@link views.dialogs.ModifyComposedExamDialog}.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class ModifyExamListener implements ActionListener {
    /**
     * Dialog with exam data
     */
    private AbstractExamDialog dialog;

    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Row index of the entry to modify
     */
    private int row;

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
     * @param dialog     Dialog with exam data
     * @param model      Table model
     * @param row        Row index
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public ModifyExamListener(AbstractExamDialog dialog, ExamsTableModel model, int row,
            AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Enables the update mode of {@link views.dialogs.ModifyComposedExamDialog}
     */
    public void enableComposedExamUpdate() {
        PartialExamView[] partialExams = ((ModifyComposedExamDialog) dialog).getPartialExams();

        for (int i = 0; i < partialExams.length; i++) {
            partialExams[i].getGradeField().setEditable(true);
            partialExams[i].getWeightField().setEditable(true);
        }

        partialExams[partialExams.length - 1].getAddButton().setEnabled(true);
        partialExams[partialExams.length - 1].getRemoveButton().setEnabled(true);

        partialExams[partialExams.length - 1].getAddButton()
                .addActionListener(new AddPartialExamListener(((ModifyComposedExamDialog) dialog)));
        partialExams[partialExams.length - 1].getRemoveButton()
                .addActionListener(new RemovePartialExamListener(((ModifyComposedExamDialog) dialog)));
    }

    /**
     * Sets dialogs fields as editable and adds the action listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.getButton().setText("Update");

        dialog.getButton().removeActionListener(dialog.getButton().getActionListeners()[0]);
        dialog.getButton().addActionListener(new ModifyExamDialogListener(dialog, model, row, isSaved, isFiltered));

        dialog.getRootPane().setDefaultButton(dialog.getButton());

        dialog.pack();

        JTextField[] generalFields = dialog.getGeneralFields();

        if (dialog.getClass().getSimpleName().equals("ModifyComposedExamDialog")) {
            enableComposedExamUpdate();
        }

        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setEditable(true);
        }

    }
}
