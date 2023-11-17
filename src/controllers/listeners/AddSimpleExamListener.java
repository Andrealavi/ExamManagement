package controllers.listeners;

import java.awt.event.*;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddSimpleExamDialog;

public class AddSimpleExamListener implements ActionListener {
    private AppFrame f;
    private final String[] COL_NAMES;
    private ExamsTableModel model;
    private Boolean isSaved;

    public AddSimpleExamListener(AppFrame f, String[] columnNames, ExamsTableModel model, Boolean isSaved) {
        this.f = f;
        this.COL_NAMES = columnNames;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        AddSimpleExamDialog d = new AddSimpleExamDialog(f, COL_NAMES);

        d.getButton().addActionListener(new SimpleExamDialogListener(d, model, isSaved));

        d.getRootPane().setDefaultButton(d.getButton());
    }
}
