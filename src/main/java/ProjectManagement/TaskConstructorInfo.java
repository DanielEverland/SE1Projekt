package ProjectManagement;

public class TaskConstructorInfo extends ActivityConstructorInfo {
	public String Description;

	public TaskConstructorInfo() {
		Description = "N/A";
	}
	public TaskConstructorInfo(String title, String description, Date startDate, Date endDate) {
		super(title, startDate, endDate, false);

		Description = description;
	}
}