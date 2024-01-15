/**
 * @author Andrea Lavino (176195)
 * 
 * @package models
 */
package models;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 * Implements a {@link javax.swing.RowSorter} for exam table. Extends
 * {@link javax.swing.table.TableRowSorter}
 * 
 * @see javax.swing.RowSorter
 * @see javax.swing.RowFilter
 * @see javax.swing.table.TableRowSorter
 */
public class ExamsRowSorter extends TableRowSorter<ExamsTableModel> {
    /**
     * Calls super constructor with model passed as argument
     * 
     * @param model Table model
     */
    public ExamsRowSorter(ExamsTableModel model) {
        super(model);
    }

    /**
     * Creates a regex filter using the {@link java.lang.String} filter passed as
     * argument and sets this {@link javax.swing.RowFilter}
     * 
     * @param filterString string used for filtering
     */
    public void createRowFilter(String filterString) {
        RowFilter<ExamsTableModel, Object> rf = null;

        try {
            rf = RowFilter.regexFilter("(?i)" + filterString, 1, 2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        setRowFilter(rf);
    }

    /**
     * Removes {@link javax.swing.RowFilter} from the {@link javax.swing.RowSorter}
     */
    public void removeRowFilter() {
        setRowFilter(null);
    }
}
