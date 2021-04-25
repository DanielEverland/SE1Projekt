package ProjectManagement;

public class Task {

	private String Title;
	private String Description;
	private int startDate;
	private int endDate;

	public Task(TaskConstructorInfo info) {
		Title = info.title;
		Description = info.Description;
		startDate = info.startDate;
		endDate = info.endDate;
	}

	public String getTile() {
		return Title;
	}

	public String getDescription() {
		return Description;
	}

	public int getStartDate() {
		return startDate;
	}

	public int getEndDate() {
		return endDate;
	}

}
