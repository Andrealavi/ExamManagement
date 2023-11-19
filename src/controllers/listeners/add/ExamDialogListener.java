package controllers.listeners.add;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JOptionPane;

import views.dialogs.*;
import models.exam.*;
import models.ExamsTableModel;

public class ExamDialogListener implements ActionListener {
    private AbstractExamDialog dialog;
    private ExamsTableModel model;
    private AtomicBoolean isSaved;

    public ExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, AtomicBoolean isSaved) {
        this.dialog = dialog;
        this.model = model;
        this.isSaved = isSaved;
    }

    private SimpleExam createSimpleExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

        return examEntry;
    }

    private ComposedExam createComposedExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        String[][] partialExamsData = ((AddComposedExamDialog) dialog).getExamsData();

        ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
        examEntry.setPartialExamsInfo(partialExamsData, ((AddComposedExamDialog) dialog).getExamNumber());

        return examEntry;
    }

    public void actionPerformed(ActionEvent e) {

        try {
            AbstractExam examEntry;

            if (dialog.getClass().getSimpleName().equals("AddSimpleExamDialog")) {
                examEntry = createSimpleExamEntry();
            } else {
                examEntry = createComposedExamEntry();
            }

            model.addEntry(examEntry);

            isSaved.set(false);

            dialog.dispose();
        } catch (ExamInfoException err) {
            JOptionPane.showMessageDialog(dialog, err.getMessage(), "Error message", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException err) {
            JOptionPane.showMessageDialog(dialog, "Invalid value inserted.\\n" +
                    "Please make sure you have inserted valid number values for grade and credits fields.",
                    "Error message", JOptionPane.ERROR_MESSAGE);
        }

    }
}