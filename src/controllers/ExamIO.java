package controllers;

import java.io.*;
import java.util.Vector;

import models.exam.*;

public class ExamIO {
    private File file;

    public ExamIO(String fileName) {
        file = new File(fileName);
    }

    public ExamIO(File file) {
        this.file = file;
    }

    public Vector<AbstractExam> load() {
        Vector<AbstractExam> examEntries = new Vector<AbstractExam>();

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {

                String[] data = line.split(",");

                if (data[0].equals("simple")) {
                    SimpleExam exam = new SimpleExam(data[1], data[2], data[3], data[4], data[5]);

                    examEntries.add(exam);
                } else if (data[0].equals("composed")) {
                    ComposedExam exam = new ComposedExam(data[1], data[2], data[3], data[4]);

                    String[][] partialExamsInfo = new String[Integer.parseInt(data[5])][2];

                    for (int i = 6, j = 0; i < (6 + (Integer.parseInt(data[5]) * 2)); i += 2, j++) {
                        partialExamsInfo[j][0] = data[i];
                        partialExamsInfo[j][1] = data[i + 1];
                    }

                    exam.setPartialExamsInfo(partialExamsInfo, Integer.parseInt(data[5]));

                    examEntries.add(exam);
                }

                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return examEntries;
    }

    public void save(Vector<AbstractExam> examEntries) {
        BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < examEntries.size(); i++) {
                writer.write(examEntries.get(i).toOutputString());
                writer.newLine();
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}