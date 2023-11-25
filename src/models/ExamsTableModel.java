/**
 * @author Andrea Lavino (176195)
 * 
 * @package models
 */
package models;

import javax.swing.table.*;

import java.util.ArrayList;
import java.util.Vector;

import models.exam.AbstractExam;

/**
 * Implements exam table model by extending
 * {@link javax.swing.table.AbstractTableModel}
 * 
 * @see javax.swing.table.AbstractTableModel
 * @see java.util.Vector
 * @see java.util.ArrayList
 */
public class ExamsTableModel extends AbstractTableModel {
    /**
     * Table columns
     */
    private final String[] COLUMN_NAMES = { "First Name", "Last Name", "Class", "Grade", "Credits", "Honors" };

    /**
     * Vector containing exam entries
     */
    private Vector<AbstractExam> examEntries;

    /**
     * Sets {@link models.ExamsTableModel#examEntries} as an empty vector
     * 
     */
    public ExamsTableModel() {
        examEntries = new Vector<AbstractExam>();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public int getRowCount() {
        return examEntries.size();
    }

    /**
     * Gets column name
     * 
     * @param col column index
     * 
     * @return {@link java.lang.String} containing column name
     */
    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    /**
     * Gets column names
     * 
     * @return An array of {@link java.lang.String} containing column names
     */
    public String[] getColumns() {
        return COLUMN_NAMES;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return examEntries.get(row).getFirstName();

            case 1:
                return examEntries.get(row).getLastName();

            case 2:
                return examEntries.get(row).getClassName();

            case 3:
                return examEntries.get(row).getGrade();

            case 4:
                return examEntries.get(row).getCredits();

            case 5:
                return examEntries.get(row).getHonor();

            default:
                break;
        }

        return null;
    }

    /**
     * Adds the entry passed as argument to
     * {@link models.ExamsTableModel#examEntries}
     * 
     * @param entry Exam entry
     */
    public void addEntry(AbstractExam entry) {
        examEntries.add(entry);
        fireTableDataChanged();
    }

    /**
     * Gets the exam entry at row
     * 
     * @param row Row index
     * @return Exam entry
     */
    public AbstractExam getEntryAtRow(int row) {
        return examEntries.get(row);
    }

    /**
     * Updates the data of the exam entry in a specific row
     * 
     * @param entry Exam entry with new data
     * @param row   Row index
     */
    public void updateEntryAtRow(AbstractExam entry, int row) {
        examEntries.set(row, entry);
        fireTableDataChanged();
    }

    /**
     * Removes entries, passed via {@link java.util.ArrayList} of
     * {@link java.lang.Integer} from {@link models.ExamsTableModel#examEntries}
     * 
     * @param rowsIntervals List containing inteval of entries to remove
     */
    public void removeEntryAtRows(ArrayList<Integer> rowsIntervals) {
        Integer[] previouslyRemoved = new Integer[examEntries.size()];

        for (int i = 0; i < examEntries.size(); i++) {
            previouslyRemoved[i] = 0;
        }

        for (int i = 0; i < rowsIntervals.size(); i += 2) {
            int fromIndex = rowsIntervals.get(i) - previouslyRemoved[rowsIntervals.get(i)];
            int toIndex = rowsIntervals.get(i + 1) - previouslyRemoved[rowsIntervals.get(i + 1)] + 1;

            examEntries.subList(fromIndex, toIndex).clear();

            int intervalLenght = rowsIntervals.get(i + 1) - rowsIntervals.get(i) + 1;

            for (int j = rowsIntervals.get(i + 1); j < previouslyRemoved.length; j++) {
                previouslyRemoved[j] += intervalLenght;
            }
        }

        fireTableDataChanged();
    }

    /**
     * Sets {@link models.ExamsTableModel#examEntries} using the
     * {@link java.util.Vector} passed as argument
     * 
     * @param examEntries {@link java.util.Vector} containing exam entries
     */
    public void setEntries(Vector<AbstractExam> examEntries) {
        this.examEntries = examEntries;
        fireTableDataChanged();
    }

    /**
     * Gets all the exam entries
     * 
     * @return {@link java.util.Vector} containing exam entries
     */
    public Vector<AbstractExam> getEntries() {
        return examEntries;
    }
}