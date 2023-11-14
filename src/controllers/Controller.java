package controllers;

import views.*;
// import views.dialogs.*;
import views.panels.*;

import javax.swing.JMenuItem;

import controllers.listeners.AddComposedExamListener;
import controllers.listeners.AddSimpleExamListener;
import controllers.listeners.DoubleClickOnEntryListener;
import models.*;

public class Controller {
    private AppFrame f;

    public Controller(AppFrame f) {
        this.f = f;
    }

    public void setTableModel() {
        switch (f.getPanelType()) {
            case "examTable":
                TablePanel tablePanel = (TablePanel) f.getMainPanel();
                tablePanel.getTable().setModel(new ExamsTableModel());
                break;

            default:
                break;
        }
    }

    public void addEventListeners() {
        switch (f.getPanelType()) {
            case "examTable":
                TablePanel tablePanel = (TablePanel) f.getMainPanel();
                ExamsTableModel tableModel = (ExamsTableModel) tablePanel.getTable().getModel();

                TopMenu menuBar = f.getTopMenu();

                JMenuItem addSimpleExamItem = menuBar.getAddExamMenu().getItem(0);
                addSimpleExamItem.addActionListener(new AddSimpleExamListener(f, tableModel.getColumns(), tableModel));

                JMenuItem addComposedExamItem = menuBar.getAddExamMenu().getItem(1);
                addComposedExamItem
                        .addActionListener(new AddComposedExamListener(f, tableModel.getColumns(), tableModel));

                tablePanel.getTable().addMouseListener(new DoubleClickOnEntryListener(f));

                f.pack();
                break;

            default:
                break;
        }
    }
}
