package ProjectManagement;

public class MainHolder {
	
	public MainHolder() throws AuthException {
		app = new Application();
		
		// Remove this, maybe? But then all the scenarios need to create projects manually... Not sure.
		Employee testEmployee = new Employee("default_test_employee");
		app.addEmployee(testEmployee);
		app.signIn("default_test_employee");
		Employee testEmployee2 = new Employee("default_test_employee2");
		app.addEmployee(testEmployee2);
		app.signIn("default_test_employee2");
		
		int id = app.createProject("default_test_project");
		project = app.getProject(id);
	}
	
	public Application app;
    public Employee employee;
    public Project project;
}
