package views.dialogs;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;

public class ModifySimpleExamDialog extends AddSimpleExamDialog {
    private JButton modifyButton;

    public ModifySimpleExamDialog(JFrame f, String[] columnNames) {
        super(f, columnNames);

        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setEditable(false);
        }

        honorCheckBox.setEnabled(false);

        modifyButton = new JButton("Modify");

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;

        add(modifyButton, buttonConstraints);
    }

    public void setEntryFields(String[] stringArray) {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setText(stringArray[i]);
        }

        if (stringArray[textFields.length].equals("Sì")) {
            honorCheckBox.setSelected(true);
        } else {
            honorCheckBox.setSelected(false);
        }
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public JTextField[] getTextFields() {
        return textFields;
    }

    public JCheckBox getCheckBox() {
        return honorCheckBox;
    }
}