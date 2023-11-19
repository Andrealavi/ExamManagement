package controllers.listeners.filter;

import java.awt.event.*;

import javax.swing.JTable;

import models.ExamsRowSorter;
import models.ExamsTableModel;
import views.dialogs.FilterDialog;
import views.*;

public class FilterDialogListener implements ActionListener {
    private FilterDialog d;
    private JTable table;

    public FilterDialogListener(FilterDialog d, JTable table) {
        this.d = d;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        ExamsRowSorter rs = new ExamsRowSorter((ExamsTableModel) table.getModel());

        table.setRowSorter(rs);

        rs.createRowFilter(d.getFilterField().getText());

        d.dispose();

        AppFrame frame = (AppFrame) d.getParent();

        Float gradeSum = 0.0f;
        Integer creditSum = 0;

        for (int i = 0; i < table.getRowSorter().getViewRowCount(); i++) {
            gradeSum += Float
                    .parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString())
                    * Float.parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());

            creditSum += Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());
        }

        Integer weightedAverage = (int) (gradeSum / creditSum);

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        frame.getFilterPanel().getClearFilterButton().addActionListener(new ClearFilterListener(frame, table));
    }
}
