package controllers.listeners.io;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import controllers.listeners.CloseButtonListener;

import java.util.concurrent.atomic.AtomicBoolean;

import models.ExamsTableModel;
import models.exam.AbstractExam;
import models.exam.ExamIO;
import views.dialogs.ErrorDialog;

public class LoadFileListener implements ActionListener {
    private JFrame frame;
    private ExamsTableModel model;
    private JFileChooser fileChooser;
    private AtomicBoolean isSaved;

    public LoadFileListener(JFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File("../documents"));
        this.isSaved = isSaved;
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(frame);
        ExamIO fileIO;

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileIO = new ExamIO(fileChooser.getSelectedFile());

            try {
                Vector<AbstractExam> examEntries = fileIO.load();

                model.setEntries(examEntries);
            } catch (FileNotFoundException notFound) {
                ErrorDialog errorDialog = new ErrorDialog(frame,
                        "The file you chose doesn't exist.\nPlease choose an existing file");
                errorDialog.getButton().addActionListener(new CloseButtonListener(errorDialog));

                fileChooser.cancelSelection();
            } catch (Exception generalException) {
                ErrorDialog errorDialog = new ErrorDialog(frame,
                        generalException.getMessage());
                errorDialog.getButton().addActionListener(new CloseButtonListener(errorDialog));
            }

        }

        isSaved.set(true);

        frame.pack();
    }
}
