package ProjectManagement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class DurationSteps {

	Duration duration = new Duration();
	Double timePassedInHours;
	boolean assertionTriggered;

	@Given("time passed is {double} hours")
	public void time_passed_is_hours(Double hoursPassed) {
		timePassedInHours = hoursPassed;
	}

	@When("duration is set to time passed")
	public void duration_is_set_to_time_passed() {
		try {
			duration.AddHours(timePassedInHours);
		} catch (java.lang.AssertionError e) {
			assertionTriggered = true;
		}
	}

	@Then("duration is equal to {double} hours")
	public void duration_is_equal_to_hours(Double hoursPassed) {
		assertThat(duration.GetHoursPassed(), is(equalTo(hoursPassed)));
	}

	@Then("an assertion error is triggered")
	public void an_assertion_error_is_triggered() {
		assertTrue(assertionTriggered);
	}

	@When("adding {double} hours and {int} minutes to duration")
	public void adding_hours_and_minutes_to_duration(Double hours, Integer minutes) {
		duration.AddTime(hours, minutes);
	}

	@Then("duration minutes passed is equal to {long} minutes")
	public void duration_minutes_passed_is_equal_to_minutes(Long minutesPassed) {
		assertThat(duration.GetMinutesPassed(), is(equalTo(minutesPassed)));
	}

	@When("adding {int} minutes to duration")
	public void adding_minutes_to_duration(Integer minutesToAdd) {
		try {
			duration.AddMinutes(minutesToAdd);
		} catch (java.lang.AssertionError e) {
			assertionTriggered = true;
		}
	}
}
