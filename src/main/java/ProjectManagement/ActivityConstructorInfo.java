package ProjectManagement;

public class ActivityConstructorInfo {
	public int startDate;
	public int endDate;
	public String title;
	
	public ActivityConstructorInfo(String title, int startDate, int endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}
}
