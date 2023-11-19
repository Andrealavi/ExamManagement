package models;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class ExamsRowSorter extends TableRowSorter<ExamsTableModel> {
    public ExamsRowSorter(ExamsTableModel model) {
        super(model);
    }

    public void createRowFilter(String filterString) {
        RowFilter<ExamsTableModel, Object> rf = null;

        try {
            rf = RowFilter.regexFilter("(?i)" + filterString + "$", 0, 1, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        setRowFilter(rf);
    }

    public void removeRowFilter() {
        setRowFilter(null);
    }
}
