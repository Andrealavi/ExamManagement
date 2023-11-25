/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.remove
 */
package controllers.listeners.remove;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;
import javax.swing.JTable;

import views.dialogs.RemoveEntryDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for remove entries button of the application menu.
 * 
 * @see javax.swing.JTable
 * @see java.awt.event.ActionListener
 */
public class RemoveEntryListener implements ActionListener {
    /**
     * Application frame
     */
    private JFrame frame;

    /**
     * Exam table
     */
    private JTable table;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame      Application frame
     * @param table      Exam table
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public RemoveEntryListener(JFrame frame, JTable table, AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Creates a {@link views.dialogs.RemoveEntryDialog} and sets its action
     * listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        RemoveEntryDialog dialog = new RemoveEntryDialog(frame);

        dialog.getButton().addActionListener(new RemoveEntryDialogListener(dialog, table, isSaved, isFiltered));

        dialog.getInfoButton().addActionListener(new RemoveInfoButtonListener(dialog));

        dialog.pack();

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
