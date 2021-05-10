package ProjectManagement;

import static org.junit.Assert.*;
import java.util.Collection;
import io.cucumber.java.en.*;

public class WhiteBoxMultipleProjectsWithSameTitleSteps {
	public Application app;
	
	public WhiteBoxMultipleProjectsWithSameTitleSteps() {
		app = new Application();
		Employee testEmployee = new Employee("default_test_employee");
		app.addEmployee(testEmployee);
		app.signIn("default_test_employee");
	}
	
	@Given("no projects exists in the application")
	public void no_projects_exists_in_the_application() {
	    assertTrue(app.getProjects().isEmpty());
	}

	@When("the employee searches for project with title {string}")
	public void the_employee_searches_for_project_with_title(String title) {
		app.multipleProjectsWithSameTitle(app.getProjects().values(), title);
	}
	
	@Given("project with title {string} exists in the application")
	public void project_with_title_exists_in_the_application(String title) {
	    app.createProject(title);
	}

	@Then("multiple projects with the title {string} are not found")
	public void multiple_projects_with_the_same_title_are_not_found(String title) {
	    assertFalse(app.multipleProjectsWithSameTitle(app.getProjects().values(), title));
	}
	
	@Given("project with titles {string} and {string} exists in the application")
	public void project_with_titles_and_exists_in_the_application(String title1, String title2) {
		app.createProject(title1);
		app.createProject(title2);
	}
	
	@Then("multiple projects with the title {string} are found")
	public void multiple_projects_with_the_title_are_found(String title) {
		assertTrue(app.multipleProjectsWithSameTitle(app.getProjects().values(), title));
	}

}
