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

    public ModifyComposedExamDialogListener(ModifyComposedExamDialog d, ExamsTableModel model, int row) {
        this.d = d;
        this.model = model;
        this.row = row;
    }

    public void actionPerformed(ActionEvent e) {
        String[] generalInfo = d.getFieldsData();

        try {
            ComposedExam examEntry = new ComposedExam(generalInfo[0], generalInfo[1], generalInfo[2], generalInfo[3]);
            examEntry.setPartialExamsInfo(d.getExamsData(), d.getExamNumber());

            model.updateEntryAtRow(examEntry, row);

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