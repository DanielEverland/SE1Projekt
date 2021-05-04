package ProjectManagement;

import java.util.*;

public class Application {

	public static Application Get() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	private static Application instance;
  
  private Map<Integer, Project> projects;
  private Map<String, Employee> employees;
  private Employee signedInEmployee;

  private int newProjectId = 0;
  private boolean isQuitting;

  // Predefined list of employee ids
  private ArrayList<String> employeeIds = new ArrayList<String>(){{
      add("abcd");
      add("efgh");
  }};

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
    	if(!employees.containsKey(id)) {
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

	private ArrayList<Project> findProjectsByTitle(String title) {
		ArrayList<Project> foundProjects = new ArrayList<Project>();
		for (Map.Entry<Integer, Project> entry : projects.entrySet()) {
			if (title.equals(entry.getValue().getTitle())) {
				Project project = entry.getValue();
				foundProjects.add(project);
			}
		}
		return foundProjects;
	}

	public Project getProjectByTitle(String title) {
		ArrayList<Project> foundProjects = findProjectsByTitle(title);

		if (foundProjects.size() == 1) {
			return foundProjects.get(0);
		} else {
			return null;
		}

	}

	public boolean isMoreThanOneProjectFound(String title) {
		ArrayList<Project> foundProjects = findProjectsByTitle(title);
		boolean multipleProjectsFound = foundProjects.size() > 1;
		if (multipleProjectsFound) {
			ErrorMessageHandler.addErrorMessage("More than one project with the title " + title + " has been found");

		}
		return multipleProjectsFound;


	public boolean isNoProjectsFound(String title) {
		ArrayList<Project> foundProjects = findProjectsByTitle(title);
		boolean noProjectsFound = foundProjects.size() < 1;
		if (noProjectsFound) {
			ErrorMessageHandler.addErrorMessage("No project with the title " + title + " has been found");
		}
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
