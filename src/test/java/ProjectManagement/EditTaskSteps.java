package ProjectManagement;

import java.util.ArrayList;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import io.cucumber.java.en.*;

public class EditTaskSteps {

	private MainHolder holder;
	private boolean assertionTriggered;
	
	public EditTaskSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("A task with title {string}, description {string}, start date {string} and end date {string} exists")
	public void a_task_with_title_description_start_date_and_end_date_exists(String title, String description,
			String startDateString, String endDateString) {
		// Create a temporary project leader so we can create new tasks
		// This is ugly, but we need a way to make sure certain tasks exists without
		// requiring the scenario to specify a project leader
		boolean alreadyHasProjectLead = holder.project.hasProjectLeader();
		Employee currentSignedIn = holder.app.getSignedInEmployee();
		Employee currentProjectLead = holder.project.getProjectLeader();

		if (!alreadyHasProjectLead) {
			Employee tempEmployee = new Employee("test_temp_employee_task_creation");
			holder.app.addEmployee(tempEmployee);
			holder.app.signIn(tempEmployee.getId());
			holder.project.assignProjectLeader(tempEmployee);
		}

		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
		holder.task = holder.project.findTask(title, description, startDate, endDate);

		// Revert to cached state
		if (!alreadyHasProjectLead) {
			holder.app.signIn(currentSignedIn.getId());
			holder.project.removeProjectLeader();
			holder.project.assignProjectLeader(currentProjectLead);
		}
	}

	@When("the employee edits title to {string} for task")
	public void the_employee_edits_title_to_for_task(String title) {
		holder.project.editTaskTitle(holder.task, title, holder.employee);
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
		holder.project.editTaskDescription(holder.task, newDescription, holder.employee);
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
		holder.project.editTaskStartDate(holder.task, newStartDate, holder.employee);
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
		holder.project.editTaskEndDate(holder.task, newEndDate, holder.employee);
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
		holder.task.setExpectedTime(newExpectedtime);
	}

	@When("the employee edits expected time to {double} hours for task")
	public void the_employee_edits_expected_time_to_hours_for_task(Double newExpectedTime) {
		holder.project.editExpectedTimeForTask(holder.task, newExpectedTime, holder.employee);
	}

	@Then("the task now has {double} hours of expected time")
	public void the_task_now_has_hours_of_expected_time(Double newExpectedTime) {
		assertThat(holder.task.getExpectedTime(), is(equalTo(newExpectedTime)));
	}

	@When("the employee adds {double} hours to the expected time")
	public void the_employee_adds_hours_to_the_expected_time(Double hoursToAdd) {
		try {
			holder.task.addToExpectedTime(hoursToAdd);
			
		} catch (Throwable e) {
			ErrorMessageHandler.addErrorMessage("Expected time must be above 0.0 hours");
		}
		
	}

	@Then("the employee cannot add {double} hours to the expected time")
	public void the_employee_cannot_add_hours_to_the_expected_time(Double hoursToAdd) {
		assertThrows(java.lang.AssertionError.class, () -> {
			holder.task.addToExpectedTime(hoursToAdd);
		});
	}

}
