package ProjectManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.equalTo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {

	private MainHolder holder;

	public ProjectSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("an employee with id {string} exists in the application")
	public void an_employee_with_id_exists_in_the_application(String id) {
		holder.employee = new Employee(id);
		holder.app.addEmployee(holder.employee);
	}

	@Given("an employee with id {string} and maxTasks {int} exists in the application")
	public void an_employee_with_id_and_max_tasks_exists_in_the_application(String id, Integer maxTasks) {
		holder.employee = new Employee(id, maxTasks);
		holder.app.addEmployee(holder.employee);
	}

	@Given("the employee is signed in")
	public void the_employee_is_signed_in() {
		holder.app.signIn(holder.employee.getId());
	}

	@Given("the employee is a project leader")
	public void the_employee_is_a_project_leader() {
		holder.project.assignProjectLeader(holder.employee);
	}

	@When("the employee creates a new project with title {string}")
	public void the_employee_creates_a_new_project_with_title(String title) {
		try {
			int id = holder.app.createProject(title);
			holder.project = holder.app.getProject(id);
		} catch (Throwable e) {
			ErrorMessageHandler.addErrorMessage(e.getMessage());
		}
	}

	@Then("project with title {string} is created")
	public void project_with_title_is_created(String title) {
		assertNotNull(holder.project);
		assertThat(holder.project.getTitle(), is(equalTo(title)));
	}

	@When("The project leader creates a task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_project_leader_creates_a_task_with_title_description_start_date_and_end_date(String title,
			String description, String startDate, String endDate) {
		holder.project.createTask(
				new TaskConstructorInfo(title, description, Date.FromString(startDate), Date.FromString(endDate)));
	}

	@Then("the error message {string} is given")
	public void the_error_message_is_given(String errorMessage) {
		assertThat(ErrorMessageHandler.getPreviousErrorMessage(), is(equalTo(errorMessage)));
	}

	@Then("A task exists with title {string}, description {string}, start date {string} and end date {string}")
	public void a_task_exists_with_title_description_start_date_and_end_date(String title, String description,
			String startDate, String endDate) {
		assertThat(
				holder.project.containsTask(title, description, Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(true)));
	}

	@Then("No task with title {string}, description {string}, start date {string} and end date {string} exists")
	public void no_task_with_title_description_start_date_and_end_date_exists(String title, String description,
			String startDate, String endDate) {
		assertThat(
				holder.project.containsTask(title, description, Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(false)));
	}

	@When("the project leader assigns the task with title {string}, description {string}, start date {string} and end date {string} to employee with id {string}")
	public void the_project_leader_assigns_the_task_with_title_description_start_date_and_end_date_to_employee_with_id(
			String title, String description, String startDate, String endDate, String empID) {
		try {
			holder.project.assignTaskToEmployee(holder.app.getEmployee(empID),
					holder.project.findTask(title, description, Date.FromString(startDate), Date.FromString(endDate)));
		} catch (Throwable e) {
			holder.errorMessage = e.getMessage();
		}
	}

	@Then("the employee with id {string} is assigned to the task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_with_id_is_assigned_to_the_task_with_title_description_start_date_and_end_date(
			String empID, String title, String description, String startDate, String endDate) {
		assertTrue(holder.app.getEmployee(empID).getTasks().stream()
				.anyMatch(m -> m.getTitle().contentEquals(title) && m.getDescription().contentEquals(description)
						&& m.getStartDate().toString().equals(startDate) && m.getEndDate().toString().equals(endDate)));
	}

	@When("the employee marks the project as complete")
	public void the_employee_marks_the_project_as_complete() {
		holder.project.markAsCompleted();
	}

	@Then("the project is marked as completed")
	public void the_project_is_marked_as_completed() {
		assertTrue(holder.project.isCompleted());
	}


	
	
}
