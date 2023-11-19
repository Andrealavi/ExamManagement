package controllers.listeners.remove;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.RemoveEntryDialog;

public class RemoveEntryDialogListener implements ActionListener {
    private RemoveEntryDialog dialog;
    private JTable table;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;
    private AtomicBoolean isFiltered;

    public RemoveEntryDialogListener(RemoveEntryDialog dialog, JTable table, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.table = table;
        this.model = (ExamsTableModel) table.getModel();
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<Integer> entriesIntervals = getEntriesToRemove(dialog.getField().getText());

            model.removeEntryAtRows(entriesIntervals);

            if (isFiltered.get()) {
                updateFilter();
            }

            dialog.dispose();
        } catch (NumberFormatException formatException) {
            JOptionPane.showMessageDialog(dialog,
                    "Your input is not valid.\nPlease make sure you've inserted correct numeric values",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Integer> getEntriesToRemove(String removeExpression) throws NumberFormatException {
        String[] removeIntervals = removeExpression.split(",");

        ArrayList<Integer> removeIndexesIntervals = new ArrayList<Integer>();

        for (int i = 0; i < removeIntervals.length; i++) {
            removeIntervals[i] = removeIntervals[i].trim();

            String[] removeInterval = removeIntervals[i].split("-");

            if (removeInterval.length == 1) {
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
            } else {
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[1]) - 1));
            }
        }

        isSaved.set(false);

        return removeIndexesIntervals;
    }

    private void updateFilter() {
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

        frame.removeFilterPanel();

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));
    }
}
