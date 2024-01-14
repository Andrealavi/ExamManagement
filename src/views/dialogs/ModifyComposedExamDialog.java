/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Implements a dialog for modifying composed exams to the exams table. It
 * extends
 * {@link views.dialogs.AddComposedExamDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see views.dialogs.AbstractExamDialog
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
public class ModifyComposedExamDialog extends AddComposedExamDialog implements ModifyExamDialogInterface {
    private JButton modifyButton;
    private JButton removeButton;

    /**
     * Calls super constructor, disables all {@link javax.swing.JTextField} and
     * removes partial exams
     * 
     * @param frame Parent frame
     */
    public ModifyComposedExamDialog(JFrame frame) {
        super(frame);

        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setEditable(false);
        }

        modifyButton = new JButton("Modify");
        removeButton = new JButton("Remove");
        actionButton.setText("Close");

        removePartialExam(true);
        removePartialExam(true);
    }

    private String convertWeight(String decimalWeight) {
        Float weight = Float.parseFloat(decimalWeight);
        weight = weight * 100;

        StringBuffer convertedWeight = new StringBuffer(weight.toString()).append("%");

        return convertedWeight.toString();
    }

    /**
     * Sets entry fields using data passed as arguments
     * 
     * @param generalDataArray Data of the general text fields
     * @param partialGrades    Grades of each partial exam
     * @param partialWeights   Weights of each partial exam
     */
    public void setEntryFields(String[] fieldsData) {
        for (int i = 0; i < generalFields.length - 1; i++) {
            generalFields[i].setText(fieldsData[i]);
        }

        generalFields[3].setText(fieldsData[3]);

        String[] partialExamsData = fieldsData[4].split(",");

        for (int i = 0; i < partialExamsData.length; i++) {
            addPartialExam();

            PartialExamView partialExam = partialExams.getLast();

            String[] examData = partialExamsData[i].split(" ");

            partialExam.getGradeField().setText(examData[0]);
            partialExam.getGradeField().setEditable(false);

            partialExam.getWeightField().setText(convertWeight(examData[1]));
            partialExam.getWeightField().setEditable(false);

            refreshModifyButton();
            refreshRemoveButton();
            refreshButton();

            pack();
        }

        partialExams.getLast().getAddButton().setEnabled(false);
        partialExams.getLast().getRemoveButton().setEnabled(false);
    }

    /**
     * Refreshes modify button position in dialog view
     */
    public void refreshModifyButton() {
        remove(modifyButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(modifyButton, buttonConstraints);
    }

    public void refreshRemoveButton() {
        remove(removeButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeButton, buttonConstraints);
    }

    /**
     * Removes partial exams from dialog
     * 
     * @param clear Boolean to check if the last partial exam view has to be removed
     */
    public void removePartialExam(boolean clear) {
        if (partialExams.size() > 1 || clear) {
            PartialExamView lastExam = partialExams.getLast();

            partialExams.removeLast();
            lastExam.removeFromView();
        }
    }

    /**
     * Gets {@link views.dialogs.ModifyComposedExamDialog#modifyButton}
     * 
     * @return Button modify button
     */
    public JButton getModifyButton() {
        return modifyButton;
    }

    public JButton getRemoveButton() {
        return removeButton;
    }

    /**
     * Gets text field components via {@link javax.swing.JTextField} array
     * 
     * @return {@link javax.swing.JTextField} array of dialog general fields
     */
    public JTextField[] getGeneralFields() {
        return generalFields;
    }

    /**
     * Gets partial exams view as an array of
     * {@link views.dialogs.AddComposedExamDialog.PartialExamView}
     * 
     * @return array of {@link views.dialogs.AddComposedExamDialog.PartialExamView}
     *         containg partial exams views
     */
    public PartialExamView[] getPartialExams() {
        PartialExamView[] partialExamsArray = new PartialExamView[partialExams.size()];

        for (int i = 0; i < partialExams.size(); i++) {
            partialExamsArray[i] = partialExams.get(i);
        }

        return partialExamsArray;
    }
}
