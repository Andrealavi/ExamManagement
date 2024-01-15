/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers
 */
package controllers;

import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import views.*;
import views.panels.*;

import models.*;

import controllers.listeners.*;
import controllers.listeners.add.AddComposedExamListener;
import controllers.listeners.add.AddSimpleExamListener;
import controllers.listeners.filter.FilterExamsListener;
import controllers.listeners.io.*;
import controllers.listeners.remove.RemoveAction;

/**
 * Enables interaction with {@link models} package
 */
public class Controller {
    /**
     * Application Frame
     */
    private AppFrame frame;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param frame Application frame
     */
    public Controller(AppFrame frame) {
        this.frame = frame;
    }

    /**
     * Sets the exams table model
     */
    public void setTableModel() {
        TablePanel tablePanel = (TablePanel) frame.getTablePanel();
        tablePanel.getTable().setModel(new ExamsTableModel());

        tablePanel.getTable().getColumnModel().getColumn(1).setMinWidth(125);
        tablePanel.getTable().getColumnModel().getColumn(2).setMinWidth(125);
    }

    /**
     * Adds all the event listeners to the views components, in particular to the
     * application menu components.
     */
    public void addEventListeners() {
        AtomicBoolean isSaved = new AtomicBoolean(true);
        AtomicBoolean isFiltered = new AtomicBoolean(false);

        TablePanel tablePanel = (TablePanel) frame.getTablePanel();
        ExamsTableModel tableModel = (ExamsTableModel) tablePanel.getTable().getModel();

        /**
         * Set a keyBinding
         */
        tablePanel.getTable().getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "pressed");
        tablePanel.getTable().getActionMap().put("pressed",
                new RemoveAction(frame, tablePanel.getTable(), isSaved, isFiltered));

        TopMenu menuBar = frame.getTopMenu();

        JMenuItem addSimpleExamItem = ((JMenu) menuBar.getExamMenuItems()[0]).getItem(0);
        addSimpleExamItem
                .addActionListener(
                        new AddSimpleExamListener(frame, tableModel.getColumns(), tableModel, isSaved, isFiltered));
        addSimpleExamItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

        JMenuItem addComposedExamItem = ((JMenu) menuBar.getExamMenuItems()[0]).getItem(1);
        addComposedExamItem
                .addActionListener(
                        new AddComposedExamListener(frame, tableModel, isSaved, isFiltered));
        addComposedExamItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

        JMenuItem filterEntriesItem = menuBar.getExamMenuItems()[1];
        filterEntriesItem.addActionListener(new FilterExamsListener(frame, tablePanel.getTable(), isFiltered));
        filterEntriesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));

        JMenuItem[] fileMenu = menuBar.getFileMenuItems();

        fileMenu[0].addActionListener(new LoadFileListener(frame, frame.getFileChooser(), tableModel, isSaved));
        fileMenu[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        fileMenu[1].addActionListener(new SaveFileListener(frame, frame.getFileChooser(), tableModel, isSaved));
        fileMenu[1].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        fileMenu[2].addActionListener(new PrintTableListener(frame, tablePanel.getTable()));
        fileMenu[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        tablePanel.getTable().addMouseListener(new DoubleClickOnEntryListener(frame, isSaved, isFiltered));

        frame.addWindowListener(new ClosingWindowListener(frame, frame.getFileChooser(), tableModel, isSaved));

        frame.pack();
    }

    /**
     * Creates and starts {@link controllers.AutoSaveThread}
     */
    public void startThread() {
        AutoSaveThread saveThread = new AutoSaveThread((ExamsTableModel) frame.getTablePanel().getTable().getModel());

        saveThread.run();
    }
}
