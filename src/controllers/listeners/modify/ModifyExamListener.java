package controllers.listeners.modify;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import controllers.listeners.add.AddPartialExamListener;
import controllers.listeners.remove.RemovePartialExamListener;
import models.ExamsTableModel;
import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.ModifySimpleExamDialog;
import views.dialogs.AddComposedExamDialog.PartialExamView;

public class ModifyExamListener implements ActionListener {
    public AbstractExamDialog dialog;
    private ExamsTableModel model;
    private int row;
    private AtomicBoolean isSaved;
    private AtomicBoolean isFiltered;

    public ModifyExamListener(AbstractExamDialog dialog, ExamsTableModel model, int row,
            AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    public void enableComposedExamUpdate() {
        PartialExamView[] partialExams = ((ModifyComposedExamDialog) dialog).getPartialExams();

        for (int i = 0; i < partialExams.length; i++) {
            partialExams[i].getGradeField().setEditable(true);
            partialExams[i].getWeightBox().setEnabled(true);
        }

        partialExams[partialExams.length - 1].getAddButton().setEnabled(true);
        partialExams[partialExams.length - 1].getRemoveButton().setEnabled(true);

        partialExams[partialExams.length - 1].getAddButton()
                .addActionListener(new AddPartialExamListener(((ModifyComposedExamDialog) dialog)));
        partialExams[partialExams.length - 1].getRemoveButton()
                .addActionListener(new RemovePartialExamListener(((ModifyComposedExamDialog) dialog)));
    }

    public void enableSimpleExamUpdate() {

    }

    public void actionPerformed(ActionEvent e) {
        dialog.getButton().setText("Update");
        dialog.getButton().removeActionListener(dialog.getButton().getActionListeners()[0]);
        dialog.getButton().addActionListener(new ModifyExamDialogListener(dialog, model, row, isSaved, isFiltered));

        dialog.pack();

        JTextField[] generalFields = null;

        if (dialog.getClass().getSimpleName().equals("ModifySimpleExamDialog")) {
            generalFields = ((ModifySimpleExamDialog) dialog).getGeneralFields();
        } else {
            generalFields = ((ModifyComposedExamDialog) dialog).getGeneralFields();
            enableComposedExamUpdate();
        }

        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setEditable(true);
        }

    }
}
