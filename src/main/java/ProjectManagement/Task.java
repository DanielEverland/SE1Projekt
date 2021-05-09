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
		if (!(hoursWorked > 0)) {
			ErrorMessageHandler.addErrorMessage("Input must be greater than 0");
			return;
		}
		assert hoursWorked > 0 : "Pre condition";
		if (!durationWorked.containsKey(employee)) {
			durationWorked.put(employee, new Duration());
		}
		assert durationWorked.containsKey(employee) : "Pre condition";

		double totalDurationBefore = getTotalDurationWorked();
		durationWorked.get(employee).addHours(hoursWorked);
		double totalDuration = getTotalDurationWorked();

		boolean isDifferentEnough = Math.abs(totalDuration - (totalDurationBefore + hoursWorked)) <= 0.00001;
		if (!isDifferentEnough) {
			ErrorMessageHandler.addErrorMessage("Hours has not been logged");
			return;
		}
		assert isDifferentEnough : "Post condition";

		if (totalDuration > expectedTime && expectedTime != 0) {
			ErrorMessageHandler.addErrorMessage("Too much time spent on task");
		}

	}

	private double getTotalDurationWorked() {
		double totalDuration = 0;
		for (Duration duration : durationWorked.values()) {
			totalDuration += duration.getHoursPassed();
		}
		return totalDuration;

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
