/**
 * @author Andrea Lavino (176195)
 * 
 * @package models.exam
 */
package models.exam;

import java.util.ArrayList;

/**
 * Implements composed exam entry. Extends the abstract class
 * {@link models.exam.AbstractExam}
 * 
 * @see models.exam.AbstractExam
 */
public class ComposedExam extends AbstractExam<ArrayList<Integer>> {
    private ArrayList<Float> weights;
    private Integer finalGrade;
    private Boolean honor;

    /**
     * Calls super constructor, sets grade and weights and compute the final exam
     * grade
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param credits   number of credits
     * @throws ExamInfoException Exception that is trown when exam data are invalid
     */
    public ComposedExam(String firstName, String lastName, String className, ArrayList<Integer> grades,
            ArrayList<Float> weights,
            Integer credits)
            throws ExamInfoException {
        super(firstName, lastName, className, grades, credits);
        this.weights = weights;

        computeGrade();
    }

    /**
     * Computes exam final grade
     */
    private void computeGrade() {
        float sum = 0.0f;
        for (int i = 0; i < super.getGrade().size(); i++) {
            sum += super.getGrade().get(i) * weights.get(i);
        }

        if (sum > 30) {
            finalGrade = 30;
        } else {
            finalGrade = Math.round(sum);
        }

        honor = sum > 30;
    }

    @Override
    /**
     * Gets exam grade
     * 
     * @return {@link java.lang.Integer} containing exam grade
     */
    public Integer getExamGrade() {
        return finalGrade;
    }

    /**
     * Gets partial exams weights as a {@link java.lang.String} array
     * 
     * @return {@link java.lang.String} array containing partial exams weights
     */
    public String[] getPartialExamsWeights() {
        String[] weightsStringArray = new String[weights.size()];

        for (int i = 0; i < weights.size(); i++) {
            weightsStringArray[i] = weights.get(i).toString();
        }

        return weightsStringArray;
    }

    public String getGradeWeightCouple() {
        StringBuffer gradeWeightStringArray = new StringBuffer();

        for (int i = 0; i < super.getGrade().size(); i++) {
            gradeWeightStringArray
                    .append(String.format("%s %s,", super.getGrade().get(i).toString(), weights.get(i).toString()));
        }

        return gradeWeightStringArray.toString();
    }

    /**
     * Gets exam honor
     * 
     * @return {@link java.lang.String} containing honor value
     */
    public String getHonor() {
        return honor ? "Yes" : "No";
    }

    @Override
    public String[] toStringArray() {
        String[] stringArray = { super.getFirstName(), super.getLastName(), super.getClassName(),
                super.getCredits().toString(), getGradeWeightCouple() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        StringBuffer outputStringBuffer = new StringBuffer(
                "composed" + "," + super.getFirstName() + "," + super.getLastName() + "," + super.getClassName()
                        + "," + super.getCredits().toString() + "," + super.getGrade().size());

        for (int i = 0; i < super.getGrade().size(); i++) {
            outputStringBuffer.append("," +
                    super.getGrade().get(i).toString() + "," + weights.get(i).toString());
        }

        return outputStringBuffer.toString();
    }
}