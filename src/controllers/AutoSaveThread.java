package controllers;

import models.ExamsTableModel;
import models.exam.ExamIO;

public class AutoSaveThread extends Thread {
    public ExamsTableModel model;

    public AutoSaveThread(ExamsTableModel model) {
        this.model = model;
    }

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
