package views.dialogs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.*;

public class ModifySimpleExamDialog extends AddSimpleExamDialog {
    private JButton modifyButton;

    public ModifySimpleExamDialog(JFrame f, String[] columnNames) {
        super(f, columnNames);

        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setEditable(false);
        }

        modifyButton = new JButton("Modify");
        actionButton.setText("Close");

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(modifyButton, buttonConstraints);
    }

    public void setEntryFields(String[] stringArray) {
        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setText(stringArray[i]);
        }
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public JTextField[] getGeneralFields() {
        return generalFields;
    }
}
