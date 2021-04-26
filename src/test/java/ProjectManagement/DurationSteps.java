package ProjectManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class DurationSteps {

	Duration duration = new Duration();
	Double hours;

	@Given("Employee enters {double} hours worked")
	public void employee_enters_hours_worked(Double hours) {
		this.hours = hours;
	}

	@Then("an assertion error is triggered")
	public void an_assertion_error_is_triggered() {
		assertThrows(java.lang.AssertionError.class, () -> {
			duration.AddHours(hours);
		});
	}

	@Then("the hours worked can be logged")
	public void the_hours_worked_can_be_logged() {
		duration.AddHours(hours);
		assertThat(duration.GetHoursPassed(), is(equalTo(hours)));
	}

}
