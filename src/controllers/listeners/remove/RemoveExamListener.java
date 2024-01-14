package controllers.listeners.remove;

import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.dialogs.AbstractExamDialog;
import views.AppFrame;

import java.awt.event.ActionEvent;

public class RemoveExamListener implements ActionListener {
    private AbstractExamDialog dialog;
    private JTable table;
    private int row;
    private AtomicBoolean isSaved;
    private AtomicBoolean isFiltered;

    public RemoveExamListener(AbstractExamDialog dialog, JTable table, int row, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.table = table;
        this.row = row;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        ExamsTableModel model = (ExamsTableModel) table.getModel();

        model.removeEntryAtRow(row);

        model.fireTableDataChanged();

        for (int i = 0; i < model.getRowCount(); i++) {
            model.updateRowNumber(i, table.convertRowIndexToView(i));
        }

        isSaved.set(false);

        if (isFiltered.get()) {
            updateFilter();
        }

        dialog.dispose();
    }

    private void updateFilter() {
        AppFrame frame = (AppFrame) dialog.getParent();
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
