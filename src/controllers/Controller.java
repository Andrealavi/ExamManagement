package controllers;

import views.*;
// import views.dialogs.*;
import views.panels.*;
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
}
