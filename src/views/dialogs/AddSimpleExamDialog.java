package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class AddSimpleExamDialog extends AbstractExamDialog {

    public AddSimpleExamDialog(JFrame f, String[] columnNames) {
        super(f, "Add Simple Exam");

        final int N = columnNames.length - 1;

        generalLabels = new JLabel[N];
        generalFields = new JTextField[N];

        actionButton = new JButton("Add Exam");

        setLayout(new GridBagLayout());

        GridBagConstraints[] generalLabelsConstraints = new GridBagConstraints[N];
        GridBagConstraints[] generalFieldsConstraints = new GridBagConstraints[N];
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        for (int i = 0, x = 0; i < N; i++, x += 2) {
            generalLabels[i] = new JLabel(String.format("%s:", columnNames[i]));
            generalLabelsConstraints[i] = new GridBagConstraints();

            generalFields[i] = new JTextField(String.format("Insert %s", columnNames[i]), 10);
            generalFieldsConstraints[i] = new GridBagConstraints();

            generalLabelsConstraints[i].gridx = x;
            generalLabelsConstraints[i].gridy = 0;
            generalLabelsConstraints[i].insets = new Insets(10, 5, 10, 5);

            generalFieldsConstraints[i].gridx = x + 1;
            generalFieldsConstraints[i].gridy = 0;
            generalFieldsConstraints[i].insets = new Insets(10, 5, 10, 5);

            add(generalLabels[i], generalLabelsConstraints[i]);
            add(generalFields[i], generalFieldsConstraints[i]);
        }

        buttonConstraints.gridx = (N) * 2;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);
        pack();
        setVisible(true);
    }

    public JButton getButton() {
        return actionButton;
    }

    public String[] getFieldsData() {
        String[] fieldsData = new String[generalFields.length + 1];

        for (int i = 0; i < generalFields.length; i++) {
            fieldsData[i] = generalFields[i].getText();
        }

        return fieldsData;
    }
}
