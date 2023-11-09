package models.exam;

public class ComposedExam extends AbstractExam {
    private Integer[] grades;
    private Float[] weights;

    public ComposedExam(String firstName, String lastName, String courseName, Integer[] grades, Float[] weights,
            Integer credits,
            boolean honors) {
        super(firstName, lastName, courseName, credits, honors);

        this.grades = grades;
        this.weights = weights;

        computeGrade();
    }

    private void computeGrade() {
        int sum = 0;

        for (int i = 0; i < grades.length; i++) {
            sum += grades[i] * weights[i];
        }

        grade = (Integer) (sum / grades.length);
    }

    public Integer getGrade() {
        return grade;
    }

    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, courseName, grade.toString(), credits.toString(),
                honors ? "Si" : "No" };

        return stringArray;
    }
}