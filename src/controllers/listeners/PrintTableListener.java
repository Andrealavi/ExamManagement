package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

public class PrintTableListener implements ActionListener {
    private JFrame frame;
    private JTable table;

    public PrintTableListener(JFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        if (table.getModel().getRowCount() == 0) {
            JOptionPane.showMessageDialog(frame, "The table is empty", "Error message", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                if (!table.print()) {
                    JOptionPane.showMessageDialog(frame, "User cancelled printing");
                }
            } catch (java.awt.print.PrinterException err) {
                JOptionPane.showMessageDialog(frame, String.format("Cannot print %s%n", err.getMessage()),
                        "Interrupted Print", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
