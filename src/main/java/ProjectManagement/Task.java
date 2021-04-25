package ProjectManagement;

import java.util.HashMap;
import java.util.Map;

public class Task extends Activity {

	private String Description;
	private Map<Employee, Double> minutesWorked;
	
	public Task(TaskConstructorInfo info) {
		super(new ActivityConstructorInfo(info.title, info.startDate, info.endDate));
		minutesWorked = new HashMap<Employee, Double>();
		Description = info.Description;
	}

	public String getDescription() {
		return Description;
	}

	public Map<Employee, Double> getMinutesWorked() {
		return minutesWorked;
	}
	
	public void logWorkHours(Employee employee, double hoursWorked) {
		minutesWorked.put(employee, hoursWorked*60);
	}
	

}
