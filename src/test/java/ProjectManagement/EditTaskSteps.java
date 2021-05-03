package ProjectManagement;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import io.cucumber.java.en.*;

public class EditTaskSteps {

	private MainHolder holder;

	public EditTaskSteps(MainHolder holder) {
		this.holder = holder;
	}
	
	@Given("A task with title {string}, description {string}, start date {string} and end date {string} exists")
	public void a_task_with_title_description_start_date_and_end_date_exists(String title, String description, String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
	}
	
	
	@When("the employee edits title to {string} for task with title {string}, description {string}, start date {string} and end date {string}'")
	public void the_employee_edits_title_to_for_task_with_title_description_start_date_and_end_date(String newTitle,
			String title, String description, String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editTaskTitle(task, newTitle, holder.employee);
	}

	@Then("the task with title {string}, description {string}, start date {string} and end date {string} is removed")
	public void the_task_with_title_description_start_date_and_end_date_is_removed(String title, String description,
			String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, not(hasItem(task)));
	}

}
