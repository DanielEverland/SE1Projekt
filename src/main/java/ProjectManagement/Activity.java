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

	@Override
	public String toString() {
		return "Title: " + title + "\nStart date: " + startDate.toString() + "\nEnd date: " + endDate.toString();
	}

	public boolean getIsBlocking() {
		return isBlocking;
	}

	public boolean isInDateInterval(Date intervalStart, Date intervalEnd) {
		return !(endDate.before(intervalStart) || startDate.after(intervalEnd));
	}

}
