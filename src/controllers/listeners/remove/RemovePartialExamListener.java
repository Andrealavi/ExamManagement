package controllers.listeners.remove;

import java.awt.event.*;

import controllers.listeners.add.AddPartialExamListener;
import views.dialogs.AddComposedExamDialog;
import views.dialogs.ModifyComposedExamDialog;

public class RemovePartialExamListener implements ActionListener {
    private AddComposedExamDialog dialog;

    public RemovePartialExamListener(AddComposedExamDialog dialog) {
        this.dialog = dialog;
    }

    public void actionPerformed(ActionEvent e) {
        dialog.getLastPartialExam().getAddButton()
                .removeActionListener(dialog.getLastPartialExam().getAddButton().getActionListeners()[0]);
        dialog.getLastPartialExam().getRemoveButton()
                .removeActionListener(dialog.getLastPartialExam().getRemoveButton().getActionListeners()[0]);

        dialog.removePartialExam();
        dialog.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(dialog));
        dialog.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(dialog));

        dialog.refreshButton();

        if (dialog.getClass().getSimpleName().equals("ModifyComposedExamDialog")) {
            ModifyComposedExamDialog dialog = (ModifyComposedExamDialog) this.dialog;
            dialog.refreshModifyButton();
        }

        dialog.pack();
    }
}
