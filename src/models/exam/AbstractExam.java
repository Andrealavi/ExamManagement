/**
 * @author Andrea Lavino (176195)
 * 
 * @package models.exam
 */
package models.exam;

/**
 * Abstract class for exams. Created to take advantage of polymorphism
 * 
 * @param <E> Grade attribute type. It can be an Integer or an ArrayList
 * 
 * @see models.exam.SimpleExam
 * @see models.exam.ComposedExam
 */
public abstract class AbstractExam<E> {
    /**
     * Student first name
     */
    private String firstName;

    /**
     * Student last name
     */
    private String lastName;

    /**
     * Class name
     */
    private String className;

    /**
     * Exam grade
     */
    private E grade;

    /**
     * Exam credits
     */
    private Integer credits;

    /**
     * Inizialize exam attributes using data passed as arguments
     * 
     * @param firstName Student first name
     * @param lastName  Student last name
     * @param className Class name
     * @param grade     Exam grade
     * @param credits   Number of credits of the exam
     */
    public AbstractExam(String firstName, String lastName, String className, E grade, Integer credits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.grade = grade;
        this.credits = credits;
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

    /**
     * Gets the grade object
     * 
     * @return grade object
     */
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