/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers.listener
 */
package controllers.listeners;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import controllers.listeners.filter.GeneralFilterListener;
import views.dialogs.*;
import models.exam.*;

/**
 * Extends {@link controllers.listeners.filter.GeneralFilterListener} in order
 * to implement the methods used for adding and modifying an exam
 * 
 * @see controllers.listeners.filter.GeneralFilterListener
 * @see views.dialogs.AbstractExamDialog
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
     * @param isFiltered Boolean containing filter state of the exam table data
     */
    public ExamDialogListener(AbstractExamDialog dialog, AtomicBoolean isFiltered) {
        super(dialog, isFiltered);
        this.dialog = (AbstractExamDialog) super.getDialog();
    }

    /**
     * Returns the dialog with the data of the exam
     * 
     * @return dialog attribute
     */
    protected AbstractExamDialog getDialog() {
        return dialog;
    }

    /**
     * Creates a {@link models.exam.SimpleExam} entry using data passed as argument
     * 
     * @param data exam data
     * 
     * @return Simple exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    protected final SimpleExam createSimpleExamEntry(String[] data) throws ExamInfoException {
        if (Integer.parseInt(data[2]) < 18) {
            throw new ExamInfoException("Grade value must be at least 18");
        } else if (Integer.parseInt(data[3]) <= 0 || Integer.parseInt(data[3]) > 18) {
            throw new ExamInfoException("Credits value must be between 1 and 18");
        }

        Integer grade = Integer.parseInt(data[2]);
        Boolean honor = grade > 30;

        if (grade > 30) {
            grade = 30;
        }

        SimpleExam examEntry = new SimpleExam(data[0], data[1], grade,
                Integer.parseInt(data[3]), honor);

        return examEntry;
    }

    /**
     * Creates a {@link models.exam.ComposedExam} entry using data passed as
     * argument
     * 
     * @param data exam data
     * 
     * @return Composed exam entry
     * @throws ExamInfoException Exception that is thrown if exam data are invalid
     */
    protected final ComposedExam createComposedExamEntry(String[] data) throws ExamInfoException {
        ArrayList<Integer> grades = new ArrayList<Integer>();
        ArrayList<Float> weights = new ArrayList<Float>();

        if (Integer.parseInt(data[2]) < 0 || Integer.parseInt(data[2]) > 18) {
            throw new ExamInfoException("Credits value must be between 1 and 18");
        }

        String[] partialExamsData = data[3].split(",");

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

        ComposedExam examEntry = new ComposedExam(data[0], data[1], grades, weights,
                Integer.parseInt(data[2]));

        return examEntry;
    }
}