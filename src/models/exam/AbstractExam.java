package models.exam;

public abstract class AbstractExam {
    protected String firstName;
    protected String lastName;
    protected String className;
    protected Integer grade;
    protected Integer credits;
    protected Boolean honor;

    public AbstractExam(String firstName, String lastName, String className, Integer credits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.credits = credits;
    }

    public AbstractExam(String firstName, String lastName, String className, String credits) throws ExamInfoException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;

        if (Integer.parseInt(credits) <= 0 || Integer.parseInt(credits) > 18) {
            throw new ExamInfoException("Invalid credits value.\nPlease insert a value between 0 and 18.");
        } else {
            this.credits = Integer.parseInt(credits);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClassName() {
        return className;
    }

    public Integer getCredits() {
        return credits;
    }

    public abstract String getHonor();

    public abstract Integer getGrade();

    public abstract String[] toStringArray();
}