package ProjectManagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private int maxTasks;
	private List<Activity> assignedActivites;

	public Employee(String id) {
		this.id = id;
		assignedActivites = new ArrayList<Activity>();
	}

	public String getId() {
		return id;
	}

	public List<Activity> getAssignedActivites() {
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

}
