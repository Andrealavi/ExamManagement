package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ExamInfoException;
import models.exam.SimpleExam;
import models.ExamsTableModel;

public class ModifySimpleExamDialogListener implements ActionListener {
    private AddSimpleExamDialog d;
    private ExamsTableModel model;
    private int row;
    private Boolean isSaved;

    public ModifySimpleExamDialogListener(ModifySimpleExamDialog d, ExamsTableModel model, int row, Boolean isSaved) {
        this.d = d;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        String[] data = d.getFieldsData();

        try {
            SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

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