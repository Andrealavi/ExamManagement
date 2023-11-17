package controllers.listeners;

import java.awt.event.*;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddComposedExamDialog;

public class AddComposedExamListener implements ActionListener {
    private AppFrame f;
    private final String[] COL_NAMES;
    private ExamsTableModel model;
    private Boolean isSaved;

    public AddComposedExamListener(AppFrame f, String[] columnNames, ExamsTableModel model, Boolean isSaved) {
        this.f = f;
        this.COL_NAMES = columnNames;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        AddComposedExamDialog d = new AddComposedExamDialog(f);

        d.getButton().addActionListener(new ComposedExamDialogListener(d, model, isSaved));
        d.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(d));
        d.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(d));

        d.getRootPane().setDefaultButton(d.getButton());
    }
}
