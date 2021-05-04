package ProjectManagement;

import java.util.ArrayList;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SeeAllTasksSteps {

	MainHolder holder;
	private ArrayList<Task> allTasks;
	private ArrayList<Task> testTasks = new ArrayList<Task>();

	public SeeAllTasksSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("the project with title {string} has an existing task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_project_with_title_has_an_existing_task_with_title_description_start_date_and_end_date(
			String projectTitle, String taskTitle, String taskDescription, String taskStartDateString,
			String taskEndDateString) {
		Date startDate = Date.FromString(taskStartDateString);
		Date endDate = Date.FromString(taskEndDateString);

		Project project = holder.app.getSpecificProjectByTitle(projectTitle);
		project.createTask(new TaskConstructorInfo(taskTitle, taskDescription, startDate, endDate));
		Task task = project.findTask(taskTitle, taskDescription, startDate, endDate);
		testTasks.add(task);
	}

	@When("the employee clicks to see all tasks in the application")
	public void the_employee_clicks_to_see_all_tasks_in_the_application() {
		try {
			allTasks = holder.app.seeAllTasks();

		} catch (Throwable e) {
			ErrorMessageHandler.addErrorMessage(e.getMessage());
		}
	}

	@Then("all tasks in the application are found")
	public void all_tasks_in_the_application_are_found() {
		assertThat(allTasks.equals(testTasks), is(equalTo(true)));

	}

	@Given("the application has no tasks")
	public void the_application_has_no_tasks() {
		allTasks = new ArrayList<Task>();
	}

}
