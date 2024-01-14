/**
 * @author Andrea Lavino (176195)
 * 
 * @package models
 */
package models;

import java.io.*;
import java.util.Vector;

import java.util.ArrayList;

import models.exam.*;

/**
 * Implements operations for reading/writing exam entries from/to text files.
 * 
 * @see models.ExamsTableModel
 * @see java.io.BufferedReader
 * @see java.io.BufferedWriter
 * @see java.io.File
 */
public final class ExamIO {
    /**
     * Loads exam entries read from {@link java.io.File} to a
     * {@link java.util.Vector} containing exam entries
     * 
     * @param file File from which data are load
     * 
     * @return {@link java.util.Vector} containing exam entries
     * @throws FileNotFoundException Exception thrown when the file doesn't exists
     * @throws IOException           Exception thrown when a problem related I/O
     *                               goes wrong
     */
    public static Vector<AbstractExam> load(File file) throws FileNotFoundException, IOException {
        Vector<AbstractExam> examEntries = new Vector<AbstractExam>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (line != null) {

            String[] data = line.split(",");
            AbstractExam exam = null;

            if (data[0].equals("simple")) {
                exam = new SimpleExam(data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]),
                        Boolean.parseBoolean(data[6]));

            } else if (data[0].equals("composed")) {
                ArrayList<Integer> grades = new ArrayList<Integer>();
                ArrayList<Float> weights = new ArrayList<Float>();

                for (int i = 6; i < (6 + (Integer.parseInt(data[5]) * 2)); i += 2) {
                    grades.add(Integer.parseInt(data[i]));
                    weights.add(Float.parseFloat(data[i + 1]));
                }

                exam = new ComposedExam(data[1], data[2], data[3], grades, weights, Integer.parseInt(data[4]));
            } else {
                throw new IOException("The selected file cannot be opened by the program.\nPlease choose another file");
            }

            examEntries.add(exam);

            line = reader.readLine();
        }

        reader.close();

        return examEntries;
    }

    /**
     * Saves exam entries from {@link java.util.Vector} to {@link java.io.File}
     * 
     * @param file        File in which data are saved
     * 
     * @param examEntries Vector containing exam entries data
     * @throws IOException Exception thrown when a problem related I/O goes
     *                     wrong
     */
    public static void save(File file, Vector<AbstractExam> examEntries) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < examEntries.size(); i++) {
            writer.write(examEntries.get(i).toOutputString());
            writer.newLine();
        }

        writer.close();
    }
}