package ProjectManagement;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String id;
	private int maxTasks;
	private List<Activity> assignedActivites;
	private boolean projectSelected = false;

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
		
		if (tasks.isEmpty()) {
			ErrorMessageHandler.addErrorMessage("No assigned tasks for this identification code");
		}
		
		return tasks;


	}


	public List<Activity> getEvents() {
		ArrayList<Activity> events = new ArrayList<>();
		for (Activity assignedActivity : assignedActivites) {
			if (!(assignedActivity instanceof Task)) {
				events.add(assignedActivity);
			}
		}
		return events;
	}
	

	public void assignToActivity(Activity activity) {
  assert !assignedActivites.contains(activity);
  
		assignedActivites.add(activity);

	}


	public boolean isAvailable(Date startDate, Date endDate) {
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

	public boolean isAvailable(Task task) {
		return isAvailable(task.startDate, task.endDate);
	}


}
