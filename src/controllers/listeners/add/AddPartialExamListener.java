package controllers.listeners.add;

import java.awt.event.*;

import controllers.listeners.remove.RemovePartialExamListener;
import views.dialogs.AddComposedExamDialog;
import views.dialogs.ModifyComposedExamDialog;

public class AddPartialExamListener implements ActionListener {
    private AddComposedExamDialog dialog;

    public AddPartialExamListener(AddComposedExamDialog dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent e) {
        dialog.getLastPartialExam().getAddButton()
                .removeActionListener(dialog.getLastPartialExam().getAddButton().getActionListeners()[0]);
        dialog.getLastPartialExam().getRemoveButton()
                .removeActionListener(dialog.getLastPartialExam().getRemoveButton().getActionListeners()[0]);

        dialog.addPartialExam();
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.refreshButton();

        if (dialog.getClass().getSimpleName().equals("ModifyComposedExamDialog")) {
            ((ModifyComposedExamDialog) dialog).refreshModifyButton();
        }

        dialog.pack();
    }
}
