package ProjectManagement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventSteps {

	private MainHolder holder;

	public EventSteps(MainHolder holder) {
		this.holder = holder;
	}

	@When("vacation from {string} to {string} is assigned to the employee")
	public void vacation_from_to_is_assigned_to_the_employee(String startDate, String endDate) {
		holder.app.assignVacation(holder.employee, Date.FromString(startDate), Date.FromString(endDate));
	}

	@When("sick leave from {string} to {string} is assigned to the employee")
	public void sick_leave_from_to_is_assigned_to_the_employee(String startDate, String endDate) {
		holder.app.assignSickLeave(holder.employee, Date.FromString(startDate), Date.FromString(endDate));
	}

	@When("course with description {string} from {string} to {string} is assigned to the employee")
	public void course_with_description_from_to_is_assigned_to_the_employee(String description, String startDate,
			String endDate) {
		holder.app.assignCourse(holder.employee, description, Date.FromString(startDate), Date.FromString(endDate));
	}

	@Then("the employee has a vacation from {string} to {string}")
	public void the_employee_has_a_vacation_from_to(String startDate, String endDate) {
		boolean hasVacation = false;
		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof Vacation && activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				hasVacation = true;
			}
		}

		assertThat(hasVacation, is(equalTo(true)));
	}

	@Then("the employee has sick leave from {string} to {string}")
	public void the_employee_has_sick_leave_from_to(String startDate, String endDate) {
		boolean hasSickLeave = false;
		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof SickLeave && activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				hasSickLeave = true;
			}
		}

		assertThat(hasSickLeave, is(equalTo(true)));
	}

	@Then("the employee has a course with description {string} from {string} to {string}")
	public void the_employee_has_a_course_with_description_from_to(String description, String startDate,
			String endDate) {
		boolean hasCourse = false;
		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof Course && ((Course) activity).getDescription().equals(description)
					&& activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				hasCourse = true;
			}
		}

		assertThat(hasCourse, is(equalTo(true)));
	}

	@Then("the employee is unavailable from {string} to {string}")
	public void the_employee_is_unavailable_from_to(String startDate, String endDate) {
		assertThat(holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(false)));
	}

	@Then("the employee is not unavailable from {string} to {string}")
	public void the_employee_is_not_unavailable_from_to(String startDate, String endDate) {
		assertThat(holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(true)));
	}
}
