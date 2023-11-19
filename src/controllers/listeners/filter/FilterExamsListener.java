package controllers.listeners.filter;

import java.awt.event.*;

import javax.swing.*;

import views.dialogs.FilterDialog;

public class FilterExamsListener implements ActionListener {
    private JFrame frame;
    private JTable table;

    public FilterExamsListener(JFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    public void actionPerformed(ActionEvent e) {
        FilterDialog d = new FilterDialog(frame);

        d.getButton().addActionListener(new FilterDialogListener(d, table));
        d.getRootPane().setDefaultButton(d.getButton());
    }

}
