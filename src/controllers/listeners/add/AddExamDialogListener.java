/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.add 
 */
package controllers.listeners.add;

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

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener to add a new exam to the exam table.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class AddExamDialogListener implements ActionListener {
    /**
     * Dialog with exam data
     */
    private AbstractExamDialog dialog;

    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Boolean used to check whether the exam entries are filtered or not
     */
    private AtomicBoolean isFiltered;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with exam data
     * @param model      Table model
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public AddExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, AtomicBoolean isSaved,
            AtomicBoolean isFiltered) {
        this.dialog = dialog;
        this.model = model;
        this.isSaved = isSaved;
        this.isFiltered = isFiltered;
    }

    /**
     * Creates a {@link models.exam.SimpleExam} entry using data taken from
     * {@link controllers.listeners.add.AddExamDialogListener#dialog}
     * 
     * @return Simple exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    private SimpleExam createSimpleExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

        return examEntry;
    }

    /**
     * Creates a {@link models.exam.ComposedExam} entry using data taken from
     * {@link controllers.listeners.add.AddExamDialogListener#dialog}
     * 
     * @return Composed exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    private ComposedExam createComposedExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        String[][] partialExamsData = ((AddComposedExamDialog) dialog).getExamsData();

        ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
        examEntry.setPartialExamsInfo(partialExamsData, ((AddComposedExamDialog) dialog).getExamNumber());

        return examEntry;
    }

    /**
     * Creates a new exam entry and adds it to the exam table
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            AbstractExam examEntry;

            if (dialog.getClass().getSimpleName().equals("AddSimpleExamDialog")) {
                examEntry = createSimpleExamEntry();
            } else {
                examEntry = createComposedExamEntry();
            }

            model.addEntry(examEntry);

            if (isFiltered.get()) {
                updateFilter();
            }

            isSaved.set(false);

            dialog.dispose();
        } catch (ExamInfoException err) {
            JOptionPane.showMessageDialog(dialog, err.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(dialog, "Invalid value inserted.\n" +
                    "Please make sure you have inserted valid number values for grade and credits fields.",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Updates the data displayed by the filtered table
     */
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