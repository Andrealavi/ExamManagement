package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.ComposedExam;
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

        ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
        examEntry.setPartialExamsInfo(partialExamsData, d.getExamNumber());

        model.addEntry(examEntry);

        d.dispose();
    }
}
