/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.*;

/**
 * Implements a dialog for displaying statistics about grade of exams present in
 * exam table.
 * 
 * @see org.knowm.xchart
 * @see javax.swing.JDialog
 */
public class HistogramDialog extends JDialog {

	/**
	 * Calls the super constructor and creates grades frequency histogram
	 * 
	 * @param frame             Parent frame
	 * @param gradesFrequencies Array of grade frequencies
	 */
	public HistogramDialog(JFrame frame, Integer[] gradesFrequencies) {
		super(frame, "Grades Histogram");

		final Integer[] gradesRange = { 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };

		CategoryChart chart = new CategoryChartBuilder().width(600).height(600).title("Grades Histogram")
				.xAxisTitle("Grade").yAxisTitle("Frequency").build();

		chart.addSeries("Grades Frequencies", Arrays.asList(gradesRange), Arrays.asList(gradesFrequencies));

		add(new XChartPanel<CategoryChart>(chart));

		pack();
		setVisible(true);
	}

}
