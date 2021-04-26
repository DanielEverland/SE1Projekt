package ProjectManagement;

public class Activity {
	protected String title;
	protected boolean isBlocking;
	protected Date startDate;
	protected Date endDate;

	public Activity(ActivityConstructorInfo info) {
		title = info.title;
		startDate = info.startDate;
		endDate = info.endDate;
		isBlocking = info.isBlocking;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
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
