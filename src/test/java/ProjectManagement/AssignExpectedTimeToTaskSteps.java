package ProjectManagement;

import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignExpectedTimeToTaskSteps {

	private MainHolder holder;
	private Project project;
	private Task task;

	public AssignExpectedTimeToTaskSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("the project with title {string} contains a task with name {string}, description {string}, start date {string} and end date {string}")
	public void the_project_with_title_contains_a_task_with_name_description_start_date_and_end_date(String title, String taskName,
			String description, String startDate, String endDate) throws AuthException {
		TaskConstructorInfo taskInfo = new TaskConstructorInfo(taskName, description, Date.FromString(startDate),
				Date.FromString(endDate));
		project = holder.app.getProjectByTitle(title);
		holder.app.getProjectByTitle(title).createTask(taskInfo);
		task = project.findTask(taskName, description, Date.FromString(startDate), Date.FromString(endDate));

	}

	@When("the project leader with id {string} enters the amount of hours {double} that he expect that the task will take to complete")
	public void the_project_leader_with_id_enters_the_amount_of_hours_that_he_expect_that_the_task_will_take_to_complete(
			String leaderID, Double hours) {

		if (project.isProjectLeader(holder.app.getEmployee(leaderID))) {
			task.setExpectedTime(hours);
		}

	}

	@Then("the task now has {double} hours remaining until completion")
	public void the_task_now_has_hours_remaining_until_completion(Double hours) {
		assertTrue(task.getExpectedTime() == hours);
	}

}
