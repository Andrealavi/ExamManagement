/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.*;

/**
 * Implements a dialog for modifying simple exams to the exams table. It
 * extends
 * {@link views.dialogs.AddComposedExamDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see views.dialogs.AbstractExamDialog
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
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

    /**
     * Sets entry fields using data passed as argument
     * 
     * @param fieldsData fields data passed as a {@link java.lang.String} array
     */
    public void setEntryFields(String[] fieldsData) {
        for (int i = 0; i < generalFields.length; i++) {
            generalFields[i].setText(fieldsData[i]);
        }
    }

    /**
     * Gets {@link views.dialogs.ModifySimpleExamDialog#modifyButton}
     * 
     * @return Button modify button
     */
    public JButton getModifyButton() {
        return modifyButton;
    }

    /**
     * Gets text field components via {@link javax.swing.JTextField} array
     * 
     * @return {@link javax.swing.JTextField} array of dialog general fields
     */
    public JTextField[] getGeneralFields() {
        return generalFields;
    }
}
