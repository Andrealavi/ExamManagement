package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.AddComposedExamDialog.PartialExamView;

public class ModifyComposedExamListener implements ActionListener {
    public ModifyComposedExamDialog d;

    public ModifyComposedExamListener(ModifyComposedExamDialog d) {
        this.d = d;
    }

    public void actionPerformed(ActionEvent e) {
        JTextField[] generalTextFields = d.getGeneralTextFields();
        PartialExamView[] partialExams = d.getPartialExams();

        for (int i = 0; i < generalTextFields.length; i++) {
            generalTextFields[i].setEditable(true);
        }

        for (int i = 0; i < partialExams.length; i++) {
            partialExams[i].getGradeField().setEditable(true);
            partialExams[i].getWeightBox().setEnabled(true);
            partialExams[i].getHonorBox().setEnabled(true);
        }

        partialExams[partialExams.length - 1].getAddButton().setEnabled(true);
        partialExams[partialExams.length - 1].getRemoveButton().setEnabled(true);

        partialExams[partialExams.length - 1].getAddButton().addActionListener(new AddPartialExamListener(d));
        partialExams[partialExams.length - 1].getRemoveButton().addActionListener(new RemovePartialExamListener(d));
    }
}
