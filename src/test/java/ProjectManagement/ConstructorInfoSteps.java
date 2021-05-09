package ProjectManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConstructorInfoSteps {

	private MainHolder holder;
	private ActivityConstructorInfo aci;
	private TaskConstructorInfo tci;

	public ConstructorInfoSteps(MainHolder holder) {
		this.holder = holder;
	}

	@When("an ActivityConstructorInfo with no arguments is created")
	public void an_activity_constructor_info_with_no_arguments_is_created() {
		aci = new ActivityConstructorInfo();
	}

	@When("an ActivityConstructorInfo with title {string}, start date {string}, end date {string} and isBlocking is created")
	public void an_activity_constructor_info_with_title_start_date_end_date_and_is_blocking_is_created(String title, String startDate, String endDate) {
		aci = new ActivityConstructorInfo(title, Date.FromString(startDate), Date.FromString(endDate), true);
	}

	@Then("the ActivityConstructorInfo has title {string}, start date {string}, end date {string}, and not isBlocking")
	public void the_activity_constructor_info_has_title_start_date_end_date_and_not_is_blocking(String title, String startDate, String endDate) {
		assertThat(aci.title, is(equalTo(title)));
		assertThat(aci.startDate, is(equalTo(Date.FromString(startDate))));
		assertThat(aci.endDate, is(equalTo(Date.FromString(endDate))));
		assertThat(aci.isBlocking, is(false));
	}

	@Then("the ActivityConstructorInfo has title {string}, start date {string}, end date {string}, and isBlocking")
	public void the_activity_constructor_info_has_title_start_date_end_date_and_is_blocking(String title, String startDate, String endDate) {
		assertThat(aci.title, is(equalTo(title)));
		assertThat(aci.startDate, is(equalTo(Date.FromString(startDate))));
		assertThat(aci.endDate, is(equalTo(Date.FromString(endDate))));
		assertThat(aci.isBlocking, is(true));
	}

	@Then("the ActivityConstructorInfo is valid")
	public void the_activity_constructor_info_is_valid() {
		assertTrue(aci.isValid());
	}

	@Then("the ActivityConstructorInfo is invalid")
	public void the_activity_constructor_info_is_invalid() {
		assertFalse(aci.isValid());
	}

	@When("a TaskConstructorInfo with no arguments is created")
	public void a_task_constructor_info_with_no_arguments_is_created() {
		tci = new TaskConstructorInfo();
	}

	@When("a TaskConstructorInfo with title {string}, description {string}, start date {string}, and end date {string}")
	public void a_task_constructor_info_with_title_description_start_date_and_end_date(String title, String description, String startDate, String endDate) {
		tci = new TaskConstructorInfo(title, description, Date.FromString(startDate), Date.FromString(endDate));
	}

	@Then("the TaskConstructorInfo has title {string}, description {string}, start date {string}, end date {string}, and not isBlocking")
	public void the_task_constructor_info_has_title_description_start_date_end_date_and_not_is_blocking(String title, String description, String startDate, String endDate) {
		assertThat(tci.title, is(equalTo(title)));
		assertThat(tci.description, is(equalTo(description)));
		assertThat(tci.startDate, is(equalTo(Date.FromString(startDate))));
		assertThat(tci.endDate, is(equalTo(Date.FromString(endDate))));
		assertThat(tci.isBlocking, is(false));
	}
}
