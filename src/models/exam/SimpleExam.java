package models.exam;

public class SimpleExam extends AbstractExam {
    public SimpleExam(String firstName, String lastName, String className, Integer grade, Integer credits,
            boolean honor) {
        super(firstName, lastName, className, credits);
        this.grade = grade;
        this.honor = honor;
    }

    public SimpleExam(String firstName, String lastName, String className, String grade, String credits,
            String honor) throws ExamInfoException {
        super(firstName, lastName, className, credits);

        if (Integer.parseInt(grade) < 18 || Integer.parseInt(grade) > 30) {
            throw new ExamInfoException("Invalid grade value.\nPlease insert a value between 18 and 30.");
        } else {
            this.grade = Integer.parseInt(grade);
        }

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
                getHonor() };

        return stringArray;
    }

    public String toOutputString() {
        String outputString = "simple" + firstName + "," + lastName + "," + className + "," + grade.toString() + ","
                + credits.toString() + "," + honor.toString();

        return outputString;
    }
}