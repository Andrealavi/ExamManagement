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
    private Integer finalGrade;
    private ArrayList<Float> weights;

    /**
     * Calls super constructor, sets grade and weights and compute the final exam
     * grade
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param grade     list of partial exams grade
     * @param weights   list of partial exams weights
     * @param credits   number of credits of the exam
     */
    public ComposedExam(String firstName, String lastName, String className, ArrayList<Integer> grade,
            ArrayList<Float> weights,
            Integer credits) {
        super(firstName, lastName, className, credits);

        this.grade = grade;
        this.weights = weights;

        computeGrade();
    }

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
    public ComposedExam(String firstName, String lastName, String className, String credits) throws ExamInfoException {
        super(firstName, lastName, className, credits);
    }

    /**
     * Computes exam final grade
     */
    private void computeGrade() {
        float sum = 0.0f;
        for (int i = 0; i < grade.size(); i++) {
            sum += grade.get(i) * weights.get(i);
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
    public Integer getGrade() {
        return finalGrade;
    }

    /**
     * Sets partial exams information
     * 
     * @param partialExams Partial exams data
     * @param examNumbers  Number of partial exams
     * @throws ExamInfoException Exception that is thrown if data are invalid
     */
    public void setPartialExamsInfo(String[][] partialExams, Integer examNumbers) throws ExamInfoException {
        grade = new ArrayList<Integer>();
        weights = new ArrayList<Float>();

        for (int i = 0; i < examNumbers; i++) {
            grade.add(Integer.parseInt(partialExams[i][0]));
            weights.add(Float.parseFloat(partialExams[i][1]));
        }

        Float sum = 0.0f;
        for (Float weight : weights) {
            sum += weight;
        }

        if (sum.equals(0.99f)) {
            sum = 1.0f;
        }

        for (Integer grade : grade) {
            if (grade < 18) {
                throw new ExamInfoException(
                        "Invalid grade value." + " Please insert a value greater than 18");
            }
        }

        if (!sum.equals(1.0f)) {
            throw new ExamInfoException("Invalid value in partial exams weights. Please be sure they add up to 100%");
        }

        computeGrade();
    }

    /**
     * Gets partial exams grade as a {@link java.lang.String} array
     * 
     * @return {@link java.lang.String} array containing partial exams grade
     */
    public String[] getPartialExamsGrades() {
        String[] gradeStringArray = new String[grade.size()];

        for (int i = 0; i < grade.size(); i++) {
            gradeStringArray[i] = grade.get(i).toString();
        }

        return gradeStringArray;
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

        for (int i = 0; i < grade.size(); i++) {
            gradeWeightStringArray.append(String.format("%s %s,", grade.get(i).toString(), weights.get(i).toString()));
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
        String[] stringArray =

                { super.getFirstName(), super.getLastName(), super.getClassName(),
                        super.getCredits().toString(), getGradeWeightCouple() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        StringBuffer outputStringBuffer = new StringBuffer(
                "composed" + "," + super.getFirstName() + "," + super.getLastName() + "," + super.getClassName()
                        + "," + super.getCredits().toString() + "," + grade.size());

        for (int i = 0; i < grade.size(); i++) {
            outputStringBuffer.append("," +
                    grade.get(i).toString() + "," + weights.get(i).toString());
        }

        return outputStringBuffer.toString();
    }
}