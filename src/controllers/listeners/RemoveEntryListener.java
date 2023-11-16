package controllers.listeners;

import java.awt.event.*;

import javax.swing.JFrame;

import models.ExamsTableModel;
import views.dialogs.RemoveEntryDialog;

public class RemoveEntryListener implements ActionListener {
    private JFrame f;
    private ExamsTableModel model;

    public RemoveEntryListener(JFrame f, ExamsTableModel model) {
        this.f = f;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        RemoveEntryDialog d = new RemoveEntryDialog(f);

        d.getButton().addActionListener(new RemoveEntryDialogListener(d, model));

        d.pack();
    }
}
