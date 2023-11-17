package views.panels;

import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
    private JLabel gradeLabel;
    private JTextField gradeField;
    private JButton clearFilterButton;
    private JButton showStatsButton;

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

    public JTextField getGradeField() {
        return gradeField;
    }

    public JButton getClearFilterButton() {
        return clearFilterButton;
    }

    public JButton getShowStatsButton() {
        return showStatsButton;
    }
}