package ProjectManagement;

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

	@Given("the employee has an existing task with title {string}, description {string}, start date {int} and end date {int}")
	public void theEmployeeHasAnExistingTaskWithTitleDescriptionStartDateAndEndDate(String title, String description,
			Integer startDate, Integer endDate) {
		
		holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
		holder.project.assignTaskToEmployee(holder.employee, holder.project.findTask(title, description, startDate, endDate));
		ArrayList<Task> projectTasks = holder.project.getTasks();
		assertTrue(projectTasks.stream()
				.anyMatch(m -> m.getTitle().contentEquals(title) && m.getDescription().contentEquals(description)));
		// ikke godt ^. Checker kun om titel og description matcher
	}

	@When("the employee inputs {double} hours worked on the task with title {string}, description {string}, start date {int} and end date {int}")
	public void the_employee_inputs_hours_worked_on_the_task_with_title_description_start_date_and_end_date(
			Double hoursWorked, String title, String description, Integer startDate, Integer endDate) {
		holder.project.findTask(title, description, startDate, endDate).logWorkHours(holder.employee, hoursWorked);

	}

	@Then("{double} hours is registered as worked on the task with title {string}, description {string}, start date {int} and end date {int}  by the employee")
	public void hours_is_registered_as_worked_on_the_task_with_title_description_start_date_and_end_date_by_the_employee(
			Double hoursWorked, String title, String description, Integer startDate, Integer endDate) {
		Map<Employee, Duration> minutesWorked = holder.project.findTask(title, description, startDate, endDate)
				.getMinutesWorked();
		Duration duration = minutesWorked.get(holder.employee);
		assertThat(duration.GetHoursPassed(), is(equalTo(hoursWorked)));
	}

}
