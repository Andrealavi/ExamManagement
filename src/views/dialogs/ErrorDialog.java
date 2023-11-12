package views.dialogs;

import java.awt.*;
import javax.swing.*;

public class ErrorDialog extends JDialog {
    private JTextArea errorMessageArea;
    private JButton closeButton;

    public ErrorDialog(JDialog d, String errorMessage) {
        super(d, "Error Message");

        errorMessageArea = new JTextArea(errorMessage);
        errorMessageArea.setEditable(false);

        closeButton = new JButton("Close");

        setLayout(new GridBagLayout());

        GridBagConstraints textAreaConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();

        textAreaConstraints.gridx = 0;
        textAreaConstraints.gridy = 0;
        textAreaConstraints.fill = GridBagConstraints.HORIZONTAL;
        textAreaConstraints.insets = new Insets(10, 10, 10, 10);

        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = 1;
        buttonConstraints.insets = new Insets(10, 10, 10, 10);

        add(errorMessageArea, textAreaConstraints);
        add(closeButton, buttonConstraints);

        pack();
        setVisible(true);
    }

    public JButton getButton() {
        return closeButton;
    }
}
