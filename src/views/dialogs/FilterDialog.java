/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;
import java.awt.*;

/**
 * Implements a dialog to set a filter on exams table. It extends
 * {@link javax.swing.JDialog} and uses
 * {@link java.awt.GridBagLayout} for organizing components
 * 
 * @see javax.swing.JDialog
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 */
public class FilterDialog extends JDialog {
    /**
     * Filter Label
     */
    private JLabel filterLabel;

    /**
     * Text field containg the filter expression
     */
    private JTextField filterField;

    /**
     * Button to get information about filter
     */
    private JButton infoButton;

    /**
     * Button that activates the action
     */
    private JButton actionButton;

    /**
     * Instantiates components of the dialog, sets layout and adds components to the
     * view
     * 
     * @param frame Parent frame
     */
    public FilterDialog(JFrame frame) {
        super(frame, "Create Filter");

        filterLabel = new JLabel("Insert filter:");
        filterField = new JTextField(25);
        infoButton = new JButton("?");
        actionButton = new JButton("Apply filter");

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        GridBagConstraints infoButtonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        add(filterLabel, labelConstraints);

        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);

        add(filterField, fieldConstraints);

        infoButtonConstraints.gridx = 2;
        infoButtonConstraints.gridy = 0;
        infoButtonConstraints.insets = new Insets(10, 10, 10, 10);

        add(infoButton, infoButtonConstraints);

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;

        add(actionButton, buttonConstraints);

        pack();
        setVisible(true);
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
     * Gets filter field component
     * 
     * @return {@link javax.swing.JTextField} containg filter string
     */
    public JTextField getFilterField() {
        return filterField;
    }

    /**
     * Gets information button
     * 
     * @return {@link javax.swing.JButton} for getting info about filter panel
     */
    public JButton getInfoButton() {
        return infoButton;
    }
}
