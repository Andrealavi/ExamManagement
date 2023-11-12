package controllers.listeners;

import java.awt.event.*;
import views.dialogs.AddComposedExamDialog;

public class RemovePartialExamListener implements ActionListener {
    private AddComposedExamDialog d;

    public RemovePartialExamListener(AddComposedExamDialog d) {
        this.d = d;
    }

    public void actionPerformed(ActionEvent e) {
        d.getLastPartialExam().getAddButton()
                .removeActionListener(d.getLastPartialExam().getAddButton().getActionListeners()[0]);
        d.getLastPartialExam().getRemoveButton()
                .removeActionListener(d.getLastPartialExam().getRemoveButton().getActionListeners()[0]);

        d.removePartialExam();
        d.getLastPartialExam().getAddButton().addActionListener(new AddPartialExamListener(d));
        d.getLastPartialExam().getRemoveButton().addActionListener(new RemovePartialExamListener(d));

        d.refreshButton();

        d.pack();
    }
}
