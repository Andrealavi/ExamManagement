package controllers.listeners;

import java.util.ArrayList;
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
    protected final SimpleExam createSimpleExamEntry(String[] data) throws ExamInfoException {
        if (Integer.parseInt(data[3]) < 18) {
            throw new ExamInfoException("Grade value must be at least 18");
        } else if (Integer.parseInt(data[4]) <= 0 || Integer.parseInt(data[4]) > 18) {
            throw new ExamInfoException("Credits value must be between 1 and 18");
        }

        Integer grade = Integer.parseInt(data[3]);

        if (grade > 30) {
            grade = 30;
        }

        SimpleExam examEntry = new SimpleExam(data[0], data[1], data[2], grade,
                Integer.parseInt(data[4]));

        return examEntry;
    }

    /**
     * Creates a {@link models.exam.ComposedExam} entry using data taken from
     * {@link controllers.listeners.add.AddExamDialogListener#dialog}
     * 
     * @return Composed exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    protected final ComposedExam createComposedExamEntry(String[] data) throws ExamInfoException {
        ArrayList<Integer> grades = new ArrayList<Integer>();
        ArrayList<Float> weights = new ArrayList<Float>();

        String[] partialExamsData = data[4].split(",");

        for (int i = 0; i < partialExamsData.length; i++) {
            String[] partialExamData = partialExamsData[i].split(" ");

            grades.add(Integer.parseInt(partialExamData[0]));
            weights.add(Float.parseFloat(partialExamData[1]));
        }

        Float weightsSum = 0.0f;

        for (float weight : weights) {
            weightsSum += weight;
        }

        if (weightsSum.equals(0.99f)) {
            weightsSum = 1.0f;
        }

        if (!weightsSum.equals(1.0f)) {
            throw new ExamInfoException("Weight values must sum up to 100%");
        }

        for (int grade : grades) {
            if (grade < 18) {
                throw new ExamInfoException("All grade values must be at least 18");
            }
        }

        ComposedExam examEntry = new ComposedExam(data[0], data[1], data[2], grades, weights,
                Integer.parseInt(data[3]));

        return examEntry;
    }
}