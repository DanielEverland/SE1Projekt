package ProjectManagement;

public class Activity {
	protected String title;
	protected int startDate;
	protected int endDate;

	public Activity(ActivityConstructorInfo info) {
		title = info.title;
		startDate = info.startDate;
		endDate = info.endDate;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

}
