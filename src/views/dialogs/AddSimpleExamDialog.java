package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class AddSimpleExamDialog extends JDialog {
    protected JLabel[] labels;
    protected JTextField[] textFields;
    protected JButton actionButton;
    protected JCheckBox honorCheckBox;

    public AddSimpleExamDialog(JFrame f, String[] columnNames) {
        super(f, "Add Simple Exam");

        final int N = columnNames.length - 1;

        labels = new JLabel[N];
        textFields = new JTextField[N];
        honorCheckBox = new JCheckBox("Honor");
        honorCheckBox.setSelected(false);

        actionButton = new JButton("Add Exam");

        setLayout(new GridBagLayout());

        GridBagConstraints[] labelsConstraints = new GridBagConstraints[N];
        GridBagConstraints[] textFieldsConstraints = new GridBagConstraints[N];
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        GridBagConstraints checkBoxConstraints = new GridBagConstraints();

        for (int i = 0, x = 0; i < N; i++, x += 2) {
            labels[i] = new JLabel(String.format("%s:", columnNames[i]));
            labelsConstraints[i] = new GridBagConstraints();

            textFields[i] = new JTextField(String.format("Insert %s", columnNames[i]), 10);
            textFieldsConstraints[i] = new GridBagConstraints();

            labelsConstraints[i].gridx = x;
            labelsConstraints[i].gridy = 0;
            labelsConstraints[i].insets = new Insets(10, 5, 10, 5);

            textFieldsConstraints[i].gridx = x + 1;
            textFieldsConstraints[i].gridy = 0;
            textFieldsConstraints[i].insets = new Insets(10, 5, 10, 5);

            add(labels[i], labelsConstraints[i]);
            add(textFields[i], textFieldsConstraints[i]);
        }

        checkBoxConstraints.gridx = N * 2;
        checkBoxConstraints.gridy = 0;
        checkBoxConstraints.insets = new Insets(10, 10, 10, 10);

        add(honorCheckBox, checkBoxConstraints);

        buttonConstraints.gridx = (N + 1) * 2;
        buttonConstraints.gridy = 1;

        add(actionButton, buttonConstraints);
        pack();
        setVisible(true);
    }

    public JButton getButton() {
        return actionButton;
    }

    public String[] getFieldsData() {
        String[] fieldsData = new String[textFields.length + 1];

        for (int i = 0; i < textFields.length; i++) {
            fieldsData[i] = textFields[i].getText();
        }

        fieldsData[textFields.length] = String.valueOf(honorCheckBox.isSelected());

        return fieldsData;
    }
}
