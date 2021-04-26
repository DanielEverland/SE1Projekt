package ProjectManagement;

public class ActivityConstructorInfo {
	public Date startDate;
	public Date endDate;
	public String title;
	
	public ActivityConstructorInfo(String title, Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
	}
}
