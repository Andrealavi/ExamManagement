package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ComposedExam;
import models.exam.ExamInfoException;
import models.ExamsTableModel;

public class ModifyComposedExamDialogListener implements ActionListener {
    private ModifyComposedExamDialog d;
    private ExamsTableModel model;
    private int row;
    private Boolean isSaved;

    public ModifyComposedExamDialogListener(ModifyComposedExamDialog d, ExamsTableModel model, int row,
            Boolean isSaved) {
        this.d = d;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        String[] generalInfo = d.getFieldsData();

        try {
            ComposedExam examEntry = new ComposedExam(generalInfo[0], generalInfo[1], generalInfo[2], generalInfo[3]);
            examEntry.setPartialExamsInfo(d.getExamsData(), d.getExamNumber());

            model.updateEntryAtRow(examEntry, row);

            isSaved = false;

            d.dispose();
        } catch (ExamInfoException err) {
            ErrorDialog errDialog = new ErrorDialog(d, err.getMessage());

            errDialog.getButton().addActionListener(new ErrorDialogButtonListener(errDialog));
        } catch (NumberFormatException err) {
            ErrorDialog errDialog = new ErrorDialog(d,
                    "Invalid value inserted.\nPlease make sure you have inserted valid number values for grade and credits fields.");

            errDialog.getButton().addActionListener(new ErrorDialogButtonListener(errDialog));
        }

    }
}