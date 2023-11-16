package controllers.listeners;

import java.awt.event.*;
import java.util.ArrayList;

import models.ExamsTableModel;
import views.dialogs.RemoveEntryDialog;

public class RemoveEntryDialogListener implements ActionListener {
    private RemoveEntryDialog d;
    private ExamsTableModel model;

    public RemoveEntryDialogListener(RemoveEntryDialog d, ExamsTableModel model) {
        this.d = d;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        ArrayList<Integer> entriesIntervals = getEntriesToRemove(d.getField().getText());

        model.removeEntryAtRows(entriesIntervals);

        d.dispose();
    }

    public ArrayList<Integer> getEntriesToRemove(String removeExpression) {
        String[] removeIntervals = removeExpression.split(",");

        ArrayList<Integer> removeIndexesIntervals = new ArrayList<Integer>();

        for (int i = 0; i < removeIntervals.length; i++) {
            removeIntervals[i] = removeIntervals[i].trim();

            String[] removeInterval = removeIntervals[i].split("-");

            removeIndexesIntervals.add(Integer.parseInt(removeInterval[0]));
            removeIndexesIntervals.add(Integer.parseInt(removeInterval[1]));
        }

        return removeIndexesIntervals;
    }
}
