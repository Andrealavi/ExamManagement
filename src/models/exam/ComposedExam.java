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

    public ComposedExam(String firstName, String lastName, String className, String credits) {
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

    public void setPartialExamsInfo(String[][] partialExams, Integer examNumbers) {
        grades = new ArrayList<Integer>();
        weights = new ArrayList<Float>();
        honors = new ArrayList<Boolean>();

        for (int i = 0; i < examNumbers; i++) {
            grades.add(Integer.parseInt(partialExams[i][0]));
            weights.add(Float.parseFloat(partialExams[i][1]));
            honors.add(Boolean.parseBoolean(partialExams[i][2]));
        }

        computeGrade();
    }

    public String getHonor() {
        return honor ? "Sì" : "No";
    }

    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, className, grade.toString(), credits.toString(),
                getHonor() };

        return stringArray;
    }
}