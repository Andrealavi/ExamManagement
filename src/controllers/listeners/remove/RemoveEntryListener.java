package controllers.listeners.remove;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;
import javax.swing.JTable;

import views.dialogs.RemoveEntryDialog;

public class RemoveEntryListener implements ActionListener {
    private JFrame frame;
    private JTable table;
    private AtomicBoolean isSaved;
    private AtomicBoolean isFiltered;

    public RemoveEntryListener(JFrame frame, JTable table, AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.frame = frame;
        this.table = table;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    public void actionPerformed(ActionEvent e) {
        RemoveEntryDialog dialog = new RemoveEntryDialog(frame);

        dialog.getButton().addActionListener(new RemoveEntryDialogListener(dialog, table, isSaved, isFiltered));

        dialog.getInfoButton().addActionListener(new RemoveInfoButtonListener(dialog));

        dialog.pack();

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
