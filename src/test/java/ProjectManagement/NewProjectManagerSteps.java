package ProjectManagement;

import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewProjectManagerSteps {

	private MainHolder holder;

	public NewProjectManagerSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("The project with title {string} exists")
	public void the_project_with_title_exists(String title) throws AuthException {
		holder.app.createProject(title);
	}

	@When("An employee enters the ID of employee {string} who shall be the project manager of the project with title {string}")
	public void an_employee_enters_the_id_of_employee_who_shall_be_the_project_manager_of_the_project_with_title(
			String employeeID, String title) {
		Project project = holder.app.getSpecificProjectByTitle(title);
		project.assignProjectLeader(holder.app.getEmployee(employeeID));
	}

	@Then("The employee with ID {string} is now the project manager of the project with title {string}")
	public void the_employee_with_id_is_now_the_project_manager_of_the_project_with_title(String employeeID,
			String title) {
		Project project = holder.app.getSpecificProjectByTitle(title);
		assertTrue(project.isProjectLeader(holder.app.getEmployee(employeeID)));
	}

}
