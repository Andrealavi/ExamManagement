package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class FilterDialog extends JDialog {
    private JLabel filterLabel;
    private JTextField filterField;
    private JButton actionButton;

    public FilterDialog(JFrame frame) {
        super(frame, "Create Filter");

        filterLabel = new JLabel("Insert filter:");
        filterField = new JTextField(25);
        actionButton = new JButton("Apply filter");

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;

        add(filterLabel, labelConstraints);
        add(filterField, fieldConstraints);
        add(actionButton, buttonConstraints);

        pack();
        setVisible(true);
    }

    public JButton getButton() {
        return actionButton;
    }

    public JTextField getFilterField() {
        return filterField;
    }
}
