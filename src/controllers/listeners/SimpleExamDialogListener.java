package controllers.listeners;

import java.awt.event.*;

import views.dialogs.*;
import models.exam.SimpleExam;
import models.ExamsTableModel;

public class SimpleExamDialogListener implements ActionListener {
    private AddSimpleExamDialog d;
    private ExamsTableModel model;

    public SimpleExamDialogListener(AddSimpleExamDialog d, ExamsTableModel model) {
        this.d = d;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String[] data = d.getFieldsData();

        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4], data[5]);

        model.addEntry(examEntry);

        d.dispose();
    }
}