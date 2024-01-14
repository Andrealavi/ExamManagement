/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Implements a dialog for adding composed exams to the exams table. It extends
 * {@link views.dialogs.AbstractExamDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see views.dialogs.AbstractExamDialog
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
public class AddComposedExamDialog extends AbstractExamDialog {

    /**
     * Used to display partial exam data
     */
    public class PartialExamView {
        protected JLabel gradeLabel;
        protected JTextField gradeTextField;
        protected JLabel weightLabel;
        protected JTextField weightField;

        protected JButton addPartialButton;
        protected JButton removePartialButton;

        /**
         * Instantiates class attributes
         */
        public PartialExamView() {
            gradeLabel = new JLabel("Exam Grade: ");
            gradeTextField = new JTextField("Insert grade", 25);

            weightLabel = new JLabel("Exam Weight: ");
            weightField = new JTextField("Insert weight", 10);

            addPartialButton = new JButton("+");
            addPartialButton.setVisible(false);

            removePartialButton = new JButton("-");
            removePartialButton.setVisible(false);
        }

        /**
         * Gets
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#addPartialButton}
         * 
         * @return Add partial exam button
         */
        public JButton getAddButton() {
            return addPartialButton;
        }

        /**
         * Gets
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#removePartialButton}
         * 
         * @return Remove partial exam button
         */
        public JButton getRemoveButton() {
            return removePartialButton;
        }

        /**
         * Gets text present in
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#gradeTextField}
         * 
         * @return Text containted in partial exam grade field
         */
        public String getGrade() {
            return gradeTextField.getText();
        }

        /**
         * Gets the weight selected in the
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#weightComboBox}
         * and coverts it into a {@link java.lang.String} which could be properly
         * converted into a
         * {@link java.lang.Float}
         * 
         * @return Weight string in a float parsable format
         */
        public String getWeight() {
            String percWeight = weightField.getText().replace("%", "");

            Float weight = Float.parseFloat(percWeight);
            weight = weight / 100;

            return weight.toString();
        }

        /**
         * Gets
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#weightComboBox}
         * 
         * @return Weights {@link javax.swing.JComboBox}
         */
        public JTextField getWeightField() {
            return weightField;
        }

        /**
         * Gets
         * {@link views.dialogs.AddComposedExamDialog.PartialExamView#gradeTextField}
         * 
         * @return Text field for partial exam grade
         */
        public JTextField getGradeField() {
            return gradeTextField;
        }

        /**
         * Adds partial exam to the dialog view
         * 
         * @param y coordinate for exam view position in dialog
         *          {@link java.awt.GridBagLayout}
         */
        public void addToView(int y) {
            GridBagConstraints gradeLabelConstraints = new GridBagConstraints();
            GridBagConstraints gradeTextFieldConstraints = new GridBagConstraints();
            GridBagConstraints weightLabelConstraints = new GridBagConstraints();
            GridBagConstraints weightFieldConstraints = new GridBagConstraints();
            GridBagConstraints addButtonConstraints = new GridBagConstraints();
            GridBagConstraints removeButtonConstraints = new GridBagConstraints();

            gradeLabelConstraints.gridx = 0;
            gradeLabelConstraints.gridy = y;
            gradeLabelConstraints.insets = new Insets(10, 10, 10, 10);

            gradeTextFieldConstraints.gridx = 1;
            gradeTextFieldConstraints.gridy = y;
            gradeTextFieldConstraints.insets = new Insets(10, 10, 10, 10);

            weightLabelConstraints.gridx = 2;
            weightLabelConstraints.gridy = y;
            weightLabelConstraints.insets = new Insets(10, 10, 10, 10);

            weightFieldConstraints.gridx = 3;
            weightFieldConstraints.gridy = y;
            weightFieldConstraints.insets = new Insets(10, 10, 10, 10);

            addButtonConstraints.gridx = 4;
            addButtonConstraints.gridy = y;
            addButtonConstraints.insets = new Insets(10, 10, 10, 0);

            removeButtonConstraints.gridx = 5;
            removeButtonConstraints.gridy = y;
            removeButtonConstraints.insets = new Insets(10, 0, 10, 10);

            AddComposedExamDialog.this.add(gradeLabel, gradeLabelConstraints);
            AddComposedExamDialog.this.add(gradeTextField, gradeTextFieldConstraints);
            AddComposedExamDialog.this.add(weightLabel, weightLabelConstraints);
            AddComposedExamDialog.this.add(weightField, weightFieldConstraints);
            AddComposedExamDialog.this.add(addPartialButton, addButtonConstraints);
            AddComposedExamDialog.this.add(removePartialButton, removeButtonConstraints);

            AddComposedExamDialog.this.pack();
        }

        /**
         * Removes partial exam from dialog view
         */
        public void removeFromView() {
            AddComposedExamDialog.this.remove(gradeLabel);
            AddComposedExamDialog.this.remove(gradeTextField);
            AddComposedExamDialog.this.remove(weightLabel);
            AddComposedExamDialog.this.remove(weightField);
            AddComposedExamDialog.this.remove(addPartialButton);
            AddComposedExamDialog.this.remove(removePartialButton);
        }
    }

    /**
     * List containg all partial exams
     */
    protected ArrayList<PartialExamView> partialExams;

    /**
     * Instantiates all dialog components and sets the dialog layout. It also
     * creates and adds two partial exams to the view.
     * 
     * @param frame Parent frame
     */
    public AddComposedExamDialog(JFrame frame) {
        super(frame, "Add Composed Exam");

        final String[] COL_NAMES = { "First name", "Last name", "Class", "Credits" };

        generalLabels = new JLabel[COL_NAMES.length];
        generalFields = new JTextField[COL_NAMES.length];
        partialExams = new ArrayList<PartialExamView>();
        actionButton = new JButton("Add");

        setLayout(new GridBagLayout());

        GridBagConstraints[] infoLabelsConstraints = new GridBagConstraints[COL_NAMES.length];
        GridBagConstraints[] infoFieldsConstraints = new GridBagConstraints[COL_NAMES.length];
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        for (int i = 0, x = 0; i < COL_NAMES.length; i++, x += 2) {
            generalLabels[i] = new JLabel(String.format("%s:", COL_NAMES[i]));
            infoLabelsConstraints[i] = new GridBagConstraints();

            generalFields[i] = new JTextField(String.format("Insert %s", COL_NAMES[i]), 25);
            infoFieldsConstraints[i] = new GridBagConstraints();

            infoLabelsConstraints[i].gridx = x;
            infoLabelsConstraints[i].gridy = 0;
            infoLabelsConstraints[i].insets = new Insets(10, 10, 10, 10);

            infoFieldsConstraints[i].gridx = x + 1;
            infoFieldsConstraints[i].gridy = 0;
            infoFieldsConstraints[i].insets = new Insets(10, 10, 10, 10);

            add(generalLabels[i], infoLabelsConstraints[i]);
            add(generalFields[i], infoFieldsConstraints[i]);
        }

        addPartialExam();
        addPartialExam();

        buttonConstraints.gridx = (COL_NAMES.length) * 2;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);

        pack();
        setVisible(true);
    }

    /**
     * Adds a single partial exam to
     * {@link views.dialogs.AddComposedExamDialog#partialExams} list and adds the
     * partial exam to the view.
     */
    public void addPartialExam() {
        if (partialExams.size() > 0) {
            PartialExamView previousPartialExam = partialExams.getLast();

            previousPartialExam.getAddButton().setVisible(false);
            previousPartialExam.getRemoveButton().setVisible(false);
        }

        partialExams.add(new PartialExamView());

        partialExams.getLast().getAddButton().setVisible(true);
        partialExams.getLast().getRemoveButton().setVisible(true);
        partialExams.getLast().addToView(partialExams.size());
    }

    /**
     * Removes partial exam from
     * {@link views.dialogs.AddComposedExamDialog#partialExams} list and from dialog
     * view
     */
    public void removePartialExam() {
        if (partialExams.size() > 1) {
            PartialExamView lastExam = partialExams.getLast();

            partialExams.removeLast();
            lastExam.removeFromView();

            partialExams.getLast().getAddButton().setVisible(true);
            partialExams.getLast().getRemoveButton().setVisible(true);
        }
    }

    /**
     * Gets the last partial exam added to
     * {@link views.dialogs.AddComposedExamDialog#partialExams} list
     * 
     * @return partial exam view object
     */
    public PartialExamView getLastPartialExam() {
        return partialExams.getLast();
    }

    /**
     * Gets dialog action button
     * 
     * @return Button component
     */
    public JButton getButton() {
        return actionButton;
    }

    /**
     * Gets the data contained in
     * {@link views.dialogs.AbstractExamDialog#generalFields} components as a
     * {@link java.lang.String} array
     * 
     * @return Data within text fields as an array of {@link java.lang.String}
     */
    public String[] getFieldsData() {
        String[] fieldsData = new String[generalFields.length + 1];

        for (int i = 0; i < generalFields.length; i++) {
            fieldsData[i] = generalFields[i].getText();
        }

        fieldsData[fieldsData.length - 1] = getExamsData();

        return fieldsData;
    }

    /**
     * Refreshes {@link views.dialogs.AddComposedExamDialog#actionButton} in dialog
     * view.
     */
    public void refreshButton() {
        remove(actionButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = (generalFields.length) * 2;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);
    }

    /**
     * Gets the number of partial exams
     * 
     * @return {@link views.dialogs.AddComposedExamDialog#partialExams} size
     */
    public Integer getExamNumber() {
        return partialExams.size();
    }

    /**
     * Gets grades and weights of any partial exams as a matrix of
     * {@link java.lang.String}
     * 
     * @return A matrix of {@link java.lang.String} with data
     */
    public String getExamsData() {
        StringBuffer examsData = new StringBuffer();

        for (int i = 0; i < partialExams.size(); i++) {
            examsData.append(String.format("%s %s,", partialExams.get(i).getGrade(), partialExams.get(i).getWeight()));
        }

        return examsData.toString();
    }

}
