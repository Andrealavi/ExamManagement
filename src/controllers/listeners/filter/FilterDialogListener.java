/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;

import controllers.listeners.GeneralFilterListener;
import models.ExamsRowSorter;
import models.ExamsTableModel;
import views.dialogs.FilterDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for apply filter button of the {@link views.dialogs.FilterDialog}.
 * 
 * @see views.AppFrame
 * @see models.ExamsTableModel
 * @see models.ExamsRowSorter
 * @see java.awt.event.ActionListener
 */
public class FilterDialogListener extends GeneralFilterListener implements ActionListener {
    /**
     * Dialog with filter data
     */
    private FilterDialog dialog;

    /**
     * Exam table
     */
    private JTable table;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with filter data
     * @param table      Exam table
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public FilterDialogListener(FilterDialog dialog, JTable table, AtomicBoolean isFiltered) {
        super(dialog, isFiltered);
        this.dialog = (FilterDialog) super.getDialog();
        this.table = table;
    }

    /**
     * Sets the table filter using the string taken from
     * {@link controllers.listeners.filter.FilterDialogListener#dialog}, adds
     * {@link views.panels.FilterPanel} to the application frame and action
     * listeners
     * to the panel buttons
     * 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExamsRowSorter rs = new ExamsRowSorter((ExamsTableModel) table.getModel());

        rs.setSortable(0, false);
        table.setRowSorter(rs);

        rs.createRowFilter(dialog.getFilterField().getText());

        for (int i = 0; i < rs.getModelRowCount(); i++) {
            rs.getModel().updateRowNumber(i, rs.convertRowIndexToView(i));
        }

        dialog.dispose();

        updateFilter();

        super.getFilterBoolean().set(true);
    }
}
