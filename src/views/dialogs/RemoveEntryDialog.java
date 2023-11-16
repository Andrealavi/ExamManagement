package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class RemoveEntryDialog extends JDialog {
    private JLabel removeExprLabel;
    private JTextField removeExprField;
    private JButton actionButton;

    public RemoveEntryDialog(JFrame f) {
        super(f, "Remove entry/entries");

        removeExprLabel = new JLabel("Remove Expression:");
        removeExprField = new JTextField(25);
        actionButton = new JButton("Remove");

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeExprLabel, labelConstraints);

        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);

        add(removeExprField, fieldConstraints);

        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(actionButton, buttonConstraints);

        setVisible(true);
        pack();
    }

    public JButton getButton() {
        return actionButton;
    }

    public JTextField getField() {
        return removeExprField;
    }
}
