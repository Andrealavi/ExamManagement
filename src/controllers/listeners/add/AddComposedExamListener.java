package controllers.listeners.add;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import controllers.listeners.remove.RemovePartialExamListener;
import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.AddComposedExamDialog;

public class AddComposedExamListener implements ActionListener {
    private AppFrame frame;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;

    public AddComposedExamListener(AppFrame frame, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        AddComposedExamDialog dialog = new AddComposedExamDialog(frame);

        dialog.getButton().addActionListener(new ExamDialogListener(dialog, model, isSaved));
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
