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
import controllers.listeners.remove.RemoveEntryListener;

public class Controller {
    private AppFrame frame;

    public Controller(AppFrame frame) {
        this.frame = frame;
    }

    public void setTableModel() {
        TablePanel tablePanel = (TablePanel) frame.getTablePanel();
        tablePanel.getTable().setModel(new ExamsTableModel());
    }

    public void addEventListeners() {
        AtomicBoolean isSaved = new AtomicBoolean(true);
        AtomicBoolean isFiltered = new AtomicBoolean(false);

        TablePanel tablePanel = (TablePanel) frame.getTablePanel();
        ExamsTableModel tableModel = (ExamsTableModel) tablePanel.getTable().getModel();

        TopMenu menuBar = frame.getTopMenu();

        JMenuItem addSimpleExamItem = ((JMenu) menuBar.getExamMenuItems()[0]).getItem(0);
        addSimpleExamItem
                .addActionListener(new AddSimpleExamListener(frame, tableModel.getColumns(), tableModel, isSaved));
        addSimpleExamItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

        JMenuItem addComposedExamItem = ((JMenu) menuBar.getExamMenuItems()[0]).getItem(1);
        addComposedExamItem
                .addActionListener(
                        new AddComposedExamListener(frame, tableModel, isSaved));
        addComposedExamItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));

        JMenuItem removeEntriesItem = menuBar.getExamMenuItems()[1];
        removeEntriesItem.addActionListener(new RemoveEntryListener(frame, tablePanel.getTable(), isSaved, isFiltered));
        removeEntriesItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));

        JMenuItem filterEntriesItem = menuBar.getExamMenuItems()[2];
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

        AutoSaveThread saveThread = new AutoSaveThread(tableModel);

        saveThread.run();
    }
}
