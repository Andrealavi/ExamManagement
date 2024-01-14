/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.panels
 */
package views.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Implements filter panel.
 * 
 * Extends {@link JPanel} in order to create a
 * custom panel useful to display weighted average of filtered exams and
 * buttons. It uses {@link java.awt.GridBagLayout} to organize
 * components.
 * 
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 * @see javax.swing.JPanel
 * @see javax.swing.JFileChooser
 */
public class FilterPanel extends JPanel {
    /**
     * Weighted average label
     */
    private JLabel gradeLabel;

    /**
     * Weighted average field
     */
    private JTextField gradeField;

    /**
     * Button that activate the filter removal
     */
    private JButton clearFilterButton;

    /**
     * Button that activates the display of the histogram
     */
    private JButton showStatsButton;

    /**
     * Instantiates components of the panel, sets layout and adds components to the
     * view
     */
    public FilterPanel() {
        gradeLabel = new JLabel("Weighted Average:");
        gradeField = new JTextField(20);
        clearFilterButton = new JButton("Clear Filter");
        showStatsButton = new JButton("Show Stats");

        gradeField.setEditable(false);

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints clearFilterButtonConstraints = new GridBagConstraints();
        GridBagConstraints showStatsButtonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);

        clearFilterButtonConstraints.gridx = 0;
        clearFilterButtonConstraints.gridy = 1;
        clearFilterButtonConstraints.insets = new Insets(10, 10, 10, 10);

        showStatsButtonConstraints.gridx = 1;
        showStatsButtonConstraints.gridy = 1;
        showStatsButtonConstraints.insets = new Insets(10, 10, 10, 10);

        add(gradeLabel, labelConstraints);
        add(gradeField, fieldConstraints);
        add(clearFilterButton, clearFilterButtonConstraints);
        add(showStatsButton, showStatsButtonConstraints);

        setVisible(true);
    }

    /**
     * Gets {@link javax.swing.JTextField} containg grades weighted average
     * 
     * @return text field containg grade
     */
    public JTextField getGradeField() {
        return gradeField;
    }

    /**
     * Gets {@link views.panels.FilterPanel#clearFilterButton} that removes filter
     * from the table
     * 
     * @return clear filter button
     */
    public JButton getClearFilterButton() {
        return clearFilterButton;
    }

    /**
     * Gets {@link views.panels.FilterPanel#showStatsButton} that shows the
     * histogram of grades frequency
     * 
     * @return show statistics button
     */
    public JButton getShowStatsButton() {
        return showStatsButton;
    }
}