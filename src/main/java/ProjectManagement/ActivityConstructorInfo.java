package ProjectManagement;

public class ActivityConstructorInfo {
	public Date startDate;
	public Date endDate;
	public String title;
	public boolean isBlocking;

	public ActivityConstructorInfo(String title, Date startDate, Date endDate, boolean isBlocking) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.isBlocking = isBlocking;
	}
}
