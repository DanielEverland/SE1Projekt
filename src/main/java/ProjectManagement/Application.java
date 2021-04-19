package ProjectManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private Map<String, Project> projects;
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
            projects.put(title, new Project(title));
        }
    }

    public Project getProject(String title) {
        return projects.get(title);
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
