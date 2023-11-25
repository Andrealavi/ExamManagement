/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.filter
 */
package controllers.listeners.filter;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

import views.dialogs.HistogramDialog;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for show stat button of the {@link views.panels.FilterPanel}.
 * 
 * @see java.awt.event.ActionListener
 */
public class ShowStatsButtonListener implements ActionListener {
	/**
	 * Application frame
	 */
	private JFrame frame;

	/**
	 * Array of grade frequencies
	 */
	private Integer[] gradesFrequencies;

	/**
	 * Instantiates class attributes using all the function arguments
	 * 
	 * @param frame             Application frame
	 * @param gradesFrequencies Array of grade frequencies
	 */
	public ShowStatsButtonListener(JFrame frame, Integer[] gradesFrequencies) {
		this.frame = frame;
		this.gradesFrequencies = gradesFrequencies;
	}

	/**
	 * Creates a new {@link views.dialogs.HistogramDialog} in which display the
	 * histogram of grade frequencies
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		new HistogramDialog(frame, gradesFrequencies);
	}
}
