package views.dialogs;

import javax.swing.JButton;

public interface ModifyExamDialogInterface {
    public JButton getModifyButton();

    public JButton getRemoveButton();

    public void setEntryFields(String[] fieldsData);
}
