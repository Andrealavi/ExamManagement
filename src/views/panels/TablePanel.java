/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.panels
 */
package views.panels;

import java.awt.*;
import javax.swing.*;

/**
 * Implements panel containing the exam table.
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
public class TablePanel extends JPanel {
    private JTable table;

    /**
     * Pane in which {@link views.panels.TablePanel#table} is placed in order to
     * have the possibility to scroll entries
     */
    private JScrollPane tablePane;

    /**
     * Instantiates components of the panel, sets layout and adds components to the
     * view
     */
    public TablePanel() {
        table = new JTable();
        tablePane = new JScrollPane(table);

        table.setFillsViewportHeight(true);

        setLayout(new GridBagLayout());

        GridBagConstraints scrollPaneConstraints = new GridBagConstraints();

        scrollPaneConstraints.gridx = 0;
        scrollPaneConstraints.gridy = 0;
        scrollPaneConstraints.fill = GridBagConstraints.HORIZONTAL;

        add(tablePane, scrollPaneConstraints);
        setVisible(true);
    }

    /**
     * Gets {@link javax.swing.JTable} that contains exam entries
     * 
     * @return exam table
     */
    public JTable getTable() {
        return table;
    }
}
