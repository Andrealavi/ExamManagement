/**
 * @author Andrea Lavino (176195)
 * 
 * @package models.exam
 */
package models.exam;

/**
 * Implements simple exam entry. Extends the abstract class
 * {@link models.exam.AbstractExam}
 * 
 * @see models.exam.AbstractExam
 */
public class SimpleExam extends AbstractExam<Integer> {
    /**
     * Calls super constructor
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param grade     student grade
     * @param credits   exam credits number
     * @param honor     student honors
     * 
     * @see models.exam.AbstractExam
     */
    public SimpleExam(String firstName, String lastName, String className, Integer grade, Integer credits,
            boolean honor) {
        super(firstName, lastName, className, credits);
        this.grade = grade;
        this.honor = honor;
    }

    /**
     * Calls super constructor
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param grade     student grade
     * @param credits   exam credits number
     * @param honor     student honors
     * 
     * @throws ExamInfoException Exception that is thrown when exam data are invalid
     * 
     * @see models.exam.AbstractExam
     */
    public SimpleExam(String firstName, String lastName, String className, String grade, String credits)
            throws ExamInfoException {
        super(firstName, lastName, className, credits);

        if (Integer.parseInt(grade) < 18) {
            throw new ExamInfoException("Invalid grade value." + " Please insert a value greater than 18.");
        } else {

            this.grade = Integer.parseInt(grade);

            if (this.grade > 30) {
                this.grade = 30;
                this.honor = true;
            } else {
                this.honor = false;
            }
        }
    }

    @Override
    public Integer getGrade() {
        return grade;
    }

    /**
     * Sets the student grade using the value given
     * 
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * Gets a string with a value depending on wether the students got honor or not
     * 
     * @return {@link java.lang.String} containing honor value
     */
    public String getHonor() {
        return honor ? "Yes" : "No";
    }

    @Override
    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, className, grade.toString(), credits.toString(), getHonor() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        String outputString = "simple" + "," + firstName + "," + lastName + "," + className + "," + grade.toString()
                + ","
                + credits.toString();

        return outputString;
    }
}