package ProjectManagement;

import java.util.*;

import static org.junit.Assert.assertNotNull;

public class Application {

	public static Application Get() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	private static Application instance;

	private Map<Integer, Project> projects;
	private ArrayList<Project> projectList = new ArrayList<Project>();
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

	public Application() {
		projects = new HashMap<>();
		employees = new HashMap<>();
		for (String id : employeeIds) {
			getEmployees().put(id, new Employee(id));
		}
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

	public int createProject(String title) throws AuthException {
		if (isSignedIn()) {
			Project newProject = new Project(newProjectId++, title);
			projects.put(newProject.getId(), newProject);
			getProjectList().add(newProject);
			return newProject.getId();
		}

		ErrorMessageHandler.addErrorMessage("Employee must be signed in to create project");
		return -1;
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
			System.out.println("No employee exists with the ID \"" + id + "\"");
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
		return employeeToSearch.getTasks();
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

//	public ArrayList<Project> searchMapByEntry(String title) {
//
//		ArrayList<Project> foundProjects = new ArrayList<Project>();
//		for (Map.Entry<Integer, Project> entry : projects.entrySet()) {
//			if (title.equals(entry.getValue().getTitle())) {
//				Project project = entry.getValue();
//			}
//		}
//		return foundProjects;
//	}

	public ArrayList<Project> findProjectsContainingTitle(String title) {
		ArrayList<Project> foundProjects = new ArrayList<Project>();

		if (isSignedIn()) {
			for (Project project : projectList) {
				if (project.getTitle().contains(title)) {
					foundProjects.add(project);
				}
			}
			return foundProjects;
		}
		ErrorMessageHandler.addErrorMessage("Employee must be signed in");
		return null;

	}

	public Project getSpecificProjectByTitle(String title) {
		assert !projectList.isEmpty() && !multipleProjectsWithSameTitle(projectList, title);

		if (isSignedIn()) {
			for (Project project : projectList) {
				if (project.getTitle().equals(title)) {
					return project;
				}
			}
		}
		ErrorMessageHandler.addErrorMessage("Employee must be signed in");
		return null;

	}

	private boolean multipleProjectsWithSameTitle(ArrayList<Project> projectList, String title) {
		assert projectList.size() > 0;

		ArrayList<Project> foundProjects = new ArrayList<Project>();
		for (Project project : projectList) {
			if (project.getTitle().equals(title)) {
				foundProjects.add(project);
			}
		}
		return foundProjects.size() > 1 ? true : false;
	}

	public boolean multipleProjectsContainingTitleFound(String title) {
		assert projectList.size() > 1;

		boolean multipleProjectsFound = true;
		ErrorMessageHandler.addErrorMessage("More than one project with the title \"" + title + "\" has been found");
		return multipleProjectsFound;
	}

	public boolean noProjectContainingTitleFound(String title) {
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

	public ArrayList<Project> getProjectList() {
		return projectList;
	}

}
