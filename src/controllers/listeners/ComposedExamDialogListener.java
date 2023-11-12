package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ComposedExam;
import models.exam.ExamInfoException;
import models.ExamsTableModel;

public class ComposedExamDialogListener implements ActionListener {
    private AddComposedExamDialog d;
    private ExamsTableModel model;

    public ComposedExamDialogListener(AddComposedExamDialog d, ExamsTableModel model) {
        this.d = d;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String[] data = d.getFieldsData();
        String[][] partialExamsData = d.getExamsData();

        try {
            ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
            examEntry.setPartialExamsInfo(partialExamsData, d.getExamNumber());

            model.addEntry(examEntry);

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
