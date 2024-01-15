/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listener.remove
 */
package controllers.listeners.remove;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.AppFrame;

/**
 * Extends {@link javax.swing.AbstractAction} in order to implement an action to
 * remove exam entries
 * 
 * @see javax.swing.AbstractAction
 */
public class RemoveAction extends AbstractAction {
    /**
     * Application frame
     */
    private AppFrame frame;

    /**
     * Exams table
     */
    private JTable table;

    /**
     * Boolean that checks whether the table is filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Boolean that checks whether the data in the table are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Integer that represent the single row to remove. By default it is set to null
     */
    private Integer row = null;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame      application frame
     * @param table      exam table
     * @param isSaved    boolean to check if the data are saved
     * @param isFiltered boolean to check if there is a filter
     */
    public RemoveAction(AppFrame frame, JTable table, AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame      application frame
     * @param table      exam table
     * @param row        index of the row to remove
     * @param isSaved    boolean to check if the data are saved
     * @param isFiltered boolean to check if there is a filter
     */
    public RemoveAction(AppFrame frame, JTable table, Integer row, AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this(frame, table, isSaved, isFiltered);
        this.row = row;
    }

    /**
     * Remove entries at the selected rows
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ExamsTableModel model = (ExamsTableModel) table.getModel();

        if (row != null) {
            model.removeEntryAtRow(table.convertColumnIndexToModel(row));
        } else {
            int[] selectedRows = table.getSelectedRows();
            int[] previouslyRemoved = new int[model.getRowCount()];

            for (int i = 0; i < model.getRowCount(); i++) {
                previouslyRemoved[i] = 0;
            }

            for (int i = 0; i < selectedRows.length; i++) {
                int convertedIndex = table.convertRowIndexToModel(selectedRows[i]);

                model.removeEntryAtRow(convertedIndex - previouslyRemoved[convertedIndex]);

                for (int j = convertedIndex + 1; j < previouslyRemoved.length; j++) {
                    previouslyRemoved[j] += 1;
                }
            }
        }

        model.fireTableDataChanged();

        ArrayList<Integer> rowNumbers = new ArrayList<Integer>();

        for (int i = 0; i < model.getRowCount(); i++) {
            rowNumbers.add(i + 1);
        }

        model.setRowNumbers(rowNumbers);

        isSaved.set(false);

        if (isFiltered.get()) {
            updateFilter();
        }
    }

    /**
     * Update filterPanel weighted average and histogram data after that an exam is
     * added/removed or
     * the filter is applied.
     */
    private final void updateFilter() {
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

        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));
    }
}
