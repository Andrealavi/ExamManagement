package controllers.listeners.remove;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFrame;

import models.ExamsTableModel;
import views.dialogs.RemoveEntryDialog;

public class RemoveEntryListener implements ActionListener {
    private JFrame frame;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;

    public RemoveEntryListener(JFrame frame, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        RemoveEntryDialog dialog = new RemoveEntryDialog(frame);

        dialog.getButton().addActionListener(new RemoveEntryDialogListener(dialog, model, isSaved));

        dialog.pack();

        dialog.getRootPane().setDefaultButton(dialog.getButton());
    }
}
