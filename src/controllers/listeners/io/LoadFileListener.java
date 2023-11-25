/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.io
 */
package controllers.listeners.io;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.concurrent.atomic.AtomicBoolean;

import models.ExamIO;
import models.ExamsRowSorter;
import models.ExamsTableModel;
import models.exam.AbstractExam;
import views.*;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for Load File button of the application menu. It uses
 * {@link models.ExamIO} to load exam entries from a file
 * 
 * @see models.ExamIO
 * @see models.ExamsRowSorter
 * @see models.exam.AbstractExam
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class LoadFileListener implements ActionListener {
    /**
     * Application frame
     */
    private AppFrame frame;

    /**
     * Exam table model
     */
    private ExamsTableModel model;

    /**
     * File chooser for selecting file
     */
    private JFileChooser fileChooser;

    /**
     * Boolean used to check whether the exam entries are saved or not
     */
    private AtomicBoolean isSaved;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param frame       Application frame
     * @param fileChooser File chooser
     * @param model       Table model
     * @param isSaved     Boolean containing save state of the exam table data
     */
    public LoadFileListener(AppFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File("./documents"));
        this.isSaved = isSaved;
        System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(frame);
        ExamIO fileIO;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileIO = new ExamIO(fileChooser.getSelectedFile());

            try {
                Vector<AbstractExam> examEntries = fileIO.load();

                model.setEntries(examEntries);
            } catch (FileNotFoundException notFound) {
                JOptionPane.showMessageDialog(frame,
                        "The file you chose doesn't exist.\nPlease choose an existing file", "Error message",
                        JOptionPane.ERROR_MESSAGE);

                fileChooser.cancelSelection();
            } catch (Exception generalException) {
                JOptionPane.showMessageDialog(frame, generalException.getMessage(), "Error message",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

        isSaved.set(true);

        ExamsRowSorter rs = (ExamsRowSorter) frame.getTablePanel().getTable().getRowSorter();

        if (rs != null) {
            rs.removeRowFilter();
            frame.removeFilterPanel();
        }

        frame.pack();
    }
}
