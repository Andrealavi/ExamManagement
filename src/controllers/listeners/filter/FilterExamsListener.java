/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import views.AppFrame;
import views.dialogs.FilterDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for filter button of application menu.
 * 
 * @see views.AppFrame
 * @see views.panels.FilterPanel
 * @see java.awt.event.ActionListener
 */
public class FilterExamsListener implements ActionListener {
    /**
     * Application frame
     */
    private AppFrame frame;

    /**
     * Exam table
     */
    private JTable table;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame      Application frame
     * @param table      Exam table
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public FilterExamsListener(AppFrame frame, JTable table, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isFiltered = isFiltered;
    }

    /**
     * Creates a new {@link views.dialogs.FilterDialog} and sets action listeners
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        FilterDialog dialog = new FilterDialog(frame);

        dialog.getButton().addActionListener(new FilterDialogListener(dialog, table, isFiltered));
        dialog.getInfoButton().addActionListener(new FilterInfoButtonListener(dialog));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }

}
