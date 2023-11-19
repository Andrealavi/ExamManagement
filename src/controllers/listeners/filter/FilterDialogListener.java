package controllers.listeners.filter;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTable;

import models.ExamsRowSorter;
import models.ExamsTableModel;
import views.dialogs.FilterDialog;
import views.*;

public class FilterDialogListener implements ActionListener {
    private FilterDialog dialog;
    private JTable table;
    private AtomicBoolean isFiltered;

    public FilterDialogListener(FilterDialog dialog, JTable table, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.table = table;
        this.isFiltered = isFiltered;
    }

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
