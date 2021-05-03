package ProjectManagement;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import io.cucumber.java.en.*;

public class EditTaskSteps {

	private MainHolder holder;

	public EditTaskSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("A task with title {string}, description {string}, start date {string} and end date {string} exists")
	public void a_task_with_title_description_start_date_and_end_date_exists(String title, String description,
			String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
	}

	@When("the employee edits title to {string} for task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_edits_title_to_for_task_with_title_description_start_date_and_end_date(String newTitle,
			String title, String description, String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editTaskTitle(task, newTitle, holder.employee);
	}

	@Then("the task with title {string}, description {string}, start date {string} and end date {string} does not exist")
	public void the_task_with_title_description_start_date_and_end_date_does_not_exist(String title, String description,
			String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, not(hasItem(task)));
	}

	@When("the employee edits description to {string} for task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_edits_description_to_for_task_with_title_description_start_date_and_end_date(
			String newDescription, String title, String description, String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editTaskDescription(task, newDescription, holder.employee);
	}

	@When("the employee edits start date to {string} for task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_edits_start_date_to_for_task_with_title_description_start_date_and_end_date(
			String newStartDateString, String title, String description, String startDateString, String endDateString) {
		Date newStartDate = Date.FromString(newStartDateString);
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editTaskStartDate(task, newStartDate, holder.employee);
	}

	@When("the employee edits end date to {string} for task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_edits_end_date_to_for_task_with_title_description_start_date_and_end_date(
			String newEndDateString, String title, String description, String startDateString, String endDateString) {
		Date newEndDate = Date.FromString(newEndDateString);
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editTaskEndDate(task, newEndDate, holder.employee);
	}

	@When("the employee edits expected time to {double} hours for task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_edits_expected_time_to_hours_for_task_with_title_description_start_date_and_end_date(
			Double newExpectedTime, String title, String description, String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.editExpectedTimeForTask(task, newExpectedTime, holder.employee);
	}

	@Then("the task with title {string}, description {string}, start date {string} and end date {string} has {double} hours of expected time")
	public void the_task_with_title_description_start_date_and_end_date_has_hours_of_expected_time(String title,
			String description, String startDateString, String endDateString, Double newExpectedTime) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		Task task = holder.project.findTask(title, description, startDate, endDate);
		assertThat(task.getExpectedTime(), is(equalTo(newExpectedTime)));
	}

}
