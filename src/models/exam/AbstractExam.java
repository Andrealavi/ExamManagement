package models.exam;

abstract class AbstractExam {
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

    abstract Integer getGrade();

    abstract String[] toStringArray();
}