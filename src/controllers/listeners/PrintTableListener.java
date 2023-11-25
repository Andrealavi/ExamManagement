/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners
 */
package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for print button of the application menu
 * 
 * @see javax.swing.JFrame
 * @see javax.swing.JTable
 * @see java.awt.event.ActionListener
 */
public class PrintTableListener implements ActionListener {
    /**
     * Application frame
     */
    private JFrame frame;

    /**
     * Exams table
     */
    private JTable table;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param frame Application frame
     * @param table Exams table
     */
    public PrintTableListener(JFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    /**
     * Calls the {@link javax.swing.JTable#print} method. It also handles cases in
     * which the table is empty or the print process ends with errors
     */
    @Override
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
