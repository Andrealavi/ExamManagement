package views;

import javax.swing.*;
import java.awt.*;

import views.panels.*;

public class AppFrame extends JFrame {
    private TopMenu menuBar;
    private JPanel mainPanel;
    private String panelType;

    public AppFrame(String title) {
        setTitle(title);

        menuBar = new TopMenu();
        mainPanel = new TablePanel();
        panelType = "examTable";

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
        add(mainPanel, panelConstraints);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AppFrame() {
        this("");
    }

    public String getPanelType() {
        return panelType;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void refreshFrame() {
        pack();
    }
}