package controllers.listeners.io;

import java.awt.event.*;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import models.ExamsTableModel;
import models.exam.AbstractExam;
import models.exam.ExamIO;

public class SaveFileListener implements ActionListener {
    private JFrame frame;
    private ExamsTableModel model;
    private JFileChooser fileChooser;
    private AtomicBoolean isSaved;

    public SaveFileListener(JFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File("./documents"));
        this.isSaved = isSaved;
    }

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

    public void actionPerformed(ActionEvent e) {
        ExamIO fileIO = null;

        if (fileChooser.getSelectedFile() != null) {
            fileIO = new ExamIO(fileChooser.getSelectedFile());

        } else {
            int returnVal = fileChooser.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (approveOverwrite(fileChooser.getSelectedFile())) {
                    fileIO = new ExamIO(fileChooser.getSelectedFile());
                } else {
                    fileChooser.cancelSelection();
                }
            }
        }

        Vector<AbstractExam> examEntries = model.getEntries();

        try {
            fileIO.save(examEntries);
        } catch (NullPointerException nullPointerException) {
            // I don't want to manage this exception, but I don't want to have errors on
            // terminal
        } catch (Exception error) {
            JOptionPane.showMessageDialog(frame, error.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
        }

        isSaved.set(true);
    }
}
