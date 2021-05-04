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

	@Given("the project with title {string} exists and the project contains a task with name {string}")
	public void the_project_with_title_exists_and_the_project_contains_a_task_with_name(String title, String taskName)
			throws AuthException {
		TaskConstructorInfo taskInfo = new TaskConstructorInfo(taskName, "test_task", Date.FromString("01/01/2000"),
				Date.FromString("01/01/2001"));
		holder.app.createProject(title);
		project = holder.app.getProjectByTitle(title);
		project.createTask(taskInfo);
		task = project.getTasks().get(0);
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
