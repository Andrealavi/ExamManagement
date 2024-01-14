package controllers.listeners;

import java.util.concurrent.atomic.AtomicBoolean;

import views.dialogs.*;
import models.exam.*;

/**
 * Implements {@link java.awt.event.ActionListener} interface to create an event
 * listener to add a new exam to the exam table.
 * 
 * @see views.dialogs.AddComposedExamDialog
 * @see models.ExamsTableModel
 * @see java.awt.event.ActionListener
 */
public class ExamDialogListener extends GeneralFilterListener {
    /**
     * Dialog with exam data
     */
    private AbstractExamDialog dialog;

    /**
     * Instantiates class attributes using all the function arguments
     * 
     * @param dialog     Dialog with exam data
     * @param model      Table model
     * @param isSaved    Boolean containing save state of the exam table data
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public ExamDialogListener(AbstractExamDialog dialog, AtomicBoolean isFiltered) {
        super(dialog, isFiltered);
        this.dialog = (AbstractExamDialog) super.getDialog();
    }

    protected AbstractExamDialog getDialog() {
        return dialog;
    }

    protected AtomicBoolean getFilterBoolean() {
        return super.getFilterBoolean();
    }

    /**
     * Creates a {@link models.exam.SimpleExam} entry using data taken from
     * {@link controllers.listeners.add.AddExamDialogListener#dialog}
     * 
     * @return Simple exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    protected SimpleExam createSimpleExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], data[3], data[4]);

        return examEntry;
    }

    /**
     * Creates a {@link models.exam.ComposedExam} entry using data taken from
     * {@link controllers.listeners.add.AddExamDialogListener#dialog}
     * 
     * @return Composed exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    protected ComposedExam createComposedExamEntry() throws ExamInfoException {
        String[] data = dialog.getFieldsData();
        String[][] partialExamsData = ((AddComposedExamDialog) dialog).getExamsData();

        ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], data[3]);
        examEntry.setPartialExamsInfo(partialExamsData, ((AddComposedExamDialog) dialog).getExamNumber());

        return examEntry;
    }
}