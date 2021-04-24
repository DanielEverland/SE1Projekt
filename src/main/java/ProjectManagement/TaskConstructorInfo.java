package ProjectManagement;

public class TaskConstructorInfo extends ActivityConstructorInfo {
	public String Description;
	
	public TaskConstructorInfo(String title, String description, int startDate, int endDate) {
		super(title, startDate, endDate);
		
		Description = description;
	}
}