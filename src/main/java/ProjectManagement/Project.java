package ProjectManagement;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {
	// The number of digits in the stringified id of projects
	private static final int serialDigits = 4;

	private int year;
	private int id;
	private String title;
	private ArrayList<Task> tasks;
	private Employee projectLead;
	private boolean completed;
	private Application application;

	public Project(int id, String title, Application owner) {
		Assert.assertFalse("Id must be a non-negative integer", id < 0);
		Assert.assertFalse("Title cannot be empty or contain '|'", title.length() == 0 || title.contains("|"));
		assert owner != null;

		year = Calendar.getInstance().get(Calendar.YEAR);
		this.id = id;
		this.title = title;
		this.application = owner;
		tasks = new ArrayList<Task>();
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getFullTitle() {
		return year + "|" + idToString() + "|" + title;
	}

	private String idToString() {
		return String.format("%0" + serialDigits + "d", id);
	}

	public void removeProjectLeader() {
		projectLead = null;
	}

	public void assignProjectLeader(Employee newProjectLeader) {
		if (this.hasProjectLeader()) {
			ErrorMessageHandler.addErrorMessage("The project already has an assigned project leader");
		} else {
			projectLead = newProjectLeader;
		}

	}

	public Employee getProjectLeader() {
		return projectLead;
	}

	public boolean hasProjectLeader() {
		return projectLead != null;
	}

	public void createTask(TaskConstructorInfo info) {
		if (!info.isValid()) {
			ErrorMessageHandler.addErrorMessage("Constructor info contains invalid information");
			return;
		}

		if (projectLead == null) {
			ErrorMessageHandler.addErrorMessage("No project lead has been assigned to this project");
			return;
		}

		if (!application.isSignedIn()) {
			ErrorMessageHandler.addErrorMessage("Cannot create tasks when not signed in");
			return;
		}

		if (application.getSignedInEmployee() != projectLead) {
			ErrorMessageHandler.addErrorMessage("Currently signed in employee is not project leader");
			return;
		}

		tasks.add(new Task(info));
	}

	public boolean containsTask(String title, String description, Date startDate, Date endDate) {
		return findTask(title, description, startDate, endDate) != null;
	}

	public void assignTaskToEmployee(Employee employee, Task task) {
		if (!isProjectLeader(application.getSignedInEmployee())) {
			ErrorMessageHandler.addErrorMessage("Currently signed in employee is not project leader");
			return;
		}
		if (employee == null || task == null) {
			ErrorMessageHandler.addErrorMessage("Employee being assigned task or assigned task was null");
			return;
		}
		assert isProjectLeader(application.getSignedInEmployee());
		assert employee != null;
		assert task != null;

		if (employee.isAvailable(task)) {
			employee.assignToActivity(task);
		} else {
			ErrorMessageHandler.addErrorMessage("Employee is unavailable");
		}

		assert !employee.isAvailable((task)) || employee.getTasks().stream()
				.anyMatch(m -> m.getTitle().contentEquals(task.getTitle()) && m.getDescription().contentEquals(task.getDescription())
						&& m.getStartDate().equals(task.getStartDate()) && m.getEndDate().equals(task.getEndDate()));
	}

	public boolean isProjectLeader(Employee employee) {
		boolean isEmployeeProjectLeader = employee == projectLead;
		if (!isEmployeeProjectLeader) {
			ErrorMessageHandler.addErrorMessage("Must be project leader");
		}
		return isEmployeeProjectLeader;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public Task findTask(String title, String description, Date startDate, Date endDate) {
		assert title != null && description != null && startDate != null && endDate != null : "Pre condition";
		
		if (tasks.isEmpty()) {
			ErrorMessageHandler.addErrorMessage("No tasks assigned to the project");
			return null;
		}
		
		for (Task task : tasks) {
			if (task.getTitle().equals(title) && 
					task.getDescription().equals(description) &&
					task.getStartDate().equals(startDate) && 
					task.getEndDate().equals(endDate)) {
				return task;
			}
		}
		return null;
	}

	public List<Employee> getAssignedEmployees(Task task) {
		List<Employee> assignedEmployees = new ArrayList<Employee>();

		for(Employee employee : application.getEmployees().values()) {
			if(employee.getTasks().contains(task))
				assignedEmployees.add(employee);
		}

		return assignedEmployees;
	}

	public void editTaskTitle(Task task, String newTitle, Employee employee) {
		if (isProjectLeader(employee)) {
			task.editTitle(newTitle);
		}

	}

	public void editTaskDescription(Task task, String newDescription, Employee employee) {
		if (isProjectLeader(employee)) {
			task.editDescription(newDescription);
		}
	}

	public void editTaskStartDate(Task task, Date newStartDate, Employee employee) {
		if (isProjectLeader(employee)) {
			task.setStartDate(newStartDate);
		}
	}

	public void editTaskEndDate(Task task, Date newEndDate, Employee employee) {
		if (isProjectLeader(employee)) {
			task.setEndDate(newEndDate);
		}
	}

	public void editExpectedTimeForTask(Task task, Double newExpectedTime, Employee employee) {
		if (isProjectLeader(employee)) {
			task.setExpectedTime(newExpectedTime);
		}
	}

	public void markAsCompleted() {
		if (!completed) {
			completed = true;
		} else {
			ErrorMessageHandler.addErrorMessage("Project already marked as complete");
		}
	}

	public boolean isCompleted() {
		return completed;
	}

	@Override
	public String toString() {
		return id + ": " + title;
	}
}
