/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.remove
 */
package controllers.listeners.remove;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import models.ExamsTableModel;
import views.AppFrame;
import views.dialogs.RemoveEntryDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for remove button of {@link views.dialogs.RemoveEntryDialog}.
 * 
 * @see views.AppFrame
 * @see models.ExamsTableModel
 * @see models.ExamsRowSorter
 * @see java.awt.event.ActionListener
 */
public class RemoveEntryDialogListener implements ActionListener {
    /**
     * Dialog with remove expression
     */
    private RemoveEntryDialog dialog;

    /**
     * Exam table
     */
    private JTable table;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with remove expression
     * @param table      Exam table
     * @param model      Table model
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public RemoveEntryDialogListener(RemoveEntryDialog dialog, JTable table, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.table = table;
        this.model = (ExamsTableModel) table.getModel();
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Removes entries in exam table
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<Integer> entriesIntervals = getEntriesToRemove(dialog.getField().getText());

            model.removeEntryAtRows(entriesIntervals);

            if (isFiltered.get()) {
                updateFilter();
            }

            dialog.dispose();
        } catch (NumberFormatException formatException) {
            JOptionPane.showMessageDialog(dialog,
                    "Your input is not valid.\nPlease make sure you've inserted correct numeric values",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Parses the remove expression into an {@link java.util.ArrayList} containing
     * the intervals of entries to remove
     * 
     * @param removeExpression {@link java.lang.String} containing the intervals to
     *                         remove
     * @return List containing indexes of intervals of entry to remove
     * @throws NumberFormatException Exception that is thrown when is inserted an
     *                               invalid number value
     */
    public ArrayList<Integer> getEntriesToRemove(String removeExpression) throws NumberFormatException {
        String[] removeIntervals = removeExpression.split(",");

        ArrayList<Integer> removeIndexesIntervals = new ArrayList<Integer>();

        for (int i = 0; i < removeIntervals.length; i++) {
            removeIntervals[i] = removeIntervals[i].trim();

            String[] removeInterval = removeIntervals[i].split("-");

            if (removeInterval.length == 1) {
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
            } else {
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[0]) - 1));
                removeIndexesIntervals.add(table.convertRowIndexToModel(Integer.parseInt(removeInterval[1]) - 1));
            }
        }

        isSaved.set(false);

        return removeIndexesIntervals;
    }

    /**
     * Updates the data displayed by the filtered table
     */
    private void updateFilter() {
        AppFrame frame = (AppFrame) dialog.getParent();

        Float gradeSum = 0.0f;
        Integer creditSum = 0;

        Integer[] gradesFrequencies = new Integer[13];

        for (int i = 0; i < gradesFrequencies.length; i++) {
            gradesFrequencies[i] = 0;
        }

        for (int i = 0; i < table.getRowSorter().getViewRowCount(); i++) {
            gradesFrequencies[Integer
                    .parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString()) - 18]++;

            gradeSum += Float
                    .parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 3).toString())
                    * Float.parseFloat(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());

            creditSum += Integer.parseInt(table.getModel().getValueAt(table.convertRowIndexToModel(i), 4).toString());
        }

        Integer weightedAverage = (int) (gradeSum / creditSum);

        frame.getFilterPanel().getShowStatsButton()
                .removeActionListener(frame.getFilterPanel().getShowStatsButton().getActionListeners()[0]);
        frame.getFilterPanel().getClearFilterButton()
                .removeActionListener(frame.getFilterPanel().getClearFilterButton().getActionListeners()[0]);

        frame.removeFilterPanel();

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));
    }
}
