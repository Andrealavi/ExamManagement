/**
 * @author Andrea Lavino (176195)
 * 
 * @package models
 */
package models;

import java.io.*;
import java.util.Vector;

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
     * Loads exam entries read from {@link models.ExamIO#file} to a
     * {@link java.util.Vector} containing exam entries
     * 
     * @return {@link java.util.Vector} containing exam entries
     * @throws FileNotFoundException Exception thrown when the file doesn't exists
     * @throws ExamInfoException     Exception thrown when exam data are wrong
     * @throws IOException           Exception thrown when a problem related I/O
     *                               goes wrong
     */
    public static Vector<AbstractExam> load(File file) throws FileNotFoundException, ExamInfoException, IOException {
        Vector<AbstractExam> examEntries = new Vector<AbstractExam>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while (line != null) {

            String[] data = line.split(",");
            AbstractExam exam = null;

            if (data[0].equals("simple")) {
                exam = new SimpleExam(data[1], data[2], data[3], data[4], data[5]);

            } else if (data[0].equals("composed")) {
                exam = new ComposedExam(data[1], data[2], data[3], data[4]);

                String[][] partialExamsInfo = new String[Integer.parseInt(data[5])][2];

                for (int i = 6, j = 0; i < (6 + (Integer.parseInt(data[5]) * 2)); i += 2, j++) {
                    partialExamsInfo[j][0] = data[i];
                    partialExamsInfo[j][1] = data[i + 1];
                }

                ((ComposedExam) exam).setPartialExamsInfo(partialExamsInfo, Integer.parseInt(data[5]));

            }

            examEntries.add(exam);

            line = reader.readLine();
        }

        reader.close();

        return examEntries;
    }

    /**
     * Saves exam entries from {@link java.util.Vector} to
     * {@link models.ExamIO#file}
     * 
     * @param examEntries Vector containing exam entries data
     * @throws IOException       Exception thrown when a problem related I/O goes
     *                           wrong
     * @throws ExamInfoException Exception thrown when exam data are wrong
     */
    public static void save(File file, Vector<AbstractExam> examEntries) throws IOException, ExamInfoException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        for (int i = 0; i < examEntries.size(); i++) {
            writer.write(examEntries.get(i).toOutputString());
            writer.newLine();
        }

        writer.close();
    }
}