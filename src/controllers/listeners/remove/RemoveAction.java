package controllers.listeners.remove;

import java.awt.event.ActionEvent;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.AppFrame;

public class RemoveAction extends AbstractAction {
    private AppFrame frame;
    private JTable table;
    private AtomicBoolean isFiltered;

    public RemoveAction(AppFrame frame, JTable table, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        ExamsTableModel model = (ExamsTableModel) table.getModel();

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

        for (int i = 0; i < model.getRowCount(); i++) {
            model.updateRowNumber(i, table.convertRowIndexToView(i));
        }

        model.fireTableDataChanged();

        if (isFiltered.get()) {
            updateFilter();
        }
    }

    private void updateFilter() {
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
                    .parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString()) - 18]++;

            gradeSum += Float
                    .parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString())
                    * Float.parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 5).toString());

            creditSum += Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 5).toString());
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
