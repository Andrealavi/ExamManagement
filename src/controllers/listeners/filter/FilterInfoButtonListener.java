package controllers.listeners.filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FilterInfoButtonListener implements ActionListener {
	private JDialog filterDialog;

	public FilterInfoButtonListener(JDialog filterDialog) {
		this.filterDialog = filterDialog;
	}

	public void actionPerformed(ActionEvent e) {
		String message = "It is possible to filter by:\n" + "- First name\n- Last name\n- Class name";

		JOptionPane.showMessageDialog(filterDialog, message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
}
