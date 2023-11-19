package controllers.listeners.modify;

import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

import controllers.listeners.CloseButtonListener;
import views.dialogs.*;
import models.exam.ComposedExam;
import models.exam.ExamInfoException;
import models.exam.*;
import models.ExamsTableModel;

public class ModifyExamDialogListener implements ActionListener {
    private AbstractExamDialog dialog;
    private ExamsTableModel model;
    private int row;
    private AtomicBoolean isSaved;

    public ModifyExamDialogListener(AbstractExamDialog dialog, ExamsTableModel model, int row,
            AtomicBoolean isSaved) {
        this.dialog = dialog;
        this.model = model;
        this.row = row;
        this.isSaved = isSaved;
    }

    private SimpleExam createSimpleEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

        return examEntry;
    }

    private ComposedExam createComposedEntry() throws ExamInfoException {
        String[] generalInfo = dialog.getFieldsData();

        ComposedExam examEntry = new ComposedExam(generalInfo[0], generalInfo[1], generalInfo[2], generalInfo[3]);
        examEntry.setPartialExamsInfo(((ModifyComposedExamDialog) dialog).getExamsData(),
                ((ModifyComposedExamDialog) dialog).getExamNumber());

        return examEntry;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            AbstractExam examEntry;

            if (dialog.getClass().getSimpleName().equals("ModifySimpleExamDialog")) {
                examEntry = createSimpleEntry();
            } else {
                examEntry = createComposedEntry();
            }

            model.updateEntryAtRow(examEntry, row);

            isSaved.set(false);

            dialog.dispose();
        } catch (ExamInfoException err) {
            ErrorDialog errDialog = new ErrorDialog(dialog, err.getMessage());

            errDialog.getButton().addActionListener(new CloseButtonListener(errDialog));
        } catch (NumberFormatException err) {
            ErrorDialog errDialog = new ErrorDialog(dialog,
                    "Invalid value inserted.\nPlease make sure you have inserted valid number values for grade and credits fields.");

            errDialog.getButton().addActionListener(new CloseButtonListener(errDialog));
        }

    }
}