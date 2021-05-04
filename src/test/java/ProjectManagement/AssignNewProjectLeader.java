package ProjectManagement;

import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignNewProjectLeader {

	private MainHolder holder;

	public AssignNewProjectLeader(MainHolder holder) {
		this.holder = holder;
	}

	@Given("The project with title {string} exists")
	public void the_project_with_title_exists(String title) throws AuthException {
		holder.app.createProject(title);
	}

	@When("an employee enters the ID of employee {string} who shall be the project manager of the project with title {string}")
	public void an_employee_enters_the_id_of_employee_who_shall_be_the_project_manager_of_the_project_with_title(
			String employeeID, String title) {
		Project project = holder.app.getSpecificProjectByTitle(title);
		project.assignProjectLeader(holder.app.getEmployee(employeeID));
	}

	@Then("the employee with ID {string} is now the project manager of the project with title {string}")
	public void the_employee_with_id_is_now_the_project_manager_of_the_project_with_title(String employeeID,
			String title) {
		Project project = holder.app.getSpecificProjectByTitle(title);
		assertTrue(project.isProjectLeader(holder.app.getEmployee(employeeID)));
	}

	@Given("the project with title {string} already has an assigned project leader with ID {string}")
	public void the_project_with_title_already_has_an_assigned_project_leader_with_id(String title, String employeeID) {

		Project project = holder.app.getSpecificProjectByTitle(title);
		project.assignProjectLeader(holder.app.getEmployee(employeeID));

	}

	@Then("the employee with id {string} remains the project leader of the project {string}")
	public void the_employee_with_id_remains_the_project_leader_of_the_project(String employeeID, String title) {
		Project project = holder.app.getSpecificProjectByTitle(title);
		assertTrue(project.getProjectLeader() == holder.app.getEmployee(employeeID));
	}

}
