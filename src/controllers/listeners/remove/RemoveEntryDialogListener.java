package controllers.listeners.remove;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import controllers.listeners.CloseButtonListener;
import models.ExamsTableModel;
import views.dialogs.ErrorDialog;
import views.dialogs.RemoveEntryDialog;

public class RemoveEntryDialogListener implements ActionListener {
    private RemoveEntryDialog dialog;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;

    public RemoveEntryDialogListener(RemoveEntryDialog dialog, ExamsTableModel model, AtomicBoolean isSaved) {
        this.dialog = dialog;
        this.model = model;
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<Integer> entriesIntervals = getEntriesToRemove(dialog.getField().getText());

            model.removeEntryAtRows(entriesIntervals);

            dialog.dispose();
        } catch (NumberFormatException formatException) {
            ErrorDialog errorDialog = new ErrorDialog(dialog,
                    "Your input is not valid.\nPlease make sure you've inserted correct numeric values");

            errorDialog.getButton().addActionListener(new CloseButtonListener(errorDialog));
        }
    }

    public ArrayList<Integer> getEntriesToRemove(String removeExpression) throws NumberFormatException {
        String[] removeIntervals = removeExpression.split(",");

        ArrayList<Integer> removeIndexesIntervals = new ArrayList<Integer>();

        for (int i = 0; i < removeIntervals.length; i++) {
            removeIntervals[i] = removeIntervals[i].trim();

            String[] removeInterval = removeIntervals[i].split("-");

            if (removeInterval.length == 1) {
                removeIndexesIntervals.add(Integer.parseInt(removeInterval[0]));
                removeIndexesIntervals.add(Integer.parseInt(removeInterval[0]));
            } else {
                removeIndexesIntervals.add(Integer.parseInt(removeInterval[0]));
                removeIndexesIntervals.add(Integer.parseInt(removeInterval[1]));
            }
        }

        isSaved.set(false);

        return removeIndexesIntervals;
    }
}
