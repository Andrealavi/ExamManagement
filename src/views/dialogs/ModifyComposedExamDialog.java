package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class ModifyComposedExamDialog extends AddComposedExamDialog {
    private JButton modifyButton;

    public ModifyComposedExamDialog(JFrame f) {
        super(f);

        for (int i = 0; i < generalInfoFields.length; i++) {
            generalInfoFields[i].setEditable(false);
        }

        modifyButton = new JButton("Modify");

        removePartialExam(true);
        removePartialExam(true);
    }

    public void setEntryFields(String[] generalDataArray, String[] partialGrades, String[] partialWeights) {
        for (int i = 0; i < generalInfoFields.length - 1; i++) {
            generalInfoFields[i].setText(generalDataArray[i]);
        }

        generalInfoFields[3].setText(generalDataArray[4]);

        for (int i = 0; i < partialGrades.length; i++) {
            addPartialExam();

            PartialExamView partialExam = partialExams.getLast();

            partialExam.getGradeField().setText(partialGrades[i]);
            partialExam.getGradeField().setEditable(false);

            JComboBox<String> partialExamComboBox = partialExam.getWeightBox();
            partialExamComboBox.setEnabled(false);

            switch (partialWeights[i]) {
                case "0.25":
                    partialExamComboBox.setSelectedIndex(0);
                    break;

                case "0.33":
                    partialExamComboBox.setSelectedIndex(1);
                    break;

                case "0.5":
                    partialExamComboBox.setSelectedIndex(2);
                    break;

                case "0.67":
                    partialExamComboBox.setSelectedIndex(3);
                    break;

                case "0.75":
                    partialExamComboBox.setSelectedIndex(4);
                    break;

                default:
                    break;
            }

            refreshModifyButton();
            refreshButton();

            pack();
        }

        partialExams.getLast().getAddButton().setEnabled(false);
        partialExams.getLast().getRemoveButton().setEnabled(false);
    }

    public void refreshModifyButton() {
        remove(modifyButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(modifyButton, buttonConstraints);
    }

    public void removePartialExam(boolean clear) {
        if (partialExams.size() > 1 || clear) {
            PartialExamView lastExam = partialExams.getLast();

            partialExams.removeLast();
            lastExam.removeFromView(this);
        }
    }

    public JButton getModifyButton() {
        return modifyButton;
    }

    public JTextField[] getGeneralTextFields() {
        return generalInfoFields;
    }

    public PartialExamView[] getPartialExams() {
        PartialExamView[] partialExamsArray = new PartialExamView[partialExams.size()];

        for (int i = 0; i < partialExams.size(); i++) {
            partialExamsArray[i] = partialExams.get(i);
        }

        return partialExamsArray;
    }
}
