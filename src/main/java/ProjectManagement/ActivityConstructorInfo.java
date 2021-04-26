package ProjectManagement;

import java.util.Date;

public class ActivityConstructorInfo {
	public Date StartDate;
	public Date EndDate;
	public String Title;
	
	public ActivityConstructorInfo(String title, Date startDate, Date endDate) {
		StartDate = startDate;
		EndDate = endDate;
		Title = title;
	}
}
