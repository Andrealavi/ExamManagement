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
    private final String[] COLUMN_NAMES = { "Row", "Student Name", "Class", "Grade", "Credits", "Honors" };

    /**
     * Vector containing exam entries
     */
    private Vector<AbstractExam> examEntries;

    /**
     * ArrayList used to keep track of the row numbers
     */
    private ArrayList<Integer> rowNumbers;

    /**
     * Sets {@link models.ExamsTableModel#examEntries} as an empty vector
     * 
     */
    public ExamsTableModel() {
        examEntries = new Vector<AbstractExam>();
        rowNumbers = new ArrayList<Integer>();
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
                return rowNumbers.get(row);
            case 1:
                return examEntries.get(row).getStudentName();

            case 2:
                return examEntries.get(row).getClassName();

            case 3:
                return examEntries.get(row).getExamGrade();

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
     * Adds a new row number to the ArrayList
     * 
     * @param row row index to add
     */
    public void addRowNumber(int row) {
        rowNumbers.add(row + 1);

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
     * Updated the row number of an existing row
     * 
     * @param row       model row number
     * @param rowNumber new row number
     */
    public void updateRowNumber(int row, int rowNumber) {
        rowNumbers.set(row, rowNumber + 1);
        fireTableDataChanged();
    }

    /**
     * Removes an entry at the given row
     * 
     * @param row index of the entry to remove
     */
    public void removeEntryAtRow(int row) {
        examEntries.remove(row);
    }

    /**
     * Sets {@link models.ExamsTableModel#examEntries} using the
     * {@link java.util.Vector} passed as argument
     * 
     * @param examEntries {@link java.util.Vector} containing exam entries
     */
    public void setEntries(Vector<AbstractExam> examEntries) {
        this.examEntries = examEntries;
        rowNumbers = new ArrayList<Integer>();

        for (int i = 0; i < examEntries.size(); i++) {
            rowNumbers.add(i + 1);
        }

        fireTableDataChanged();
    }

    /**
     * Sets the {@link models.ExamsTableModel#rowNumbers}
     * 
     * @param rowNumbers ArrayList containing the new row numbers
     */
    public void setRowNumbers(ArrayList<Integer> rowNumbers) {
        this.rowNumbers = rowNumbers;

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