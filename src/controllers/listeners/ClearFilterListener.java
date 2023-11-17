package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

import models.ExamsRowSorter;
import views.AppFrame;

public class ClearFilterListener implements ActionListener {
    private AppFrame frame;
    private JTable table;

    public ClearFilterListener(AppFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        ExamsRowSorter rs = (ExamsRowSorter) table.getRowSorter();

        rs.removeRowFilter();

        frame.removeFilterPanel();
    }
}
