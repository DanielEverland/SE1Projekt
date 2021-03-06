package ProjectManagement;

import java.util.*;

import static org.junit.Assert.assertNotNull;

public class Application {

	private static Application instance;

	private Map<Integer, Project> projects;
	private Map<String, Employee> employees;
	private Employee signedInEmployee;

	private int newProjectId = 0;
	private boolean isQuitting;

	// Predefined list of employee ids
	private ArrayList<String> employeeIds = new ArrayList<String>() {
		{
			add("abcd");
			add("efgh");
		}
	};

	private Application() {
		projects = new HashMap<>();
		employees = new HashMap<>();
		for (String id : employeeIds) {
			getEmployees().put(id, new Employee(id));
		}
	}

	public static Application getInstance(boolean reset) {
		if (instance == null || reset) {
			instance = new Application();
		}
		return instance;
	}

	public static Application getInstance() {
		return getInstance(false);
	}

	public void quit() {
		isQuitting = true;
	}

	public boolean getIsQuitting() {
		return isQuitting;
	}

	public Employee getSignedInEmployee() {
		return signedInEmployee;
	}

	public int createProject(String title) {
		if (isSignedIn()) {
			Project newProject = new Project(newProjectId++, title, this);
			projects.put(newProject.getId(), newProject);
			return newProject.getId();
		}

		ErrorMessageHandler.addErrorMessage("Employee must be signed in to create project");
		return -1;
	}

	public List<Activity> getAllActivities() {
		List<Activity> allActivities = new ArrayList<Activity>();

		for (Project project : projects.values()) {
			allActivities.addAll(project.getTasks());
		}

		if (isSignedIn())
			allActivities.addAll(signedInEmployee.getEvents());

		return allActivities;
	}

	public Map<Integer, Project> getProjects() {
		return projects;
	}

	public Project getProject(int id) {
		return projects.get(id);
	}

	public void addEmployee(Employee employee) {
		getEmployees().put(employee.getId(), employee);
	}

	public Employee getEmployee(String id) {
		return employees.get(id);
	}

	public void signIn(String id) {
		if (!employees.containsKey(id)) {
			ErrorMessageHandler.addErrorMessage("No employee exists with the ID \"" + id + "\"");
			return;
		}

		signedInEmployee = getEmployees().get(id);
		System.out.println("Successfully signed in as \"" + id + "\"");
	}

	public void signOut() {
		signedInEmployee = null;
	}

	public boolean isSignedIn() {
		return signedInEmployee != null;
	}

	public List<Task> searchAssignedTasksForEmployee(String id) {
		Employee employeeToSearch = getEmployees().get(id);
		List<Task> tasks = employeeToSearch.getTasks();
		if (tasks.isEmpty()) {
			ErrorMessageHandler.addErrorMessage("No assigned tasks for this identification code");
		}
		return tasks;
	}

	public Map<String, Employee> getEmployees() {
		return employees;
	}

	public ArrayList<Employee> getAvailableEmployees(Date startDate, Date endDate) {
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException("Start date cannot be greater than end date");
		}

		ArrayList<Employee> availableEmployees = new ArrayList<>();
		for (Employee employee : employees.values()) {
			if (employee.isAvailable(startDate, endDate)) {
				availableEmployees.add(employee);
			}
		}

		return availableEmployees;

	}
	
//	Method for finding all tasks that contain {String} in title 
	public ArrayList<Project> findProjectsContainingTitle(String title) {
		ArrayList<Project> foundProjects = new ArrayList<Project>();

		if (!isSignedIn()) {
			ErrorMessageHandler.addErrorMessage("Employee must be signed in");	
			return null;
		}

		for (Project project : projects.values()) {
			if (project.getTitle().contains(title)) {
				foundProjects.add(project);
			}
		}
		return foundProjects;

	}

	public Project getSpecificProjectByTitle(String title) {
		if (multipleProjectsWithSameTitle(projects.values(), title)) {
			return null;
		}
		
		assert !multipleProjectsWithSameTitle(projects.values(), title) : "Pre condition";
		
		Project foundProject = null; 

		if (isSignedIn()) {
			for (Project project : projects.values()) {
				if (project.getTitle().equals(title)) {
					foundProject = project;
					assert foundProject.getTitle().equals(title) : "Post condition";
					return foundProject;
				}
			}
		} else {
			ErrorMessageHandler.addErrorMessage("Employee must be signed in");
			return null;
		}
		
		assert foundProject == null : "Post condition";
		return null;

	}

	public boolean multipleProjectsWithSameTitle(Collection<Project> projects, String title) {
		if (projects == null || projects.isEmpty()) {
			ErrorMessageHandler.addErrorMessage("List of projects cannot be empty");
			return false;
		}
		
		assert projects != null && !projects.isEmpty();

		ArrayList<Project> foundProjects = new ArrayList<Project>();
		for (Project project : projects) {
			if (project.getTitle().equals(title)) {
				foundProjects.add(project);
			}
		}
		boolean moreThanOneProjectFound = foundProjects.size() > 1;
		if (moreThanOneProjectFound) {
			ErrorMessageHandler.addErrorMessage("More than one project with the title \"" + title + "\" found");
		}
		return moreThanOneProjectFound;
	}

	public boolean multipleProjectsContainingTitleFound(String title) {
		if (projects.size() <= 1) {
			return false;
		}
		assert projects.size() > 1;

		boolean multipleProjectsFound = true;
		ErrorMessageHandler.addErrorMessage("More than one project with the title \"" + title + "\" has been found");
		return multipleProjectsFound;
	}

	public boolean noProjectContainingTitleFound(String title) {
		if (findProjectsContainingTitle(title).size() > 0) {
			return false;
		}
		
		assert findProjectsContainingTitle(title).isEmpty();

		boolean noProjectsFound = true;
		ErrorMessageHandler.addErrorMessage("No project with the title \"" + title + "\" has been found");
		return noProjectsFound;
	}

	public void assignVacation(Employee employee, Date startDate, Date endDate) {
		employee.assignToActivity(new Vacation(startDate, endDate));
	}

	public void assignSickLeave(Employee employee, Date startDate, Date endDate) {
		employee.assignToActivity(new SickLeave(startDate, endDate));
	}

	public void assignCourse(Employee employee, String description, Date startDate, Date endDate) {
		employee.assignToActivity(new Course(description, startDate, endDate));
	}

}
