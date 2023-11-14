package controllers.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import views.dialogs.ModifySimpleExamDialog;

public class ModifySimpleExamListener implements ActionListener {
    private ModifySimpleExamDialog d;

    public ModifySimpleExamListener(ModifySimpleExamDialog d) {
        this.d = d;
    }

    public void actionPerformed(ActionEvent e) {
        JTextField[] textFields = d.getTextFields();

        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setEditable(true);
        }
        
        d.getCheckBox().setEnabled(true);
    }
}
