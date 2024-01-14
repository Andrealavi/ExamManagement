/**
 * @author Andrea Lavino (176195)
 * 
 * @package models.exam
 */
package models.exam;

/**
 * Abstract class for exams. Created to take advantage of polymorphism
 * 
 * @see models.exam.SimpleExam
 * @see models.exam.ComposedExam
 */
public abstract class AbstractExam<E> {
    private String firstName;
    private String lastName;
    private String className;
    private E grade;
    private Integer credits;

    /**
     * Inizialize exam attributes using data passed as arguments
     * 
     * @param firstName Student first name
     * @param lastName  Student last name
     * @param className Class name
     * @param credits   Number of credits of the exam
     * @throws ExamInfoException Exception that is thrown when credits number is
     *                           wrong
     */
    public AbstractExam(String firstName, String lastName, String className, E grade, Integer credits)
            throws ExamInfoException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.grade = grade;

        if (credits <= 0 || credits > 18) {
            throw new ExamInfoException("Invalid credits value. Please insert a value between 0 and 18.");
        } else {
            this.credits = credits;
        }
    }

    /**
     * Gets student first name
     * 
     * @return {@link java.lang.String} containing the student first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets student last name
     * 
     * @return {@link java.lang.String} containing the student last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the name of the class
     * 
     * @return {@link java.lang.String} containing the class name
     */
    public String getClassName() {
        return className;
    }

    public E getGrade() {
        return grade;
    }

    /**
     * Gets exam credits
     * 
     * @return {@link java.lang.Integer} containing the number of credits of the
     *         exam
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * Gets honors
     * 
     * @return {@link java.lang.Boolean} which tells if the the student got honors
     *         for the exam
     */
    public abstract String getHonor();

    /**
     * Gets student grade
     * 
     * @return {@link java.lang.Integer} containing student grade
     */
    public abstract Integer getExamGrade();

    /**
     * Converts exam data into a {@link java.lang.String} array
     * 
     * @return array containing exam data
     */
    public abstract String[] toStringArray();

    /**
     * Converts exam data into a {@link java.lang.String} that follows a specific
     * pattern used when writing entries to file
     * 
     * @return {@link java.lang.String} containing exam data in a specific format
     */
    public abstract String toOutputString();
}