package ProjectManagement;

public class MainHolder {
	
	public MainHolder()
	{
		app = new Application();
		
		// Remove this, maybe? But then all the scenarios need to create projects manually... Not sure.
		Employee testEmployee = new Employee("default_test_employee");
		app.addEmployee(testEmployee);
		app.signIn("default_test_employee");
		app.createProject("default_test_project");
		project = app.getProject("default_test_project");
		
	}
	
	public Application app;
    public Employee employee;
    public Project project;
}
