package ProjectManagement;

import java.util.*;

public class Application {
	
	public static Application Get() {
		if(instance == null) {
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
    
    public int createProject(String title) throws AuthException {
        if (isSignedIn()) {
            Project newProject = new Project(newProjectId++, title);
            projects.put(newProject.getId(), newProject);
            return newProject.getId();
        }

		ErrorMessageHandler.addErrorMessage("Employee must be signed in to create project");
		return -1;
    }

    public Map<Integer, Project> getProjectTitles() {
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
        signedInEmployee = getEmployees().get(id);
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

}
