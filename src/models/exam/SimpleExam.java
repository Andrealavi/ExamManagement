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
    private Boolean honor;

    /**
     * Calls super constructor
     * 
     * @param firstName student first name
     * @param lastName  student last name
     * @param className class name
     * @param grade     student grade
     * @param credits   exam credits number
     * @param honor     student honor
     * 
     * @see models.exam.AbstractExam
     */
    public SimpleExam(String firstName, String lastName, String className, Integer grade, Integer credits,
            Boolean honor) {
        super(firstName, lastName, className, grade, credits);

        this.honor = honor;
    }

    @Override
    public Integer getExamGrade() {
        return super.getGrade();
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
        String[] stringArray = { super.getFirstName(), super.getLastName(), super.getClassName(),
                super.getGrade().toString(),
                super.getCredits().toString() };

        return stringArray;
    }

    @Override
    public String toOutputString() {
        String outputString = "simple" + "," + super.getFirstName() + "," + super.getLastName() + ","
                + super.getClassName() + "," + super.getGrade().toString()
                + ","
                + super.getCredits().toString() + "," + this.honor;

        return outputString;
    }
}