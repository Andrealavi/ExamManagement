/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for apply filter button of the {@link views.dialogs.FilterDialog}.
 * 
 * @see java.awt.event.ActionListener
 */
public class FilterInfoButtonListener implements ActionListener {
	/**
	 * Filter dialog
	 */
	private JDialog filterDialog;

	/**
	 * Instantiates class attributes using all the function arguments
	 * 
	 * @param filterDialog Filter dialog
	 */
	public FilterInfoButtonListener(JDialog filterDialog) {
		this.filterDialog = filterDialog;
	}

	/**
	 * Shows a {@link javax.swing.JOptionPane} with an info message regarding filter
	 * options
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String message = "It is possible to filter by:\n" + "- Student name\n- Class name";

		JOptionPane.showMessageDialog(filterDialog, message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
}
