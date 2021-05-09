package ProjectManagement;

public class TaskConstructorInfo extends ActivityConstructorInfo {
	public String description;

	public TaskConstructorInfo() {
		description = "N/A";
	}
	public TaskConstructorInfo(String title, String description, Date startDate, Date endDate) {
		super(title, startDate, endDate, false);

		this.description = description;
	}
}