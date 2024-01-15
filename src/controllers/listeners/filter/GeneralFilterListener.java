/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JDialog;
import javax.swing.JTable;

import models.ExamsTableModel;
import views.AppFrame;

/**
 * Implements updateFilter method used across several listeners
 * 
 * @see models.ExamsTableModel
 * @see views.AppFrame
 */
public class GeneralFilterListener {
    /**
     * Dialog used to get the parent frame
     */
    private JDialog dialog;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog
     * @param isFiltered
     */
    public GeneralFilterListener(JDialog dialog, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.isFiltered = isFiltered;
    }

    /**
     * Returns the dialog from which the listener is called
     * 
     * @return dialog attribute
     */
    protected JDialog getDialog() {
        return dialog;
    }

    /**
     * Returns
     * {@link controllers.listeners.filter.GeneralFilterListener#isFiltered}, the
     * boolean that checks whether the table is filtered or not
     * 
     * @return isFiltered attribute
     */
    protected AtomicBoolean getFilterBoolean() {
        return isFiltered;
    }

    /**
     * Update filter panel weighted average and histogram data after that an exam is
     * added/removed or
     * the filter is applied.
     * 
     * @see views.AppFrame
     * @see views.panels.FilterPanel
     * @see views.dialogs.HistogramDialog
     * @see models.ExamsRowSorter
     * 
     */
    protected final void updateFilter() {
        AppFrame frame = (AppFrame) dialog.getParent();
        JTable table = frame.getTablePanel().getTable();

        ExamsTableModel model = (ExamsTableModel) table.getModel();

        for (int i = 0; i < table.getRowSorter().getModelRowCount(); i++) {
            model.updateRowNumber(i, table.getRowSorter().convertRowIndexToView(i));
        }

        Float gradeSum = 0.0f;
        Integer creditSum = 0;

        Integer[] gradesFrequencies = new Integer[13];

        for (int i = 0; i < gradesFrequencies.length; i++) {
            gradesFrequencies[i] = 0;
        }

        /**
         * Computes the weighted average
         */
        for (int i = 0; i < table.getRowSorter().getViewRowCount(); i++) {
            gradesFrequencies[Integer
                    .parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString()) - 18]++;

            gradeSum += Float
                    .parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString())
                    * Float.parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());

            creditSum += Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());
        }

        Integer weightedAverage = Math.round(gradeSum / creditSum);

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        /**
         * Removes listeners from filterPanel buttons
         */
        if (frame.getFilterPanel().getClearFilterButton().getActionListeners().length != 0) {
            int length = frame.getFilterPanel().getClearFilterButton().getActionListeners().length;

            for (int i = 0; i < length; i++) {
                frame.getFilterPanel().getShowStatsButton()
                        .removeActionListener(frame.getFilterPanel().getClearFilterButton().getActionListeners()[i]);
            }
        }

        if (frame.getFilterPanel().getClearFilterButton().getActionListeners().length != 0) {
            int length = frame.getFilterPanel().getShowStatsButton().getActionListeners().length;

            for (int i = 0; i < length; i++) {
                frame.getFilterPanel().getShowStatsButton()
                        .removeActionListener(frame.getFilterPanel().getShowStatsButton().getActionListeners()[i]);
            }
        }

        /**
         * Add listeners to filterPanel buttons
         */
        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));
    }
}
