package ProjectManagement;

public class Course extends Activity {
    private String description;

    public Course(String description, Date startDate, Date endDate) {
        super(new ActivityConstructorInfo("Course", startDate, endDate, false));
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}