package models.exam;

import java.util.ArrayList;

public class ComposedExam extends AbstractExam {
    private ArrayList<Integer> grades;
    private ArrayList<Float> weights;
    private ArrayList<Boolean> honors;

    public ComposedExam(String firstName, String lastName, String className, ArrayList<Integer> grades,
            ArrayList<Float> weights,
            Integer credits,
            ArrayList<Boolean> honors) {
        super(firstName, lastName, className, credits);

        this.grades = grades;
        this.weights = weights;

        computeGrade();
    }

    public ComposedExam(String firstName, String lastName, String className, String credits) throws ExamInfoException {
        super(firstName, lastName, className, credits);
    }

    private void computeGrade() {
        float sum = 0.0f;
        int honorSum = 0;

        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i) * weights.get(i);

            honorSum += honors.get(i) ? 1 : 0;
        }

        grade = (int) sum;
        honor = grade == 30 && honorSum > (honors.size() / 2) ? true : false;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setPartialExamsInfo(String[][] partialExams, Integer examNumbers) throws ExamInfoException {
        grades = new ArrayList<Integer>();
        weights = new ArrayList<Float>();
        honors = new ArrayList<Boolean>();

        for (int i = 0; i < examNumbers; i++) {
            grades.add(Integer.parseInt(partialExams[i][0]));
            weights.add(Float.parseFloat(partialExams[i][1]));
            honors.add(Boolean.parseBoolean(partialExams[i][2]));
        }

        Float sum = 0.0f;
        for (Float weight : weights) {
            sum += weight;
        }

        for (Integer grade : grades) {
            if (grade < 18 || grade > 30) {
                throw new ExamInfoException(
                        "Invalid value in partial exams grades.\nMake sure all grades are between 18 and 30");
            }
        }

        if (!sum.equals(1.0f)) {
            throw new ExamInfoException("Invalid value in partial exams weights.\nPlease be sure they add up to 100%");
        }

        computeGrade();
    }

    public String[] getPartialExamsGrades() {
        String[] gradesStringArray = new String[grades.size()];

        for (int i = 0; i < grades.size(); i++) {
            gradesStringArray[i] = grades.get(i).toString();
        }

        return gradesStringArray;
    }

    public String[] getPartialExamsWeights() {
        String[] weightsStringArray = new String[weights.size()];

        for (int i = 0; i < weights.size(); i++) {
            weightsStringArray[i] = weights.get(i).toString();
        }

        return weightsStringArray;
    }

    public Boolean[] getPartialExamsHonors() {
        Boolean[] honorsArray = new Boolean[honors.size()];

        for (int i = 0; i < honors.size(); i++) {
            honorsArray[i] = honors.get(i);
        }

        return honorsArray;
    }

    public String getHonor() {
        return honor ? "Sì" : "No";
    }

    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, className, grade.toString(), credits.toString(),
                getHonor() };

        return stringArray;
    }

    public String toOutputString() {
        StringBuffer outputStringBuffer = new StringBuffer("composed" + firstName + "," + lastName + "," + className
                + "," + credits.toString() + "," + grades.size() + ",");

        for (int i = 0; i < grades.size(); i++) {
            outputStringBuffer.append(
                    grades.get(i).toString() + "," + weights.get(i).toString() + "," + honors.get(i).toString());
        }

        return outputStringBuffer.toString();
    }
}