package models;

import javax.swing.table.*;
import java.util.Vector;

import models.exam.AbstractExam;

public class ExamsTableModel extends AbstractTableModel {
    private final String[] COLUMN_NAMES = { "First Name", "Last Name", "Course", "Grade", "Honors", "Credits" };
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

    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return examEntries.get(row).getFirstName();

            case 1:
                return examEntries.get(row).getLastName();

            case 2:
                return examEntries.get(row).getCourseName();

            case 3:
                return examEntries.get(row).getGrade();

            case 4:
                return examEntries.get(row).getHonors();

            case 5:
                return examEntries.get(row).getCredits();

            default:
                break;
        }

        return null;
    }
}