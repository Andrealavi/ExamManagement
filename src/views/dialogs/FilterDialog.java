package views.dialogs;

import javax.swing.*;
import java.awt.*;

public class FilterDialog extends JDialog {
    private JLabel filterLabel;
    private JTextField filterField;
    private JButton infoButton;
    private JButton actionButton;

    public FilterDialog(JFrame frame) {
        super(frame, "Create Filter");

        filterLabel = new JLabel("Insert filter:");
        filterField = new JTextField(25);
        infoButton = new JButton("?");
        actionButton = new JButton("Apply filter");
        

        setLayout(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        GridBagConstraints fieldConstraints = new GridBagConstraints();
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        GridBagConstraints infoButtonConstraints = new GridBagConstraints();

        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.insets = new Insets(10, 10, 10, 10);
        
        add(filterLabel, labelConstraints);
        
        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = 0;
        fieldConstraints.insets = new Insets(10, 10, 10, 10);
        
        add(filterField, fieldConstraints);

        infoButtonConstraints.gridx = 2;
        infoButtonConstraints.gridy = 0;
        infoButtonConstraints.insets = new Insets(10,10,10,10);
        
        add(infoButton, infoButtonConstraints);
        
        buttonConstraints.gridx = 1;
        buttonConstraints.gridy = 1;
        
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
    
    public JButton getInfoButton() {
    	return infoButton;
    }
}
