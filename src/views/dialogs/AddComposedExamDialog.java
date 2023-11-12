package views.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddComposedExamDialog extends JDialog {
    public class PartialExamView {
        protected final String[] weights = { "25%", "33%", "50%", "67%", "75%" };
        protected JLabel gradeLabel;
        protected JTextField gradeTextField;
        protected JLabel weightLabel;
        protected JCheckBox honoursBox;

        protected JComboBox weightComboBox;

        protected JButton addPartialButton;
        protected JButton removePartialButton;

        public PartialExamView() {

            gradeLabel = new JLabel("Exam Grade: ");
            gradeTextField = new JTextField("Insert grade", 25);

            weightLabel = new JLabel("Exam Weight: ");
            weightComboBox = new JComboBox<String>(weights);
            weightComboBox.setSelectedItem(2);

            honoursBox = new JCheckBox("Honours");

            addPartialButton = new JButton("+");
            addPartialButton.setVisible(false);

            removePartialButton = new JButton("-");
            removePartialButton.setVisible(false);
        }

        public JButton getAddButton() {
            return addPartialButton;
        }

        public JButton getRemoveButton() {
            return removePartialButton;
        }

        public String getGrade() {
            return gradeTextField.getText();
        }

        public String getWeight() {
            switch ((String) weightComboBox.getSelectedItem()) {
                case "25%":
                    return "0.25";

                case "33%":
                    return "0.33";

                case "50%":
                    return "0.5";

                case "67%":
                    return "0.67";

                case "75%":
                    return "0.75";

                default:
                    break;
            }

            return "";
        }

        public String getHonours() {
            return String.valueOf(honoursBox.isSelected());
        }

        public void addToView(JDialog d, int y) {
            GridBagConstraints gradeLabelConstraints = new GridBagConstraints();
            GridBagConstraints gradeTextFieldConstraints = new GridBagConstraints();
            GridBagConstraints weightLabelConstraints = new GridBagConstraints();
            GridBagConstraints weightBoxConstraints = new GridBagConstraints();
            GridBagConstraints honoursBoxConstraints = new GridBagConstraints();
            GridBagConstraints addButtonConstraints = new GridBagConstraints();
            GridBagConstraints removeButtonConstraints = new GridBagConstraints();

            gradeLabelConstraints.gridx = 0;
            gradeLabelConstraints.gridy = y;
            gradeLabelConstraints.insets = new Insets(10, 10, 10, 10);

            gradeTextFieldConstraints.gridx = 1;
            gradeTextFieldConstraints.gridy = y;
            gradeTextFieldConstraints.insets = new Insets(10, 10, 10, 10);

            honoursBoxConstraints.gridx = 2;
            honoursBoxConstraints.gridy = y;
            honoursBoxConstraints.insets = new Insets(10, 10, 10, 10);

            weightLabelConstraints.gridx = 3;
            weightLabelConstraints.gridy = y;
            weightLabelConstraints.insets = new Insets(10, 10, 10, 10);

            weightBoxConstraints.gridx = 4;
            weightBoxConstraints.gridy = y;
            weightBoxConstraints.insets = new Insets(10, 10, 10, 10);

            addButtonConstraints.gridx = 5;
            addButtonConstraints.gridy = y;
            addButtonConstraints.insets = new Insets(10, 10, 10, 0);

            removeButtonConstraints.gridx = 6;
            removeButtonConstraints.gridy = y;
            removeButtonConstraints.insets = new Insets(10, 0, 10, 10);

            d.add(gradeLabel, gradeLabelConstraints);
            d.add(gradeTextField, gradeTextFieldConstraints);
            d.add(honoursBox, honoursBoxConstraints);
            d.add(weightLabel, weightLabelConstraints);
            d.add(weightComboBox, weightBoxConstraints);
            d.add(addPartialButton, addButtonConstraints);
            d.add(removePartialButton, removeButtonConstraints);

            d.pack();
        }

        public void removeFromView(JDialog d) {
            d.remove(gradeLabel);
            d.remove(gradeTextField);
            d.remove(weightLabel);
            d.remove(weightComboBox);
            d.remove(honoursBox);
            d.remove(addPartialButton);
            d.remove(removePartialButton);

        }
    }

    protected JLabel[] generalInfoLabels;
    protected JTextField[] generalInfoFields;
    protected JButton actionButton;
    protected ArrayList<PartialExamView> partialExams;

    public AddComposedExamDialog(JFrame f) {
        super(f, "Add Composed Exam");

        final String[] COL_NAMES = { "First name", "Last name", "Class", "Credits" };

        generalInfoLabels = new JLabel[COL_NAMES.length];
        generalInfoFields = new JTextField[COL_NAMES.length];
        partialExams = new ArrayList<PartialExamView>();
        actionButton = new JButton("Add");

        setLayout(new GridBagLayout());

        GridBagConstraints[] infoLabelsConstraints = new GridBagConstraints[COL_NAMES.length];
        GridBagConstraints[] infoFieldsConstraints = new GridBagConstraints[COL_NAMES.length];
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        for (int i = 0, x = 0; i < COL_NAMES.length; i++, x += 2) {
            generalInfoLabels[i] = new JLabel(String.format("%s:", COL_NAMES[i]));
            infoLabelsConstraints[i] = new GridBagConstraints();

            generalInfoFields[i] = new JTextField(String.format("Insert %s", COL_NAMES[i]), 25);
            infoFieldsConstraints[i] = new GridBagConstraints();

            infoLabelsConstraints[i].gridx = x;
            infoLabelsConstraints[i].gridy = 0;
            infoLabelsConstraints[i].insets = new Insets(10, 10, 10, 10);

            infoFieldsConstraints[i].gridx = x + 1;
            infoFieldsConstraints[i].gridy = 0;
            infoFieldsConstraints[i].insets = new Insets(10, 10, 10, 10);

            add(generalInfoLabels[i], infoLabelsConstraints[i]);
            add(generalInfoFields[i], infoFieldsConstraints[i]);
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

    public void addPartialExam() {
        if (partialExams.size() > 0) {
            PartialExamView previousPartialExam = partialExams.getLast();

            previousPartialExam.getAddButton().setVisible(false);
            previousPartialExam.getRemoveButton().setVisible(false);
        }

        partialExams.add(new PartialExamView());

        partialExams.getLast().getAddButton().setVisible(true);
        partialExams.getLast().getRemoveButton().setVisible(true);
        partialExams.getLast().addToView(this, partialExams.size());

        pack();
    }

    public void removePartialExam() {
        if (partialExams.size() > 1) {
            PartialExamView lastExam = partialExams.getLast();

            partialExams.removeLast();
            lastExam.removeFromView(this);

            partialExams.getLast().getAddButton().setVisible(true);
            partialExams.getLast().getRemoveButton().setVisible(true);
        }
    }

    public PartialExamView getLastPartialExam() {
        return partialExams.getLast();
    }

    public JButton getButton() {
        return actionButton;
    }

    public String[] getFieldsData() {
        String[] generalFieldsData = new String[generalInfoFields.length];

        for (int i = 0; i < generalInfoFields.length; i++) {
            generalFieldsData[i] = generalInfoFields[i].getText();
        }

        return generalFieldsData;
    }

    public void refreshButton() {
        remove(actionButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = (generalInfoFields.length) * 2;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);
    }

    public Integer getExamNumber() {
        return partialExams.size();
    }

    public String[][] getExamsData() {
        String[][] examsData = new String[partialExams.size()][3];

        for (int i = 0; i < partialExams.size(); i++) {
            examsData[i][0] = partialExams.get(i).getGrade();
            examsData[i][1] = partialExams.get(i).getWeight();
            examsData[i][2] = partialExams.get(i).getHonours();
        }

        return examsData;
    }

}
