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
    private JLabel[] generalLabels;

    /**
     * Array of {@link javax.swing.JTextField} for exam general info text fields
     */
    private JTextField[] generalFields;

    /**
     * {@link javax.swing.JButton} for executing an action
     */
    private JButton actionButton;

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
     * Sets the {@link views.dialogs.AbstractExamDialog#actionButton} with the given
     * button
     * 
     * @param button
     */
    protected void setButton(JButton button) {
        this.actionButton = button;
    }

    /**
     * Gets the {@link views.dialogs.AbstractExamDialog#generalLabels}
     * 
     * @return Dialog general labels
     */
    protected JLabel[] getGeneralLabels() {
        return generalLabels;
    }

    /**
     * Sets the {@link views.dialogs.AbstractExamDialog#generalFields}
     * 
     * @return Dialog general fields
     */
    public JTextField[] getGeneralFields() {
        return generalFields;
    }

    /**
     * Sets the {@link views.dialogs.AbstractExamDialog#generalLabels} with the
     * given
     * labels
     * 
     * @param labels
     */
    protected void setLabels(JLabel[] labels) {
        generalLabels = labels;
    }

    /**
     * Sets the {@link views.dialogs.AbstractExamDialog#generalLabels} with the
     * given fields
     * 
     * @param fields
     */
    protected void setFields(JTextField[] fields) {
        generalFields = fields;
    }

    /**
     * Gets fields data. It is an abstract method because simple and composed
     * dialogs have a different fields structure
     * 
     * @return Data within text fields as an array of strings
     */
    public abstract String[] getFieldsData();
}
