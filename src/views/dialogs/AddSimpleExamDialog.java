/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Implements a dialog for adding simple exams to the exams table. It extends
 * {@link views.dialogs.AbstractExamDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see views.dialogs.AbstractExamDialog
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
public class AddSimpleExamDialog extends AbstractExamDialog {

    /**
     * Class constructor. It populates
     * {@link views.dialogs.AbstractExamDialog#generalLabels} and
     * {@link views.dialogs.AbstractExamDialog#generalFields} and applies the
     * layout.
     * 
     * @param frame       Dialog parent
     * @param columnNames Table columns names
     */
    public AddSimpleExamDialog(JFrame frame, String[] columnNames) {
        super(frame, "Add Simple Exam");

        final int N = columnNames.length - 2;

        JLabel[] labels = new JLabel[N];
        JTextField[] fields = new JTextField[N];

        super.setButton(new JButton("Add Exam"));

        setLayout(new GridBagLayout());

        GridBagConstraints[] generalLabelsConstraints = new GridBagConstraints[N];
        GridBagConstraints[] generalFieldsConstraints = new GridBagConstraints[N];
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        for (int i = 0, x = 0; i < N; i++, x += 2) {
            labels[i] = new JLabel(String.format("%s:", columnNames[i + 1]));
            generalLabelsConstraints[i] = new GridBagConstraints();

            fields[i] = new JTextField(String.format("Insert %s", columnNames[i + 1]), 10);
            generalFieldsConstraints[i] = new GridBagConstraints();

            generalLabelsConstraints[i].gridx = x;
            generalLabelsConstraints[i].gridy = 0;
            generalLabelsConstraints[i].insets = new Insets(10, 5, 10, 5);

            generalFieldsConstraints[i].gridx = x + 1;
            generalFieldsConstraints[i].gridy = 0;
            generalFieldsConstraints[i].insets = new Insets(10, 5, 10, 5);

            add(labels[i], generalLabelsConstraints[i]);
            add(fields[i], generalFieldsConstraints[i]);
        }

        super.setLabels(labels);
        super.setFields(fields);

        buttonConstraints.gridx = (N) * 2;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(super.getButton(), buttonConstraints);
        pack();
        setVisible(true);
    }

    /**
     * Gets the data contained in
     * {@link views.dialogs.AbstractExamDialog#generalFields} components as a
     * {@link java.lang.String}
     * 
     * @return Data within text fields as an array of strings
     * 
     * @see views.dialogs.AbstractExamDialog#getFieldsData method
     */
    public String[] getFieldsData() {
        String[] fieldsData = new String[super.getGeneralFields().length];

        for (int i = 0; i < super.getGeneralFields().length; i++) {
            fieldsData[i] = super.getGeneralFields()[i].getText();
        }

        return fieldsData;
    }
}
