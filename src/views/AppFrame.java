package views;

import javax.swing.*;
import java.awt.*;

import views.panels.*;

public class AppFrame extends JFrame {
    private TopMenu menuBar;
    private JPanel tablePanel;
    private FilterPanel filterPanel;
    private JFileChooser fileChooser;

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
        panelConstraints.fill = GridBagConstraints.HORIZONTAL;

        add(menuBar, menuConstraints);
        add(tablePanel, panelConstraints);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AppFrame() {
        this("");
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public TopMenu getTopMenu() {
        return menuBar;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public FilterPanel getFilterPanel() {
        return filterPanel;
    }

    public void addFilterPanel() {
        GridBagConstraints filterPanelConstraints = new GridBagConstraints();

        filterPanelConstraints.gridx = 0;
        filterPanelConstraints.gridy = 2;
        filterPanelConstraints.fill = GridBagConstraints.HORIZONTAL;

        add(filterPanel, filterPanelConstraints);

        pack();
    }

    public void removeFilterPanel() {
        remove(filterPanel);

        pack();
    }
}