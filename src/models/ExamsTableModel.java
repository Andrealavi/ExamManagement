package models;

import javax.swing.table.*;

import java.util.ArrayList;
import java.util.Vector;

import models.exam.AbstractExam;

public class ExamsTableModel extends AbstractTableModel {
    private final String[] COLUMN_NAMES = { "First Name", "Last Name", "Class", "Grade", "Credits", "Honors" };
    private Vector<AbstractExam> examEntries;

    public ExamsTableModel(Vector<AbstractExam> examEntries) {
        this.examEntries = examEntries;
    }

    public ExamsTableModel() {
        this(new Vector<AbstractExam>());
    }

    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    public int getRowCount() {
        return examEntries.size();
    }

    public String getColumnName(int col) {
        return COLUMN_NAMES[col];
    }

    public String[] getColumns() {
        return COLUMN_NAMES;
    }

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

    public void addEntry(AbstractExam entry) {
        examEntries.add(entry);
        fireTableDataChanged();
    }

    public AbstractExam getEntryAtRow(int row) {
        return examEntries.get(row);
    }

    public void updateEntryAtRow(AbstractExam entry, int row) {
        examEntries.set(row, entry);
        fireTableDataChanged();
    }

    public void removeEntryAtRows(ArrayList<Integer> rowsIntervals) {
        Integer[] previouslyRemoved = new Integer[examEntries.size()];

        for (int i = 0; i < examEntries.size(); i++) {
            previouslyRemoved[i] = 0;
        }

        for (int i = 0; i < rowsIntervals.size(); i += 2) {
            int fromIndex = rowsIntervals.get(i) - previouslyRemoved[rowsIntervals.get(i) - 1] - 1;
            int toIndex = rowsIntervals.get(i + 1) - previouslyRemoved[rowsIntervals.get(i + 1) - 1];

            examEntries.subList(fromIndex, toIndex).clear();

            int intervalLenght = rowsIntervals.get(i + 1) - rowsIntervals.get(i) + 1;

            for (int j = rowsIntervals.get(i + 1); j < previouslyRemoved.length; j++) {
                previouslyRemoved[j] += intervalLenght;
            }
        }

        fireTableDataChanged();
    }

    public void setEntries(Vector<AbstractExam> examEntries) {
        this.examEntries = examEntries;
        fireTableDataChanged();
    }

    public Vector<AbstractExam> getEntries() {
        return examEntries;
    }
}