package ProjectManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;


public class EventSteps {

    private MainHolder holder;

    public EventSteps(MainHolder holder) {
        this.holder = holder;
    }

    @When("vacation from {string} to {string} is assigned to the employee")
    public void vacation_from_to_is_assigned_to_the_employee(String startDate, String endDate) {
        holder.app.assignVacation(holder.employee, Date.FromString(startDate), Date.FromString(endDate));
    }

    @Then("the employee has a vacation from {string} to {string}")
    public void the_employee_has_a_vacation_from_to(String startDate, String endDate) {
        boolean hasVacation = false;
        for (Activity activity : holder.employee.getEvents()) {
            if (activity instanceof Vacation && activity.getStartDate().equals(Date.FromString(startDate)) && activity.getEndDate().equals(Date.FromString(endDate))) {
                hasVacation = true;
            }
        }

        assertThat(hasVacation, is(equalTo(true)));
    }

    @Then("the employee is unavailable from {string} to {string}")
    public void the_employee_is_unavailable_from_to(String startDate, String endDate) {
        assertThat(holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate)), is(equalTo(false)));
    }
}
