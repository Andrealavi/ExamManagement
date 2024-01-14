/**
 * @author Andrea Lavino (176195)
 * 
 * @package app
 */
package app;

import views.AppFrame;
import controllers.Controller;

/**
 * Main class that start the application
 */
public class Main {
    /**
     * Start the application
     * 
     * @param args
     */
    public static void main(String[] args) {
        AppFrame f = new AppFrame("Exams Manager");
        Controller c = new Controller(f);

        c.setTableModel();
        c.addEventListeners();
        c.startThread();

        f.pack();
    }
}
