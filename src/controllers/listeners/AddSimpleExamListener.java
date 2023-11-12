package controllers.listeners;

import java.awt.event.*;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddSimpleExamDialog;

public class AddSimpleExamListener implements ActionListener {
    AppFrame f;
    final String[] COL_NAMES;
    ExamsTableModel model;

    public AddSimpleExamListener(AppFrame f, String[] columnNames, ExamsTableModel model) {
        this.f = f;
        this.COL_NAMES = columnNames;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        AddSimpleExamDialog d = new AddSimpleExamDialog(f, COL_NAMES);

        d.getButton().addActionListener(new SimpleExamDialogListener(d, model));
    }
}
