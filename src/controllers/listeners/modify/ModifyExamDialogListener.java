package controllers.listeners.modify;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controllers.listeners.filter.ClearFilterListener;
import controllers.listeners.filter.ShowStatsButtonListener;
import views.AppFrame;
import views.dialogs.*;
import models.exam.*;
import models.ExamsTableModel;

public class ModifyExamDialogListener implements ActionListener {
    private AbstractExamDialog dialog;
    private ExamsTableModel model;
    private int row;
    private AtomicBoolean isSaved;
    private AtomicBoolean isFiltered;

    public ModifyExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, int row,
            AtomicBoolean isSaved, AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    private SimpleExam createSimpleEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

        return examEntry;
    }

    private ComposedExam createComposedEntry() throws ExamInfoException {
        String[] generalInfo = dialog.getFieldsData();

        ComposedExam examEntry = new ComposedExam(generalInfo[0], generalInfo[1], generalInfo[2], generalInfo[3]);
        examEntry.setPartialExamsInfo(((ModifyComposedExamDialog) dialog).getExamsData(),
                ((ModifyComposedExamDialog) dialog).getExamNumber());

        return examEntry;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            AbstractExam examEntry;

            if (dialog.getClass().getSimpleName().equals("ModifySimpleExamDialog")) {
                examEntry = createSimpleEntry();
            } else {
                examEntry = createComposedEntry();
            }

            model.updateEntryAtRow(examEntry, row);

            isSaved.set(false);

            if (isFiltered.get()) {
                updateFilter();
            }

            dialog.dispose();
        } catch (ExamInfoException err) {
            JOptionPane.showMessageDialog(dialog, err.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(dialog,
                    "Invalid value inserted.\nPlease make sure you have inserted valid number values for grade and credits fields.",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateFilter() {
        AppFrame frame = (AppFrame) dialog.getParent();

        JTable table = frame.getTablePanel().getTable();

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

        frame.removeFilterPanel();

        frame.addFilterPanel();

        frame.getFilterPanel().getGradeField().setText(weightedAverage.toString());

        frame.getFilterPanel().getClearFilterButton()
                .addActionListener(new ClearFilterListener(frame, table, isFiltered));
        frame.getFilterPanel().getShowStatsButton()
                .addActionListener(new ShowStatsButtonListener(frame, gradesFrequencies));
    }
}