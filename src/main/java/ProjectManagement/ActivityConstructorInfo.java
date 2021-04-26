package ProjectManagement;

public class ActivityConstructorInfo {
	public int startDate;
	public int endDate;
	public String title;
	public boolean isBlocking;
	
	public ActivityConstructorInfo(String title, int startDate, int endDate, boolean isBlocking) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.isBlocking = isBlocking;
	}
}
