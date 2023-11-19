package controllers.listeners.filter;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.JFrame;

import views.dialogs.HistogramDialog;

public class ShowStatsButtonListener implements ActionListener {
	private JFrame frame;
	private Integer[] gradesFrequencies;

	public ShowStatsButtonListener(JFrame frame, Integer[] gradesFrequencies) {
		this.frame = frame;
		this.gradesFrequencies = gradesFrequencies;
	}

	public void actionPerformed(ActionEvent e) {
		HistogramDialog dialog = new HistogramDialog(frame, gradesFrequencies);
	}
}
