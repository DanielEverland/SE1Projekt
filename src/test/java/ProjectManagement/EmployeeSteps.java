package ProjectManagement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.ArrayList;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

	private MainHolder holder;

	public EmployeeSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("the employee has an existing task with title {string}, description {string}, start date {string} and end date {string}")
	public void theEmployeeHasAnExistingTaskWithTitleDescriptionStartDateAndEndDate(String title, String description, String startDateString, String endDateString) throws Exception {		
		Date startDate = Date.FromString(startDateString);
		Date endDate = Date.FromString(endDateString);
		
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
		holder.project.assignTaskToEmployee(holder.employee, holder.project.findTask(title, description, startDate, endDate));
		ArrayList<Task> projectTasks = holder.project.getTasks();
		assertTrue(projectTasks.stream()
				.anyMatch(m -> m.getTitle().contentEquals(title) && m.getDescription().contentEquals(description)));
		// ikke godt ^. Checker kun om titel og description matcher
		assertThat(holder.project.findTask(title, description, startDate, endDate).getStartDate(), is(equalTo(startDate)));
		assertThat(holder.project.findTask(title, description, startDate, endDate).getEndDate(), is(equalTo(endDate)));
		// så det bliver fikset sådan her selvom det er grimt
	}

	@When("the employee inputs {double} hours worked on the task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_employee_inputs_hours_worked_on_the_task_with_title_description_start_date_and_end_date(
			Double hoursWorked, String title, String description, String startDate, String endDate) {
		holder.project.findTask(title, description, Date.FromString(startDate), Date.FromString(endDate)).logWorkHours(holder.employee, hoursWorked);

	}

	@Then("{double} hours is registered as worked on the task with title {string}, description {string}, start date {string} and end date {string} by the employee")
	public void hours_is_registered_as_worked_on_the_task_with_title_description_start_date_and_end_date_by_the_employee(
			Double hoursWorked, String title, String description, String startDate, String endDate) {
		Map<Employee, Duration> minutesWorked = holder.project.findTask(title, description, Date.FromString(startDate), Date.FromString(endDate))
				.getMinutesWorked();
		Duration duration = minutesWorked.get(holder.employee);
		assertThat(duration.GetHoursPassed(), is(equalTo(hoursWorked)));
	}
	@Given("the task with title {string}, description {string}, start date {string} and end date {string} has expected time of {double} hours")
	public void the_task_with_title_description_start_date_and_end_date_has_expected_time_of_hours(String title, String description, String startDate, String endDate, Double expectedTime) {
		holder.project.findTask(title, description, Date.FromString(startDate), Date.FromString(endDate)).setExpectedTime(expectedTime);
	}
	
	@Given("worked time of {double} hours is larger than expected time of {double} hours")
	public void worked_time_of_hours_is_larger_than_expected_time_of_hours(Double workedHours, Double expectedHours) {
		assertTrue(workedHours > expectedHours);
	}

	@Then("the employee get the error message {string}")
	public void the_employee_get_the_error_message(String errorMessage) throws AuthException {
		assertEquals(errorMessage, holder.errorMessage);
	}
	
	@Given("the employee with id {string} is unavailable")
	public void the_employee_with_id_is_unavailable(String id) {
		holder.app.getEmployee(id).setAvailability(false);
	}
}
