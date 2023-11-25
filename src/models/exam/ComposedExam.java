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
public class ComposedExam extends AbstractExam {
    private ArrayList<Integer> grades;
    private ArrayList<Float> weights;

    /**
     * Calls super constructor, sets grades and weights and compute the final exam
     * grade
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param grades    list of partial exams grades
     * @param weights   list of partial exams weights
     * @param credits   number of credits of the exam
     */
    public ComposedExam(String firstName, String lastName, String className, ArrayList<Integer> grades,
            ArrayList<Float> weights,
            Integer credits) {
        super(firstName, lastName, className, credits);

        this.grades = grades;
        this.weights = weights;

        computeGrade();
    }

    /**
     * Calls super constructor, sets grades and weights and compute the final exam
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
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i) * weights.get(i);
        }

        if (sum > 30) {
            grade = 30;
        } else {
            grade = (int) sum;
        }

        honor = sum > 30;
    }

    /**
     * Gets exam grade
     * 
     * @return {@link java.lang.Integer} containing exam grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets partial exams information
     * 
     * @param partialExams Partial exams data
     * @param examNumbers  Number of partial exams
     * @throws ExamInfoException Exception that is thrown if data are invalid
     */
    public void setPartialExamsInfo(String[][] partialExams, Integer examNumbers) throws ExamInfoException {
        grades = new ArrayList<Integer>();
        weights = new ArrayList<Float>();

        for (int i = 0; i < examNumbers; i++) {
            grades.add(Integer.parseInt(partialExams[i][0]));
            weights.add(Float.parseFloat(partialExams[i][1]));
        }

        Float sum = 0.0f;
        for (Float weight : weights) {
            sum += weight;
        }

        if (sum.equals(0.99f)) {
            sum = 1.0f;
        }

        for (Integer grade : grades) {
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
     * Gets partial exams grades as a {@link java.lang.String} array
     * 
     * @return {@link java.lang.String} array containing partial exams grades
     */
    public String[] getPartialExamsGrades() {
        String[] gradesStringArray = new String[grades.size()];

        for (int i = 0; i < grades.size(); i++) {
            gradesStringArray[i] = grades.get(i).toString();
        }

        return gradesStringArray;
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
        String[] stringArray = { firstName, lastName, className, grade.toString(), credits.toString(),
                getHonor() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        StringBuffer outputStringBuffer = new StringBuffer(
                "composed" + "," + firstName + "," + lastName + "," + className
                        + "," + credits.toString() + "," + grades.size());

        for (int i = 0; i < grades.size(); i++) {
            outputStringBuffer.append("," +
                    grades.get(i).toString() + "," + weights.get(i).toString());
        }

        return outputStringBuffer.toString();
    }
}