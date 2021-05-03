package ProjectManagement;

import java.util.HashMap;
import java.util.Map;

public class Task extends Activity {

	private String description;
	private double expectedTime;
	private Map<Employee, Duration> minutesWorked;
	
	public Task(TaskConstructorInfo info) {
		super(new ActivityConstructorInfo(info.title, info.startDate, info.endDate, false));
		minutesWorked = new HashMap<Employee, Duration>();
		description = info.Description;
	}

	public String getDescription() {
		return description;
	}

	public Map<Employee, Duration> getMinutesWorked() {
		return minutesWorked;
	}
	
	public void logWorkHours(Employee employee, double hoursWorked) {
		if (!minutesWorked.containsKey(employee)) {
			minutesWorked.put(employee, new Duration());
		}
		minutesWorked.get(employee).AddHours(hoursWorked);
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
		return super.toString() + "\nDescription: " + description + "\n";
	}

	public void editTitle(String newTitle) {
		this.title = newTitle;
	}
}
