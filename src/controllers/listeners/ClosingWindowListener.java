package controllers.listeners;

import java.awt.event.*;
import javax.swing.*;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

import models.exam.*;
import views.dialogs.ErrorDialog;
import models.ExamsTableModel;

import java.io.File;

public class ClosingWindowListener extends WindowAdapter {
    private JFrame frame;
    private AtomicBoolean isSaved;
    private JFileChooser fileChooser;
    private ExamsTableModel model;

    public ClosingWindowListener(JFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.fileChooser = fileChooser;
        this.model = model;
        this.isSaved = isSaved;
    }

    public Boolean approveOverwrite(File f) {
        if (f.exists()) {
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

    public void saveFile() {
        ExamIO f = null;

        if (fileChooser.getSelectedFile() != null) {
            f = new ExamIO(fileChooser.getSelectedFile());

        } else {
            int returnVal = fileChooser.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                if (approveOverwrite(fileChooser.getSelectedFile())) {
                    f = new ExamIO(fileChooser.getSelectedFile());
                } else {
                    fileChooser.cancelSelection();
                }
            }
        }

        Vector<AbstractExam> examEntries = model.getEntries();

        try {
            f.save(examEntries);
        } catch (Exception e) {
            ErrorDialog errorDialog = new ErrorDialog(frame, e.getMessage());
            errorDialog.getButton().addActionListener(new CloseButtonListener(errorDialog));
        }
    }

    public void windowClosing(WindowEvent e) {
        if (!isSaved.get()) {
            int result = JOptionPane.showConfirmDialog(fileChooser,
                    "Your file is not saved. Do you want to save it?", "File not saved",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                saveFile();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (result == JOptionPane.NO_OPTION) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            } else if (result == JOptionPane.CANCEL_OPTION) {
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    }
}