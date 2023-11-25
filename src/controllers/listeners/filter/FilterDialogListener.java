/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;

import models.ExamsRowSorter;
import models.ExamsTableModel;
import views.dialogs.FilterDialog;
import views.*;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for apply filter button of the {@link views.dialogs.FilterDialog}.
 * 
 * @see views.AppFrame
 * @see models.ExamsTableModel
 * @see models.ExamsRowSorter
 * @see java.awt.event.ActionListener
 */
public class FilterDialogListener implements ActionListener {
    /**
     * Dialog with filter data
     */
    private FilterDialog dialog;

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
     * @param dialog     Dialog with filter data
     * @param table      Exam table
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public FilterDialogListener(FilterDialog dialog, JTable table, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.table = table;
        this.isFiltered = isFiltered;
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

        table.setRowSorter(rs);

        rs.createRowFilter(dialog.getFilterField().getText());

        dialog.dispose();

        AppFrame frame = (AppFrame) dialog.getParent();

        Float gradeSum = 0.0f;
        Integer creditSum = 0;

        Integer[] gradesFrequencies = new Integer[13];

        for (int i = 0; i < gradesFrequencies.length; i++) {
            gradesFrequencies[i] = 0;
        }

        for (int i = 0; i < table.getRowSorter().getViewRowCount(); i++) {
            gradesFrequencies[Integer
                    .parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString()) - 18]++;

            gradeSum += Float
                    .parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString())
                    * Float.parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());

            creditSum += Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());
        }

        Integer weightedAverage = (int) (gradeSum / creditSum);

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));

        isFiltered.set(true);
    }
}
