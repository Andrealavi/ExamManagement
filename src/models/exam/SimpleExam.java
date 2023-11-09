package models.exam;

public class SimpleExam extends AbstractExam {
    public SimpleExam(String firstName, String lastName, String courseName, Integer grade, Integer credits,
            boolean honors) {
        super(firstName, lastName, courseName, credits, honors);
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, courseName, grade.toString(), credits.toString(),
                honors ? "Si" : "No" };

        return stringArray;
    }
}