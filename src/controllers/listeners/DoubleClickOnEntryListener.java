package controllers.listeners;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import models.ExamsTableModel;
import models.exam.*;
import views.dialogs.ModifyComposedExamDialog;
import views.dialogs.ModifySimpleExamDialog;

public class DoubleClickOnEntryListener extends MouseAdapter {
    private JFrame f;

    public DoubleClickOnEntryListener(JFrame f) {
        this.f = f;
    }

    public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point p = e.getPoint();
        int row = table.rowAtPoint(p);

        if (e.getClickCount() == 2) {
            ExamsTableModel model = (ExamsTableModel) table.getModel();
            AbstractExam entry = model.getEntryAtRow(row);

            if (entry.getClass().getSimpleName().equals("SimpleExam")) {
                ModifySimpleExamDialog d = new ModifySimpleExamDialog(f, model.getColumns());
                d.setEntryFields(entry.toStringArray());

                d.getModifyButton().addActionListener(new ModifySimpleExamListener(d));

                d.getButton().setText("Update");
                d.getButton().addActionListener(new ModifySimpleExamDialogListener(d, model, row));

            } else {
                ComposedExam composed = (ComposedExam) entry;

                ModifyComposedExamDialog d = new ModifyComposedExamDialog(f);
                d.setEntryFields(entry.toStringArray(), composed.getPartialExamsGrades(),
                        composed.getPartialExamsWeights(), composed.getPartialExamsHonors());

                d.getModifyButton().addActionListener(new ModifyComposedExamListener(d));

                d.getButton().setText("Update");
                d.getButton().addActionListener(new ModifyComposedExamDialogListener(d, model, row));

                d.pack();
            }
        }
    }
}
