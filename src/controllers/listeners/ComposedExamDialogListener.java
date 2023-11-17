package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ComposedExam;
import models.exam.ExamInfoException;
import models.ExamsTableModel;

public class ComposedExamDialogListener implements ActionListener {
    private AddComposedExamDialog d;
    private ExamsTableModel model;
    private Boolean isSaved;

    public ComposedExamDialogListener(AddComposedExamDialog d, ExamsTableModel model, Boolean isSaved) {
        this.d = d;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        String[] data = d.getFieldsData();
        String[][] partialExamsData = d.getExamsData();

        try {
            ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
            examEntry.setPartialExamsInfo(partialExamsData, d.getExamNumber());

            model.addEntry(examEntry);

            isSaved = false;

            d.dispose();
        } catch (ExamInfoException err) {
            ErrorDialog errDialog = new ErrorDialog(d, err.getMessage());

            errDialog.getButton().addActionListener(new ErrorDialogButtonListener(errDialog));
        } catch (NumberFormatException err) {
            ErrorDialog errDialog = new ErrorDialog(d,
                    "Invalid value inserted.\nPlease make sure you have inserted valid number values for grades and credits fields.");

            errDialog.getButton().addActionListener(new ErrorDialogButtonListener(errDialog));
        }

    }
}
