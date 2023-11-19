package controllers.listeners.io;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.concurrent.atomic.AtomicBoolean;

import models.ExamsRowSorter;
import models.ExamsTableModel;
import models.exam.AbstractExam;
import models.exam.ExamIO;
import views.*;

public class LoadFileListener implements ActionListener {
    private AppFrame frame;
    private ExamsTableModel model;
    private JFileChooser fileChooser;
    private AtomicBoolean isSaved;

    public LoadFileListener(AppFrame frame, JFileChooser fileChooser, ExamsTableModel model, AtomicBoolean isSaved) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File("./documents"));
        this.isSaved = isSaved;
        System.out.println();
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
