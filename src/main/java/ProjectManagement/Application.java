package ProjectManagement;

import java.util.*;

public class Application {
    private Map<Integer, Project> projects;
    private Map<String, Employee> employees;
    private Employee signedInEmployee;

    // Predefined list of employee ids
    private ArrayList<String> employeeIds = new ArrayList<String>(){{
        add("abcd");
        add("efgh");
    }};

    public Application() {
        projects = new HashMap<>();
        employees = new HashMap<>();
        for (String id : employeeIds) {
            employees.put(id, new Employee(id));
        }
    }

    public void createProject(String title) {
        if (isSignedIn()) {
            Project newProject = new Project(projects.size(), title);
            projects.put(newProject.getId(), newProject);
        }
    }

    public Map<Integer, Project> getProjectTitles() {
        return projects;
    }

    public Project getProject(int id) {
        return projects.get(id);
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getId(), employee);
    }

    public void signIn(String id) {
        signedInEmployee = employees.get(id);
    }

    public void signOut() {
        signedInEmployee = null;
    }

    public boolean isSignedIn() {
        return signedInEmployee != null;
    }
}
