package controllers.listeners;

import java.awt.event.*;
import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import controllers.ExamIO;
import models.ExamsTableModel;
import models.exam.AbstractExam;

public class SaveFileListener implements ActionListener {
    private JFrame frame;
    private ExamsTableModel model;
    private JFileChooser fileChooser;

    public SaveFileListener(JFrame frame, JFileChooser fileChooser, ExamsTableModel model) {
        this.frame = frame;
        this.model = model;
        this.fileChooser = fileChooser;
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    public void actionPerformed(ActionEvent e) {
        ExamIO f = null;

        if (fileChooser.getSelectedFile() != null) {
            f = new ExamIO(fileChooser.getSelectedFile());

        } else {
            int returnVal = fileChooser.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                f = new ExamIO(fileChooser.getSelectedFile());
            }
        }

        Vector<AbstractExam> examEntries = model.getEntries();

        f.save(examEntries);
    }
}
