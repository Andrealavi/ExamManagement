package views.dialogs;

import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.*;

public class HistogramDialog extends JDialog {
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
