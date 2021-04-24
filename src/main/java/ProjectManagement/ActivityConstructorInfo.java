package ProjectManagement;

public class ActivityConstructorInfo {
	public int StartDate;
	public int EndDate;
	public String Title;
	
	public ActivityConstructorInfo(String title, int startDate, int endDate) {
		StartDate = startDate;
		EndDate = endDate;
		Title = title;
	}
}
