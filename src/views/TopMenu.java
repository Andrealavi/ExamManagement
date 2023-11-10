package views;

import javax.swing.*;
import java.awt.*;

class TopMenu extends JMenuBar {
    private JMenu fileMenu;
    private JMenu examMenu;

    public TopMenu() {
        fileMenu = new JMenu("File");
        examMenu = new JMenu("Exam");

        JMenuItem loadFileItem = new JMenuItem("Load File");
        JMenuItem saveFileItem = new JMenuItem("Save File");
        JMenuItem printFileItem = new JMenuItem("Print File");

        fileMenu.add(loadFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(printFileItem);

        JMenu addExamMenu = new JMenu("Add Exam");
        JMenuItem removeExamItem = new JMenuItem("Remove Exam/Exams");
        JMenuItem filterExamItem = new JMenuItem("Filter Exams");

        JMenuItem simpleExamItem = new JMenuItem("Add simple exam");
        JMenuItem composedExamItem = new JMenuItem("Add composed exam");

        addExamMenu.add(simpleExamItem);
        addExamMenu.add(composedExamItem);

        examMenu.add(addExamMenu);
        examMenu.add(removeExamItem);
        examMenu.add(filterExamItem);

        add(fileMenu);
        add(examMenu);

        setVisible(true);
    }

    public JMenuItem[] getFileMenuItems() {
        JMenuItem[] fileItems = { fileMenu.getItem(0), fileMenu.getItem(1), fileMenu.getItem(2) };
        return fileItems;
    }

    public JMenuItem[] getExamMenuItems() {
        JMenu addExamMenu = (JMenu) examMenu.getItem(0);

        JMenuItem[] menuItems = { addExamMenu.getItem(0), addExamMenu.getItem(1), addExamMenu.getItem(2),
                examMenu.getItem(1), examMenu.getItem(2) };

        return menuItems;
    }
}