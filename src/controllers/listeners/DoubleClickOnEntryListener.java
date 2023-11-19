package controllers.listeners;

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.*;

import controllers.listeners.modify.ModifyExamListener;
import models.ExamsTableModel;
import models.exam.*;
import views.dialogs.AbstractExamDialog;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.ModifySimpleExamDialog;

public class DoubleClickOnEntryListener extends MouseAdapter {
    private JFrame frame;
    private AtomicBoolean isSaved;

    public DoubleClickOnEntryListener(JFrame frame, AtomicBoolean isSaved) {
        this.frame = frame;
        this.isSaved = isSaved;
    }

    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);

        if (e.getClickCount() == 2) {
            ExamsTableModel model = (ExamsTableModel) table.getModel();
            AbstractExam entry = model.getEntryAtRow(row);
            AbstractExamDialog dialog;

            if (entry.getClass().getSimpleName().equals("SimpleExam")) {
                dialog = new ModifySimpleExamDialog(frame, model.getColumns());
                ((ModifySimpleExamDialog) dialog).setEntryFields(entry.toStringArray());

                ((ModifySimpleExamDialog) dialog).getModifyButton().addActionListener(
                        new ModifyExamListener(dialog, model, row, isSaved));
            } else {
                dialog = new ModifyComposedExamDialog(frame);
                ((ModifyComposedExamDialog) dialog).setEntryFields(entry.toStringArray(),
                        ((ComposedExam) entry).getPartialExamsGrades(),
                        ((ComposedExam) entry).getPartialExamsWeights());

                ((ModifyComposedExamDialog) dialog).getModifyButton()
                        .addActionListener(new ModifyExamListener(dialog, model, row, isSaved));
            }

            dialog.getButton().addActionListener(new CloseButtonListener(dialog));
            dialog.getRootPane().setDefaultButton(dialog.getButton());
        }
    }
}
