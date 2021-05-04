package ProjectManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

	private MainHolder holder;
	private Task task;
	private List<Task> assignedTasksForEmployee;

	public EmployeeSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("the employee has an existing task with title {string}, description {string}, start date {string} and end date {string}")
	public void theEmployeeHasAnExistingTaskWithTitleDescriptionStartDateAndEndDate(String title, String description,
			String startDateString, String endDateString) {

		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);

		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));

		task = holder.project.findTask(title, description, startDate, endDate);
		holder.project.assignTaskToEmployee(holder.employee, task);
	}

	@When("the employee inputs {double} hours worked on the task")
	public void the_employee_inputs_hours_worked_on_the_task(Double hoursWorked) {
		task.logWorkHours(holder.employee, hoursWorked);
	}
	
	@Then("{double} hours is registered as worked on the task by the employee")
	public void hours_is_registered_as_worked_on_the_task(Double hoursWorked) {
		Map<Employee, Duration> minutesWorked = task.getMinutesWorked();
		Duration duration = minutesWorked.get(holder.employee);
		assertThat(duration.GetHoursPassed(), is(equalTo(hoursWorked)));
	}

	@Given("the task has expected time of {double} hours")
	public void the_task_has_expected_time_of_hours(Double expectedHours) {
		task.setExpectedTime(expectedHours);
	}

	@Given("worked time of {double} hours is larger than expected time of {double} hours")
	public void worked_time_of_hours_is_larger_than_expected_time_of_hours(Double workedHours, Double expectedHours) {
		assertTrue(workedHours > expectedHours);
	}

	@When("the employee enters the ID {string}")
	public void the_employee_enters_the_id(String id) {
		assignedTasksForEmployee = holder.app.searchAssignedTasksForEmployee(id);
	}

	@Then("all tasks that are assigned to the ID are found")
	public void all_tasks_that_are_assigend_to_the_id_are_found() {
		List<Task> tasks = holder.employee.getTasks();
		assertThat(assignedTasksForEmployee, containsInAnyOrder(tasks.toArray()));
	}

	@Then("the employee with id {string} has no assigned tasks")
	public void the_employee_with_id_has_no_assigned_tasks(String id) {
		Employee employee = holder.app.getEmployees().get(id);
		assertTrue(employee.getTasks().isEmpty());
	}

	@Then("the employee get the error message {string}")
	public void the_employee_get_the_error_message(String errorMessage) throws AuthException {
		assertEquals(errorMessage, holder.errorMessage);
	}

	@When("the employee marks the task as complete")
	public void the_employee_marks_the_task_as_complete() {
		task.markAsCompleted();
	}

	@Then("the task is marked as completed")
	public void the_task_is_marked_as_completed() {
		assertTrue(task.isCompleted());
	}
}
