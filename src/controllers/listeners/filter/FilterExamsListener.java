package controllers.listeners.filter;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import views.dialogs.FilterDialog;

public class FilterExamsListener implements ActionListener {
    private JFrame frame;
    private JTable table;
    private AtomicBoolean isFiltered;

    public FilterExamsListener(JFrame frame, JTable table, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        FilterDialog dialog = new FilterDialog(frame);

        dialog.getButton().addActionListener(new FilterDialogListener(dialog, table, isFiltered));
        dialog.getInfoButton().addActionListener(new FilterInfoButtonListener(dialog));

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }

}
