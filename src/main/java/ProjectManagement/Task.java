package ProjectManagement;

import java.util.Date;

public class Task {
	
	private String Title;
	private String Description;
	private Date StartDate;
	private Date EndDate;
	
	public String getTile() {
		return Title;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public Date getStartDate() {
		return StartDate;
	}
	
	public Date getEndDate() {
		return EndDate;
	}
	
	public Task(TaskConstructorInfo info) {
		Title = info.Title;
		Description = info.Description;
		StartDate = info.StartDate;
		EndDate = info.EndDate;
	}
}
