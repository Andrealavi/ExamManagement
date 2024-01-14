/**
 * @author Andrea Lavino (176195)
 * 
 * @package views.dialog
 */
package views.dialogs;

import javax.swing.JButton;

/**
 * A simple interface used in
 * {@link controllers.listeners.DoubleClickOnEntryListener} in order to use
 * polymorphism
 */
public interface ModifyExamDialogInterface {
    /**
     * Gets the action button
     * 
     * @return action button
     */
    public JButton getButton();

    /**
     * Get the modify button
     * 
     * @return modify button
     */
    public JButton getModifyButton();

    /**
     * Gets the remove button
     * 
     * @return remove button
     */
    public JButton getRemoveButton();

    /**
     * Sets entry fields using data passed as argument
     * 
     * @param fieldsData fields data passed as a {@link java.lang.String} array
     */
    public void setEntryFields(String[] fieldsData);
}
