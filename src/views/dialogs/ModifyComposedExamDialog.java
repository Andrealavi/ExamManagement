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
    /**
     * Button that activates the modify mode
     */
    private JButton modifyButton;

    /**
     * Button that activates the entry removal
     */
    private JButton removeButton;

    /**
     * Calls super constructor, disables all {@link javax.swing.JTextField} and
     * removes partial exams
     * 
     * @param frame Parent frame
     */
    public ModifyComposedExamDialog(JFrame frame) {
        super(frame);

        for (int i = 0; i < super.getGeneralFields().length; i++) {
            super.getGeneralFields()[i].setEditable(false);
        }

        modifyButton = new JButton("Modify");
        removeButton = new JButton("Remove");
        super.getButton().setText("Close");

        removePartialExam(true);
        removePartialExam(true);
    }

    /**
     * Converts weight into a prettier format with %
     * 
     * @param decimalWeight Partial exam weight
     * @return
     */
    private String convertWeight(String decimalWeight) {
        Float weight = Float.parseFloat(decimalWeight);
        weight = weight * 100;

        StringBuffer convertedWeight = new StringBuffer(weight.toString()).append("%");

        return convertedWeight.toString();
    }

    @Override
    public void setEntryFields(String[] fieldsData) {
        for (int i = 0; i < super.getGeneralFields().length - 1; i++) {
            super.getGeneralFields()[i].setText(fieldsData[i]);
        }

        super.getGeneralFields()[3].setText(fieldsData[3]);

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
     * Refreshes {@link views.dialogs.ModifyComposedExamDialog#modifyButton}
     * position in dialog view
     */
    public void refreshModifyButton() {
        remove(modifyButton);

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = partialExams.size() + 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(modifyButton, buttonConstraints);
    }

    /**
     * Refreshes {@link views.dialogs.ModifyComposedExamDialog#removeButton}
     * position in dialog view
     */
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

    @Override
    public JButton getModifyButton() {
        return modifyButton;
    }

    @Override
    public JButton getRemoveButton() {
        return removeButton;
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
