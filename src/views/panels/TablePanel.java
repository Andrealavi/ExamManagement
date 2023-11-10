package views.panels;

import java.awt.*;
import javax.swing.*;

public class TablePanel extends JPanel {
    private JTable table;
    private JScrollPane tablePane;

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

    public JTable getTable() {
        return table;
    }
}
