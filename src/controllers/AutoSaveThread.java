/**
 * @author Andrea Lavino (176195)
 * 
 * @package controllers
 */
package controllers;

import models.ExamIO;
import models.ExamsTableModel;

/**
 * Extends {@link java.lang.Thread} to create a custom thread for autosaving
 * exams table content periodically
 */
public class AutoSaveThread extends Thread {
    /**
     * Exams table model
     */
    public ExamsTableModel model;

    /**
     * Instantiates class attribute with the value passed as argument
     * 
     * @param model Exams table model
     */
    public AutoSaveThread(ExamsTableModel model) {
        this.model = model;
    }

    /**
     * Opens a new file and save table content
     */
    @Override
    public void run() {
        ExamIO file = new ExamIO("./documents/.datalog");

        while (true) {
            try {
                sleep(120000);
                file.save(model.getEntries());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
