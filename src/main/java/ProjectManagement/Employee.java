package ProjectManagement;

import java.util.List;

public class Employee {
	private String id;
	private int maxTasks;
	private List<Activity> aassignedActivites;
	private List<Task> tasks;

	public Employee(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<Activity> getAassignedActivites() {
		return aassignedActivites;
	}

	public List<Task> getTasks() {
		return tasks;
	}

}
