package ProjectManagement;

public class Activity {
	protected String title;
	protected int startDate;
	protected int endDate;
	protected boolean isBlocking;

	public Activity(ActivityConstructorInfo info) {
		title = info.title;
		startDate = info.startDate;
		endDate = info.endDate;
		isBlocking = info.isBlocking;
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

	public boolean getIsBlocking() {
		return isBlocking;
	}

	public boolean isInDateInterval(int intervalStart, int intervalEnd) {
		return (startDate >= intervalStart && startDate < intervalEnd)
				|| (endDate > intervalStart && endDate <= intervalEnd)
				|| (startDate <= intervalStart && endDate >= intervalEnd);
	}
}
