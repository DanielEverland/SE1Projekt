package ProjectManagement;

public class Task {
	
	private String Title;
	private String Description;
	private int StartDate;
	private int EndDate;
	
	public String getTile() {
		return Title;
	}
	
	public String getDescription() {
		return Description;
	}
	
	public int getStartDate() {
		return StartDate;
	}
	
	public int getEndDate() {
		return EndDate;
	}
	
	public Task(TaskConstructorInfo info) {
		Title = info.Title;
		Description = info.Description;
		StartDate = info.StartDate;
		EndDate = info.EndDate;
	}
}
