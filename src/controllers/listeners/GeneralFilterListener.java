package controllers.listeners;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JDialog;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.AppFrame;

public class GeneralFilterListener {
    private JDialog dialog;
    private AtomicBoolean isFiltered;

    public GeneralFilterListener(JDialog dialog, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.isFiltered = isFiltered;
    }

    protected JDialog getDialog() {
        return dialog;
    }

    protected AtomicBoolean getFilterBoolean() {
        return isFiltered;
    }

    protected void updateFilter() {
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
