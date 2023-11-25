/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Implements a dialog for removing exams from the exam table. It extends
 * {@link javax.swing.JDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
public class RemoveEntryDialog extends JDialog {
    private JLabel removeExprLabel;
    private JTextField removeExprField;
    private JButton infoButton;
    private JButton actionButton;

    /**
     * Instantiates components of the dialog, sets layout and adds components to the
     * view
     * 
     * @param frame Parent frame
     */
    public RemoveEntryDialog(JFrame frame) {
        super(frame, "Remove entry/entries");

        removeExprLabel = new JLabel("Remove Expression:");
        removeExprField = new JTextField(25);
        infoButton = new JButton("?");
        actionButton = new JButton("Remove");

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        GridBagConstraints infoButtonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeExprLabel, labelConstraints);

        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeExprField, fieldConstraints);

        infoButtonConstraints.gridx = 2;
        infoButtonConstraints.gridy = 0;
        infoButtonConstraints.insets = new Insets(10, 10, 10, 10);

        add(infoButton, infoButtonConstraints);

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);

        setVisible(true);
        pack();
    }

    /**
     * Gets dialog action button
     * 
     * @return Dialog button
     */
    public JButton getButton() {
        return actionButton;
    }

    /**
     * Gets information button
     * 
     * @return {@link javax.swing.JButton} for getting info about filter panel
     */
    public JButton getInfoButton() {
        return infoButton;
    }

    /**
     * Gets {@link javax.swing.JTextField} that contains the remove expression
     * 
     * @return Text field containing remove expression
     */
    public JTextField getField() {
        return removeExprField;
    }
}
