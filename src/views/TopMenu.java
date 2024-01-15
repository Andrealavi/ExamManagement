/**
 * @author Andrea Lavino (176195)
 * 
 * @package views
 */
package views;

import javax.swing.*;

/**
 * Implements the menu of the application.
 * 
 * Extends {@link JMenuBar} in order to create a custom
 * menu. It has two menus: file and exam
 * 
 * @see javax.swing.JMenuBar
 * @see javax.swing.JMenu
 * @see javax.swing.JMenuItem
 */
public class TopMenu extends JMenuBar {
    /**
     * File menu. It contains three menu items for loading, saving and printing
     * entries
     */
    private JMenu fileMenu;

    /**
     * Exam menu. It contains an inner menu for adding exam and two menu items for
     * removing and filtering entries.
     */
    private JMenu examMenu;

    /**
     * It populates file and exam menus with the proper menu items and then
     * add the menus on the menu bar.
     */
    public TopMenu() {
        fileMenu = new JMenu("File");
        examMenu = new JMenu("Exam");

        JMenuItem loadFileItem = new JMenuItem("Load File");
        JMenuItem saveFileItem = new JMenuItem("Save File");
        JMenuItem saveFileAsItem = new JMenuItem("Save File As");
        JMenuItem printFileItem = new JMenuItem("Print File");

        fileMenu.add(loadFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(saveFileAsItem);
        fileMenu.add(printFileItem);

        JMenu addExamMenu = new JMenu("Add Exam");
        JMenuItem filterExamItem = new JMenuItem("Filter Exams");

        JMenuItem simpleExamItem = new JMenuItem("Add simple exam");
        JMenuItem composedExamItem = new JMenuItem("Add composed exam");

        addExamMenu.add(simpleExamItem);
        addExamMenu.add(composedExamItem);

        examMenu.add(addExamMenu);
        examMenu.add(filterExamItem);

        add(fileMenu);
        add(examMenu);

        setVisible(true);
    }

    /**
     * Returns {@link TopMenu#fileMenu} items.
     * 
     * @return Array of {@link javax.swing.JMenuItem} with the items of file menu
     */
    public JMenuItem[] getFileMenuItems() {
        JMenuItem[] fileItems = { fileMenu.getItem(0), fileMenu.getItem(1), fileMenu.getItem(2), fileMenu.getItem(3) };
        return fileItems;
    }

    /**
     * Returns {@link TopMenu#examMenu} items.
     * 
     * @return Array of {@link javax.swing.JMenuItem} with the items of exam menu
     */
    public JMenuItem[] getExamMenuItems() {
        JMenuItem[] examItems = { examMenu.getItem(0), examMenu.getItem(1) };

        return examItems;
    }
}