package app;

import views.AppFrame;
import controllers.Controller;

public class Main {
    public static void main(String[] args) {
        AppFrame f = new AppFrame("Exams Manager");
        Controller c = new Controller(f);

        c.setTableModel();
        c.addEventListeners();

        f.pack();
    }
}
