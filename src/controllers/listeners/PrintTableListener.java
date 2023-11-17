package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

public class PrintTableListener implements ActionListener {
    private JTable table;

    public PrintTableListener(JTable table) {
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (!table.print()) {
                System.err.println("User cancelled printing");
            }
        } catch (java.awt.print.PrinterException err) {
            System.err.format("Cannot print %s%n", err.getMessage());
        }
    }
}
