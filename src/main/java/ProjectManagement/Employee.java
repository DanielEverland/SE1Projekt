package ProjectManagement;

import io.cucumber.messages.internal.com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
		if (endDate.before(startDate)) {
			throw new IllegalArgumentException("Start date cannot be greater than end date");
		}
		assert !endDate.before(startDate);

		// Special case: If employee isn't allowed to have tasks, they are always unavailable
		if (maxTasks <= 0) {
			return false;
		}

		// Iterate through all assigned activities in the interval
		// If an activity is blocking, employee is unavailable
		// If an activity is a task, add it to the list of tasks in the interval
		ArrayList<Task> tasksInInterval = new ArrayList<>();
		for (Activity activity : assignedActivites) {
			if (activity.isInDateInterval(startDate, endDate)) {
				if (activity.getIsBlocking()) {
					return false;
				}
				if (activity instanceof Task) {
					tasksInInterval.add((Task)activity);
				}
			}
		}

		// Construct a timeline of when tasks in the interval start and end
		HashMap<Date, Integer> startDates = new HashMap<>();
		HashMap<Date, Integer> endDates = new HashMap<>();
		for (Task task : tasksInInterval) {
			startDates.put(task.getStartDate(),	startDates.getOrDefault(task.getStartDate(), 0) + 1);
			endDates.put(task.getEndDate(),	endDates.getOrDefault(task.getEndDate(), 0) + 1);
		}

		// Iterate through the timeline and check if the number of simultaneous tasks is ever equal to maxTasks
		int overlappingTasks = 0;
		for (Date date : Sets.union(startDates.keySet(), endDates.keySet()).stream().sorted().collect(Collectors.toList())) {
			if (date.after(endDate)) {
				break;
			}

			overlappingTasks += startDates.getOrDefault(date, 0);
			if (overlappingTasks >= maxTasks && (date.equals(startDate) || date.after(startDate))) {
				return false;
			}
			overlappingTasks -= endDates.getOrDefault(date, 0);
		}

		return true;
	}

	public boolean isAvailable(Task task) {
		return isAvailable(task.startDate, task.endDate);
	}

}
