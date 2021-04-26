package ProjectManagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private int maxTasks;
	private List<Activity> assignedActivites;

	public Employee(String id, int maxTasks) {
		this.id = id;
		this.maxTasks = maxTasks;
		assignedActivites = new ArrayList<Activity>();
	}

	public Employee(String id) {
		this(id, 10);
	}

	public String getId() {
		return id;
	}

	public List<Activity> getAassignedActivites() {
		return assignedActivites;
	}
	
	public List<Task> getTasks() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		for (Activity assignedActivity : assignedActivites) {
			if (assignedActivity instanceof Task) {
				tasks.add((Task) assignedActivity);
			}
		}
		return tasks;
		
	}
	
	public void assignToTask(Task task) {
		assignedActivites.add(task);
	}

	public boolean isAvailable(int startDate, int endDate) {
		int tasksInPeriod = 0;
		for (Activity activity : assignedActivites) {
			if (activity.isInDateInterval(startDate, endDate)) {
				if (activity.getIsBlocking()) {
					return false;
				}
				if (activity instanceof Task) {
					tasksInPeriod++;
				}
			}
		}

		return tasksInPeriod < maxTasks;
	}
}
