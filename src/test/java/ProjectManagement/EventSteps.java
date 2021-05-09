package ProjectManagement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventSteps {

	private MainHolder holder;
	private Activity activity = null;

	public EventSteps(MainHolder holder) {
		this.holder = holder;
	}

	@When("vacation from {string} to {string} is assigned to the employee")
	public void vacation_from_to_is_assigned_to_the_employee(String startDate, String endDate) {
		holder.app.assignVacation(holder.employee, Date.FromString(startDate), Date.FromString(endDate));

		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof Vacation && activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				this.activity = activity;
			}
		}
	}

	@When("sick leave from {string} to {string} is assigned to the employee")
	public void sick_leave_from_to_is_assigned_to_the_employee(String startDate, String endDate) {
		holder.app.assignSickLeave(holder.employee, Date.FromString(startDate), Date.FromString(endDate));

		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof SickLeave && activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				this.activity = activity;
			}
		}
	}

	@When("course with description {string} from {string} to {string} is assigned to the employee")
	public void course_with_description_from_to_is_assigned_to_the_employee(String description, String startDate,
			String endDate) {
		holder.app.assignCourse(holder.employee, description, Date.FromString(startDate), Date.FromString(endDate));

		for (Activity activity : holder.employee.getEvents()) {
			if (activity instanceof Course && ((Course) activity).getDescription().equals(description)
					&& activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate))) {
				this.activity = activity;
			}
		}
	}

	@When("the course has its description changed to {string}")
	public void the_course_has_its_description_changed_to(String newDescription) {
		((Course)activity).setDescription(newDescription);
	}

	@Then("the employee has a vacation from {string} to {string}")
	public void the_employee_has_a_vacation_from_to(String startDate, String endDate) {
		boolean hasVacation = activity instanceof Vacation && activity.getStartDate().equals(Date.FromString(startDate))
				&& activity.getEndDate().equals(Date.FromString(endDate));
		assertThat(hasVacation, is(equalTo(true)));
	}

	@Then("the activity produces string consisting of {string}, {string}, and {string}")
	public void the_activity_produces_string_consisting_of_and(String title, String startDate, String endDate) {
		String string = "Title: " + title + "\nStart date: " + startDate + "\nEnd date: " + endDate;
		assertThat(activity.toString(), is(string));
	}

	@Then("vacation is in interval {string} to {string}")
	public void vacation_is_in_interval_to(String startDate, String endDate) {
		assertThat(activity.isInDateInterval(Date.FromString(startDate), Date.FromString(endDate)), is(equalTo(true)));
	}

	@Then("vacation is not in interval {string} to {string}")
	public void vacation_is_not_in_interval_to(String startDate, String endDate) {
		assertThat(activity.isInDateInterval(Date.FromString(startDate), Date.FromString(endDate)), is(equalTo(false)));
	}

	@Then("the employee has sick leave from {string} to {string}")
	public void the_employee_has_sick_leave_from_to(String startDate, String endDate) {
		boolean hasSickLeave = activity instanceof SickLeave && activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate));
		assertThat(hasSickLeave, is(equalTo(true)));
	}

	@Then("the employee has a course with description {string} from {string} to {string}")
	public void the_employee_has_a_course_with_description_from_to(String description, String startDate,
			String endDate) {
		boolean hasCourse = activity instanceof Course && ((Course) activity).getDescription().equals(description)
					&& activity.getStartDate().equals(Date.FromString(startDate))
					&& activity.getEndDate().equals(Date.FromString(endDate));

		assertThat(hasCourse, is(equalTo(true)));
	}

	@Then("the course produces string consisting of {string}, {string}, {string}, and {string}")
	public void the_course_produces_string_consisting_of_and(String title, String startDate, String endDate, String description) {
		String string = "Title: " + title + "\nStart date: " + startDate + "\nEnd date: " + endDate + "\nDescription: " + description;
		assertThat(((Course)activity).toString(), is(string));
	}

	@Then("the employee is unavailable from {string} to {string}")
	public void the_employee_is_unavailable_from_to(String startDate, String endDate) {
		assertThat(holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(false)));
	}

	@Then("the employee is available from {string} to {string}")
	public void the_employee_is_available_from_to(String startDate, String endDate) {
		assertThat(holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate)),
				is(equalTo(true)));
	}
}
