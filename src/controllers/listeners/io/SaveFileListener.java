/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners.io
 */
package controllers.listeners.io;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import models.ExamIO;
import models.ExamsTableModel;
import models.exam.AbstractExam;
import views.AppFrame;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener for Save File button of the application menu. It uses
 * {@link models.ExamIO} to load exam entries from a file
 *
 * @see models.ExamIO
 * @see models.ExamsRowSorter
 * @see models.exam.AbstractExam
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 * 
 */
public class SaveFileListener implements ActionListener {
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
     * Boolean used to check wether the exam entries are saved or not
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
    public SaveFileListener(AppFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File("./documents"));
        this.isSaved = isSaved;
    }

    /**
     * Returns a boolean that indicates whether the user wants to overwrite the
     * selected file or not
     * 
     * @param file File to overwrite
     * @return A boolean that indicates whether the user wants to overwrite existing
     *         file or not
     */
    public Boolean approveOverwrite(File file) {
        if (file.exists()) {
            int result = JOptionPane.showConfirmDialog(fileChooser,
                    "This file already exists, do you want to overwrite it?", "Existing file",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            switch (result) {
                case JOptionPane.YES_OPTION:
                    return true;
                default:
                    break;
            }
        } else {
            return true;
        }

        return false;
    }

    /**
     * Make the user select a file and save data
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File file = null;

        if (fileChooser.getSelectedFile() != null) {
            file = fileChooser.getSelectedFile();

        } else {
            int returnVal = fileChooser.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (approveOverwrite(fileChooser.getSelectedFile())) {
                    file = fileChooser.getSelectedFile();
                } else {
                    fileChooser.setSelectedFile(null);
                }
            }
        }

        Vector<AbstractExam> examEntries = model.getEntries();

        try {
            ExamIO.save(file, examEntries);
        } catch (NullPointerException nullPointerException) {
            // I don't want to manage this exception, but I don't want to have errors on
            // terminal
        } catch (Exception error) {
            JOptionPane.showMessageDialog(frame, error.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
        }

        isSaved.set(true);
    }
}
