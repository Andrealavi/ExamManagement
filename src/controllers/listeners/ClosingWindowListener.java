/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listeners
 */
package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import models.exam.*;
import models.ExamIO;
import models.ExamsTableModel;

import java.io.File;

/**
 * Extends {@link java.awt.event.WindowAdapter} to create a custom event
 * listener for when the user is closing the application window
 * 
 * @see models.ExamIO
 * @see models.ExamsTableModel
 * @see java.awt.event.WindowAdapter
 */
public class ClosingWindowListener extends WindowAdapter {
    /**
     * Application frame
     */
    private JFrame frame;

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
     * Instantiates class attribute with the value passed as argument
     * 
     * @param frame       Application frame
     * @param fileChooser File chooser
     * @param model       Table model
     * @param isSaved     Boolean containing save state of the exam table data
     */
    public ClosingWindowListener(JFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.fileChooser = fileChooser;
        this.model = model;
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

            if (result == JOptionPane.YES_OPTION) {
                return true;
            }
        } else {
            return true;
        }

        return false;
    }

    /**
     * Saves file content into the selected file
     * 
     * @return a boolean that indicates the result of the operation
     */
    public boolean saveFile() {
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

        if (file == null) {
            return false;
        }

        Vector<AbstractExam> examEntries = model.getEntries();

        try {
            ExamIO.save(file, examEntries);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Checks if the table content is saved. In case the data are not saved opens a
     * {@link javax.swing.JOptionPane} asking to the user whether to save or not
     */
    @Override
    public void windowClosing(WindowEvent e) {
        if (!isSaved.get()) {
            int result = JOptionPane.showConfirmDialog(fileChooser,
                    "Your file is not saved. Do you want to save it?", "File not saved",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION && saveFile()) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (result == JOptionPane.NO_OPTION) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else {
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }
}