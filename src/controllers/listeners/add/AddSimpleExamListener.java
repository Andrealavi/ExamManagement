package controllers.listeners.add;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddSimpleExamDialog;

public class AddSimpleExamListener implements ActionListener {
    private AppFrame frame;
    private final String[] COL_NAMES;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;

    public AddSimpleExamListener(AppFrame frame, String[] columnNames, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.COL_NAMES = columnNames;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        AddSimpleExamDialog dialog = new AddSimpleExamDialog(frame, COL_NAMES);

        dialog.getButton().addActionListener(new ExamDialogListener(dialog, model, isSaved));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
