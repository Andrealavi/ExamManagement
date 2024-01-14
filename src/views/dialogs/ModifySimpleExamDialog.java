/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.JButton;
import javax.swing.JFrame;

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
public class ModifySimpleExamDialog extends AddSimpleExamDialog implements ModifyExamDialogInterface {
    /**
     * Button that activates the modify mode
     */
    private JButton modifyButton;

    /**
     * Button that activates the entry removal
     */
    private JButton removeButton;

    /**
     * Instantiates class attributes using all the function arguments. Disables text
     * fields, add {@link views.dialogs.ModifySimpleExamDialog#modifyButton} and
     * {@link views.dialogs.ModifySimpleExamDialog#removeButton} and changes
     * action button text to close
     * 
     * @param frame       Application frame
     * @param columnNames Array containing the columns names
     */
    public ModifySimpleExamDialog(JFrame frame, String[] columnNames) {
        super(frame, columnNames);

        for (int i = 0; i < super.getGeneralFields().length; i++) {
            super.getGeneralFields()[i].setEditable(false);
        }

        modifyButton = new JButton("Modify");
        removeButton = new JButton("Remove");
        super.getButton().setText("Close");

        GridBagConstraints buttonConstraints = new GridBagConstraints();

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(modifyButton, buttonConstraints);

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeButton, buttonConstraints);
    }

    @Override
    public void setEntryFields(String[] fieldsData) {
        for (int i = 0; i < super.getGeneralFields().length; i++) {
            super.getGeneralFields()[i].setText(fieldsData[i]);
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
}
