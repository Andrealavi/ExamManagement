package models.exam;

public abstract class AbstractExam {
    protected String firstName;
    protected String lastName;
    protected String courseName;
    protected Integer grade;
    protected Integer credits;
    protected boolean honors;

    public AbstractExam(String firstName, String lastName, String courseName, Integer credits, boolean honors) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseName = courseName;
        this.credits = credits;
        this.honors = honors;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getCredits() {
        return credits;
    }

    public String getHonors() {
        return honors ? "Yes" : "No";
    }

    public abstract Integer getGrade();

    public abstract String[] toStringArray();
}