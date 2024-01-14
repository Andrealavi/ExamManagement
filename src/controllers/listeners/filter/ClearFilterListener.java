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

import models.ExamsRowSorter;
import views.AppFrame;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for clear filter button of {@link views.panels.FilterPanel}.
 * 
 * @see views.AppFrame
 * @see models.ExamsRowSorter
 * @see java.awt.event.ActionListener
 */
public class ClearFilterListener implements ActionListener {
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
    public ClearFilterListener(AppFrame frame, JTable table, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isFiltered = isFiltered;
    }

    /**
     * Removes filter from the table model and removes
     * {@link views.panels.FilterPanel} from the application frame. It also manages
     * to update row indices.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExamsRowSorter rs = (ExamsRowSorter) table.getRowSorter();

        rs.removeRowFilter();

        for (int i = 0; i < rs.getModelRowCount(); i++) {
            rs.getModel().updateRowNumber(i, i);
        }

        frame.removeFilterPanel();

        isFiltered.set(false);
    }
}
