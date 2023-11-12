package models.exam;

public class SimpleExam extends AbstractExam {
    public SimpleExam(String firstName, String lastName, String className, Integer grade, Integer credits,
            boolean honor) {
        super(firstName, lastName, className, credits);
        this.grade = grade;
        this.honor = honor;
    }

    public SimpleExam(String firstName, String lastName, String className, String grade, String credits,
            String honor) {
        super(firstName, lastName, className, credits);
        this.grade = Integer.parseInt(grade);
        this.honor = Boolean.valueOf(honor);
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getHonor() {
        return honor ? "Sì" : "No";
    }

    public String[] toStringArray() {
        String[] stringArray = { firstName, lastName, className, grade.toString(), credits.toString(),
                honor ? "Si" : "No" };

        return stringArray;
    }
}