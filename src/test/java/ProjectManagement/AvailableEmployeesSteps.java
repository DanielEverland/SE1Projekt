package ProjectManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.equalTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;

public class AvailableEmployeesSteps {
    private MainHolder holder;
    private ArrayList<Employee> availableEmployees;

    public AvailableEmployeesSteps(MainHolder holder) {
        this.holder = holder;
    }

    @When("a list of available employees is requested from date {int} to date {int}")
    public void a_list_of_available_employees_is_requested_from_date_to_date(Integer date1, Integer date2) {
        availableEmployees = holder.app.getAvailableEmployees(date1, date2);
    }

    @Then("the application returns a list containing employee with id {string}")
    public void the_application_returns_a_list_containing_employee_with_id(String id) {
        Employee matchingEmployee = null;
        for (Employee employee : availableEmployees) {
            if (employee.getId().equals(id)) {
                matchingEmployee = employee;
                break;
            }
        }

        assertThat(matchingEmployee != null, is(equalTo(true)));
        availableEmployees.remove(matchingEmployee);
    }
}