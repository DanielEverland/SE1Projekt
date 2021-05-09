package ProjectManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

public class AvailableEmployeesSteps {
    private MainHolder holder;
    private ArrayList<Employee> availableEmployees;
    private boolean isAvailable;

    public AvailableEmployeesSteps(MainHolder holder) {
        this.holder = holder;
    }

    @When("the employee's availability is checked from date {string} to date {string}")
    public void the_employee_s_availability_is_checked_from_date_to_date(String startDate, String endDate) {
        try {
            isAvailable = holder.employee.isAvailable(Date.FromString(startDate), Date.FromString(endDate));
        } catch (Exception e) {
            ErrorMessageHandler.addErrorMessage(e.getMessage());
        }
    }

    @When("a list of available employees is requested from date {string} to date {string}")
    public void a_list_of_available_employees_is_requested_from_date_to_date(String startDate, String endDate) {
        try {
            availableEmployees = holder.app.getAvailableEmployees(Date.FromString(startDate), Date.FromString(endDate));
        } catch (Exception e) {
            ErrorMessageHandler.addErrorMessage(e.getMessage());
        }
    }

    @Then("the employee is not available")
    public void the_employee_is_not_available() {
        assertFalse(isAvailable);
    }

    @Then("the employee is available")
    public void the_employee_is_available() {
        assertTrue(isAvailable);
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
    }

    @Then("the application returns a list that does not contain employee with id {string}")
    public void the_application_returns_a_list_that_does_not_contain_employee_with_id(String id) {
        Employee matchingEmployee = null;
        for (Employee employee : availableEmployees) {
            if (employee.getId().equals(id)) {
                matchingEmployee = employee;
                break;
            }
        }

        assertThat(matchingEmployee, is(equalTo(null)));
    }
}
