package views.dialogs;

import javax.swing.*;

public abstract class AbstractExamDialog extends JDialog {
    protected JLabel[] generalLabels;
    protected JTextField[] generalFields;
    protected JButton actionButton;

    AbstractExamDialog() {
        super();
    }

    AbstractExamDialog(JFrame frame, String title) {
        super(frame, title);
    }

    public JButton getButton() {
        return actionButton;
    }

    public abstract String[] getFieldsData();
}
