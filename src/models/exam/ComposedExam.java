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
     * Calls super constructor, sets weights and compute the final exam
     * grade
     * 
     * @param studentName student's name
     * @param className   class name
     * @param grades      partial exams grades
     * @param weights     partial exams weights
     * @param credits     number of credits
     */
    public ComposedExam(String studentName, String className, ArrayList<Integer> grades,
            ArrayList<Float> weights,
            Integer credits) {
        super(studentName, className, grades, credits);
        this.weights = weights;

        computeFinalGrade();
    }

    /**
     * Computes exam final grade
     */
    private void computeFinalGrade() {
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

    /**
     * Gets exam grade
     * 
     * @return {@link java.lang.Integer} containing exam grade
     */
    @Override
    public Integer getFinalGrade() {
        return finalGrade;
    }

    /**
     * Gets a string with all the partial exams weights and grades organized in
     * couples
     * 
     * @return a string with all the couples divided by a comma
     */
    private String getGradeWeightCouple() {
        StringBuffer gradeWeightStringArray = new StringBuffer();

        for (int i = 0; i < super.getGrade().size(); i++) {
            gradeWeightStringArray
                    .append(String.format("%s %s,", super.getGrade().get(i).toString(), weights.get(i).toString()));
        }

        return gradeWeightStringArray.toString();
    }

    /**
     * Gets exam honor in a string format
     * 
     * @return {@link java.lang.String} containing honor value
     */
    public String getHonor() {
        return honor ? "Yes" : "No";
    }

    @Override
    public String[] toStringArray() {
        String[] stringArray = { super.getStudentName(), super.getClassName(),
                super.getCredits().toString(), getGradeWeightCouple() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        StringBuffer outputStringBuffer = new StringBuffer(
                "composed" + "," + super.getStudentName() + "," + super.getClassName()
                        + "," + super.getCredits().toString() + "," + super.getGrade().size());

        for (int i = 0; i < super.getGrade().size(); i++) {
            outputStringBuffer.append("," +
                    super.getGrade().get(i).toString() + "," + weights.get(i).toString());
        }

        return outputStringBuffer.toString();
    }
}