/**
 * @author Andrea Lavino (176195)
 * 
 * @package models.exam
 */
package models.exam;

/**
 * Implements a custom exception extending {@link java.lang.Exception}
 * 
 * @see java.lang.Exception
 */
public class ExamInfoException extends Exception {
    /**
     * Calls super constructor
     * 
     * @param errorMessage Error message of the exception
     */
    public ExamInfoException(String errorMessage) {
        super(errorMessage);
    }
}