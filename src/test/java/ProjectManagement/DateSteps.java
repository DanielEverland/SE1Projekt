package ProjectManagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class DateSteps {
	
	private MainHolder mainHolder;
	private Date currentDate;
	private Date anotherDate;
	private int calculatedHashcode;
	private boolean assertionTriggered;
	
	public DateSteps(MainHolder mainHolder) {
		this.mainHolder = mainHolder; 
	}

	@Given("the current date is {string}")
	public void the_current_date_is(String dateString) {
		currentDate = Date.FromString(dateString);    
	}

	@When("another date is {string}")
	public void another_date_is(String dateString) {
		anotherDate = Date.FromString(dateString);
	}

	@Then("their hashcodes are different")
	public void their_hashcodes_are_different() {
	    assertThat(currentDate.hashCode(), is(not(equalTo(anotherDate.hashCode()))));
	}
	
	@Given("the current date is unspecified")
	public void the_current_date_is_unspecified() {
	    currentDate = new Date();
	}
	
	@Given("another date is unspecified")
	public void another_date_is_unspecified() {
	    anotherDate = new Date();
	}

	@When("the hashcode is calculated")
	public void the_hashcode_is_calculate() {
		try {
			calculatedHashcode = currentDate.hashCode();
		}
		catch(java.lang.AssertionError exception) {
			assertionTriggered = true;
		}		
	}

	@Then("no assertion error is triggered")
	public void no_assertion_error_is_triggered() {
	    assertFalse(assertionTriggered);
	}
	
	@Then("their dates are not equal")
	public void their_dates_are_not_equal() {
	    assertFalse(currentDate.equals(anotherDate));
	}

	@Then("their dates are equal")
	public void their_dates_are_equal() {
	    assertTrue(currentDate.equals(anotherDate));
	}
	
	@Then("another object type is not equal")
	public void another_object_type_is_not_equal() {
	    assertFalse(currentDate.equals(10));
	}
}
