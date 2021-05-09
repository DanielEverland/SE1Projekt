package ProjectManagement;

import java.util.HashMap;
import java.util.Map;

public class Task extends Activity {

	private String description;
	private double expectedTime;
	private Map<Employee, Duration> durationWorked;
	private boolean completed;

	public Task(TaskConstructorInfo info) {
		super(new ActivityConstructorInfo(info.title, info.startDate, info.endDate, false));
		durationWorked = new HashMap<Employee, Duration>();
		description = info.Description;
	}

	public String getDescription() {
		return description;
	}

	public Map<Employee, Duration> getDurationWorked() {
		return durationWorked;
	}

	public void logWorkHours(Employee employee, double hoursWorked) {
		if (!durationWorked.containsKey(employee)) {	
			durationWorked.put(employee, new Duration());
		}
		durationWorked.get(employee).addHours(hoursWorked);

		double hoursPassed = durationWorked.get(employee).getHoursPassed();
		if (hoursPassed > expectedTime && expectedTime != 0) {	
			ErrorMessageHandler.addErrorMessage("Too much time spent on task");
		}
	}

	public double getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(double expectedTime) {
		assert expectedTime > 0.0;

		this.expectedTime = expectedTime;
	}

	public void addToExpectedTime(double hours) {
		assert hours > 0.0;

		this.expectedTime += hours;
	}

	@Override
	public String toString() {
		return super.toString() + "\nDescription: " + description + "\nExpected Time: " + expectedTime + " hours";
	}

	public void editTitle(String newTitle) {
		this.title = newTitle;
	}

	public void editDescription(String newDescription) {
		this.description = newDescription;
	}

	public void markAsCompleted() {
		if (!completed) {
			completed = true;
		} else {
			ErrorMessageHandler.addErrorMessage("Task already marked as complete");
		}
	}

	public boolean isCompleted() {
		return completed;
	}
}
