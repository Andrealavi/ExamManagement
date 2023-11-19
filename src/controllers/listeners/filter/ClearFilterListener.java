package controllers.listeners.filter;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import models.ExamsRowSorter;
import views.AppFrame;

public class ClearFilterListener implements ActionListener {
    private AppFrame frame;
    private JTable table;
    private AtomicBoolean isFiltered;

    public ClearFilterListener(AppFrame frame, JTable table, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        ExamsRowSorter rs = (ExamsRowSorter) table.getRowSorter();

        rs.removeRowFilter();

        frame.removeFilterPanel();

        isFiltered.set(false);
    }
}
