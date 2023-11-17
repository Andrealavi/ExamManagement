package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ExamInfoException;
import models.exam.SimpleExam;
import models.ExamsTableModel;

public class SimpleExamDialogListener implements ActionListener {
    private AddSimpleExamDialog d;
    private ExamsTableModel model;
    private Boolean isSaved;

    public SimpleExamDialogListener(AddSimpleExamDialog d, ExamsTableModel model, Boolean isSaved) {
        this.d = d;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        String[] data = d.getFieldsData();

        try {
            SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

            model.addEntry(examEntry);

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