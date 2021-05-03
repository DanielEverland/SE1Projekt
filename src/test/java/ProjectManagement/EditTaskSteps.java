package ProjectManagement;

import java.util.ArrayList;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import io.cucumber.java.en.*;

public class EditTaskSteps {

	private MainHolder holder;
	private Task task;
	
	public EditTaskSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("A task with title {string}, description {string}, start date {string} and end date {string} exists")
	public void a_task_with_title_description_start_date_and_end_date_exists(String title, String description,
			String startDateString, String endDateString) {
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
		task = holder.project.findTask(title, description, startDate, endDate);
		
	}
	
	@When("the employee edits title to {string} for task")
	public void the_employee_edits_title_to_for_task(String title) {
		holder.project.editTaskTitle(task, title, holder.employee);
	}

	@Then("A task with title {string} exists")
	public void a_task_with_title_exists(String title) {
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("title", is(title))));
	}

	@Then("the task with title {string} does not exist")
	public void the_task_with_title_does_not_exist(String title) {
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("title", not(is(title)))));
	}

	@When("the employee edits description to {string} for task")
	public void the_employee_edits_description_to_for_task(String newDescription) {
		holder.project.editTaskDescription(task, newDescription, holder.employee);
	}

	@Then("A task with description {string} exists")
	public void a_task_with_description_exists(String newDescription) {
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("description", is(newDescription))));
	}

	@Then("the task with description {string} does not exist")
	public void the_task_with_description_does_not_exist(String newDescription) {
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("description", not(is(newDescription)))));
	}
	
	@When("the employee edits start date to {string} for task")
	public void the_employee_edits_start_date_to_for_task(String newStartDateString) {
		Date newStartDate = Date.FromString(newStartDateString);
		holder.project.editTaskStartDate(task, newStartDate, holder.employee);
	}

	@Then("A task with start date {string} exists")
	public void a_task_with_start_date_exists(String newStartDateString) {
		Date newStartDate = Date.FromString(newStartDateString);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("startDate", is(newStartDate))));
	}

	@Then("the task with start date {string} does not exist")
	public void the_task_with_start_date_does_not_exist(String newStartDateString) {
		Date newStartDate = Date.FromString(newStartDateString);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("startDate", not(is(newStartDate)))));
	}

	@When("the employee edits end date to {string} for task")
	public void the_employee_edits_end_date_to_for_task(String newEndDateString) {
		Date newEndDate = Date.FromString(newEndDateString);
		holder.project.editTaskEndDate(task, newEndDate, holder.employee);
	}

	@Then("A task with end date {string} exists")
	public void a_task_with_end_date_exists(String newEndDateString) {
		Date newEndDate = Date.FromString(newEndDateString);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("endDate", is(newEndDate))));
	}

	@Then("the task with end date {string} does not exist")
	public void the_task_with_end_date_does_not_exist(String newEndDateString) {
		Date newEndDate = Date.FromString(newEndDateString);
		ArrayList<Task> tasks = holder.project.getTasks();
		assertThat(tasks, contains(hasProperty("endDate", not(is(newEndDate)))));
	}
	
	@Given("the task has {double} hours of expected time")
	public void the_task_has_hours_of_expected_time(Double newExpectedtime) {
		task.setExpectedTime(newExpectedtime);
	}

	@When("the employee edits expected time to {double} hours for task")
	public void the_employee_edits_expected_time_to_hours_for_task(Double newExpectedTime) {
		holder.project.editExpectedTimeForTask(task, newExpectedTime, holder.employee);
	}
	
	@Then("the task now has {double} hours of expected time")
	public void the_task_now_has_hours_of_expected_time(Double newExpectedTime) {
		assertThat(task.getExpectedTime(), is(equalTo(newExpectedTime)));
	}


}
