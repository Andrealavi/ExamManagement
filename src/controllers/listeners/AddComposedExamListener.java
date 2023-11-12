package controllers.listeners;

import java.awt.event.*;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddComposedExamDialog;

public class AddComposedExamListener implements ActionListener {
    AppFrame f;
    final String[] COL_NAMES;
    ExamsTableModel model;

    public AddComposedExamListener(AppFrame f, String[] columnNames, ExamsTableModel model) {
        this.f = f;
        this.COL_NAMES = columnNames;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        AddComposedExamDialog d = new AddComposedExamDialog(f);

        d.getButton().addActionListener(new ComposedExamDialogListener(d, model));
        d.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(d));
        d.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(d));
    }
}
