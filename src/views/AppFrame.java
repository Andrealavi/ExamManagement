/**
 * @author Andrea Lavino (176195)
 * 
 * @package views
 */
package views;

import javax.swing.*;
import java.awt.*;

import views.panels.*;

/**
 * 
 * Implements application frame.
 * 
 * Extends {@link JFrame} in order to create a
 * custom
 * application frame. It uses {@link java.awt.GridBagLayout} to organize
 * components.
 * 
 * @see views.TopMenu
 * @see views.panels.TablePanel
 * @see views.panels.FilterPanel
 * @see java.awt.GridBagLayout
 * @see java.awt.GridBagConstraints
 * @see javax.swing.JFileChooser
 * 
 */
public class AppFrame extends JFrame {
    /**
     * Application menu. It has two menus: file and exam
     */
    private TopMenu menuBar;

    /**
     * Panel that contains a {@link javax.swing.JTable} with exam entries
     */
    private TablePanel tablePanel;

    /**
     * Panel that contains filter info
     */
    private FilterPanel filterPanel;

    /**
     * Component for file selection
     */
    private JFileChooser fileChooser;

    /**
     * Instantiates attributes and organizes the component using
     * {@link java.awt.GridBagLayout}
     * 
     * @param title Frame title
     */
    public AppFrame(String title) {
        setTitle(title);

        menuBar = new TopMenu();
        tablePanel = new TablePanel();
        filterPanel = new FilterPanel();
        fileChooser = new JFileChooser();

        setLayout(new GridBagLayout());

        GridBagConstraints menuConstraints = new GridBagConstraints();
        GridBagConstraints panelConstraints = new GridBagConstraints();

        menuConstraints.gridx = 0;
        menuConstraints.gridy = 0;
        menuConstraints.fill = GridBagConstraints.HORIZONTAL;

        panelConstraints.gridx = 0;
        panelConstraints.gridy = 1;
        panelConstraints.fill = GridBagConstraints.BOTH;

        panelConstraints.weightx = 1.0;
        panelConstraints.weighty = 1.0;

        add(menuBar, menuConstraints);
        add(tablePanel, panelConstraints);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Gets {@link views.AppFrame#tablePanel} attribute
     * 
     * @return Component that contains the exams table
     */
    public TablePanel getTablePanel() {
        return tablePanel;
    }

    /**
     * Gets {@link views.AppFrame#menuBar} attribute
     * 
     * @return Application menu
     */
    public TopMenu getTopMenu() {
        return menuBar;
    }

    /**
     * Gets {@link views.AppFrame#fileChooser} attribute
     * 
     * @return {@link javax.swing.JFileChooser} component for selecting files
     */
    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    /**
     * Gets {@link views.AppFrame#filterPanel} attribute
     * 
     * @return Panel with filter info
     */
    public FilterPanel getFilterPanel() {
        return filterPanel;
    }

    /**
     * Adds {@link views.AppFrame#filterPanel} to the frame using
     * {@link java.awt.GridBagLayout} layout
     */
    public void addFilterPanel() {
        GridBagConstraints filterPanelConstraints = new GridBagConstraints();

        filterPanelConstraints.gridx = 0;
        filterPanelConstraints.gridy = 2;
        filterPanelConstraints.fill = GridBagConstraints.HORIZONTAL;

        add(filterPanel, filterPanelConstraints);

        pack();
    }

    /**
     * Removes {@link views.AppFrame#filterPanel} from the frame.
     * 
     */
    public void removeFilterPanel() {
        remove(filterPanel);

        pack();
    }
}