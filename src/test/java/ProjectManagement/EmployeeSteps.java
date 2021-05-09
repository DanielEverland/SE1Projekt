package ProjectManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.Matcher;

public class EmployeeSteps {

	private MainHolder holder;
	private List<Task> assignedTasksForEmployee;
	private List<Activity> assignedActivities;

	public EmployeeSteps(MainHolder holder) {
		this.holder = holder;
	}

	@When("an employee is created with id {string}")
	public void an_employee_is_created_with_id(String id) {
		holder.employee = new Employee(id);
	}

	@When("an employee is created with id {string} and max tasks {int}")
	public void an_employee_is_created_with_id_and_max_tasks(String id, int maxTasks) {
		holder.employee = new Employee(id, maxTasks);
	}

	@Then("the employee has id {string} and maxTasks {int}")
	public void the_employee_has_id_and_max_tasks(String id, Integer maxTasks) {
		assertThat(holder.employee, hasProperty("id", is(id)));
		assertThat(holder.employee, hasProperty("maxTasks", is(maxTasks)));
	}

	@When("a list of the employee's assigned activities is requested")
	public void a_list_of_the_employee_s_assigned_activities_is_requested() {
		assignedActivities = holder.employee.getAssignedActivites();
	}

	@Then("the list has {int} elements")
	public void the_list_has_elements(Integer count) {
		assertThat(assignedActivities.size(), is(equalTo(count)));
	}

	@Then("the list contains a task with title {string}, description {string}, start date {string} and end date {string}")
	public void the_list_contains_a_task_with_title_description_start_date_and_end_date(String title, String description, String startDate, String endDate) {
		boolean hasTask = false;
		for (Activity activity : assignedActivities) {
			if (activity instanceof Task) {
				Task task = (Task)activity;
				if (task.getTitle().equals(title) && task.getDescription().equals(description)
				    && task.getStartDate().equals(Date.FromString(startDate)) && task.getEndDate().equals(Date.FromString(endDate))) {
					hasTask = true;
					break;
				}
			}
		}

		assertTrue(hasTask);
	}

	@Then("the list contains a vacation from {string} to {string}")
	public void the_list_contains_a_vacation_from_to(String startDate, String endDate) {
		boolean hasVacation = false;
		for (Activity activity : assignedActivities) {
			if (activity instanceof Vacation) {
				Vacation vacation = (Vacation) activity;
				if (vacation.getStartDate().equals(Date.FromString(startDate))
					&& vacation.getEndDate().equals(Date.FromString(endDate))) {
					hasVacation = true;
					break;
				}
			}
		}

		assertTrue(hasVacation);
	}

	@When("the same vacation is attempted assigned to the employee")
	public void the_same_vacation_is_attempted_assigned_to_the_employee() {
		Vacation vacation = null;
		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof Vacation) {
				vacation = (Vacation)activity;
			}
		}

		holder.employee.assignToActivity(vacation);
	}

	@Given("the task is assigned to the employee")
	public void the_employee_is_assigned_to_the_task() {

		// Create a temporary project leader so employees can have tasks assigned to
		// them without being project leader
		boolean alreadyHasProjectLead = holder.project.hasProjectLeader();
		Employee currentSignedIn = holder.app.getSignedInEmployee();
		Employee currentProjectLead = holder.project.getProjectLeader();

		if (!alreadyHasProjectLead) {
			Employee tempEmployee = new Employee("test_temp_employee_task_creation");
			holder.app.addEmployee(tempEmployee);
			holder.app.signIn(tempEmployee.getId());
			holder.project.assignProjectLeader(tempEmployee);
		}

		holder.project.assignTaskToEmployee(holder.employee, holder.task);

		// Revert to cached state
		if (!alreadyHasProjectLead) {
			holder.app.signIn(currentSignedIn.getId());
			holder.project.removeProjectLeader();
			holder.project.assignProjectLeader(currentProjectLead);
		}
	}

	@When("the employee inputs {double} hours worked on the task")
	public void the_employee_inputs_hours_worked_on_the_task(Double hoursWorked) {
		holder.task.logWorkHours(holder.employee, hoursWorked);
	}

	@Then("{double} hours is registered as worked on the task by the employee")
	public void hours_is_registered_as_worked_on_the_task(Double hoursWorked) {
		Map<Employee, Duration> minutesWorked = holder.task.getDurationWorked();
		Duration duration = minutesWorked.get(holder.employee);
		assertThat(duration.getHoursPassed(), is(equalTo(hoursWorked)));
	}

	@Given("the task has expected time of {double} hours")
	public void the_task_has_expected_time_of_hours(Double expectedHours) {
		holder.task.setExpectedTime(expectedHours);
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

	@When("the employee marks the task as complete")
	public void the_employee_marks_the_task_as_complete() {
		holder.task.markAsCompleted();
	}

	@Then("the task is marked as completed")
	public void the_task_is_marked_as_completed() {
		assertTrue(holder.task.isCompleted());
	}
}
