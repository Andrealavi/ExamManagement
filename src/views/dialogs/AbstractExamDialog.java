/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialogs
 */
package views.dialogs;

import javax.swing.*;

/**
 * An abstract class for exams dialogs. Created to apply polymorphism for other
 * exam dialogs.
 * 
 * @see views.dialogs.AddSimpleExamDialog
 * @see views.dialogs.AddComposedExamDialog
 * @see views.dialogs.ModifySimpleExamDialog
 * @see views.dialogs.ModifyComposedExamDialog
 * @see javax.swing.JDialog
 */
public abstract class AbstractExamDialog extends JDialog {
    /**
     * Array of {@link javax.swing.JLabel} for exam general info labels
     */
    protected JLabel[] generalLabels;

    /**
     * Array of {@link javax.swing.JTextField} for exam general info text fields
     */
    protected JTextField[] generalFields;

    /**
     * {@link javax.swing.JButton} for executing an action
     */
    protected JButton actionButton;

    /**
     * Class constructor. Takes two arguments and calls the constructor of
     * {@link javax.swing.JDialog}
     * 
     * @param frame Dialog parent
     * @param title Dialog title
     */
    AbstractExamDialog(JFrame frame, String title) {
        super(frame, title);
    }

    /**
     * Gets {@link AbstractExamDialog#actionButton}
     * 
     * @return Dialog button
     */
    public JButton getButton() {
        return actionButton;
    }

    /**
     * Gets fields data. It is an abstract method because simple and composed
     * dialogs have a different fields structure
     * 
     * @return Data within text fields as an array of strings
     */
    public abstract String[] getFieldsData();
}
