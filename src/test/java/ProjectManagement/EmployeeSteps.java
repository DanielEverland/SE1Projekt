package ProjectManagement;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {
	
	
	private MainHolder holder;

    public EmployeeSteps(MainHolder holder) {
        this.holder = holder;
    }
    @Given("the employee has an existing task with title {string}, description {string}, start date {int} and end date {int}")
    public void theEmployeeHasAnExistingTaskWithTitleDescriptionStartDateAndEndDate(String title, String description, Integer startDate, Integer endDate) {
        holder.project.createTask(new TaskConstructorInfo(title, description, startDate, endDate));
        holder.project.assignTaskToEmployee(holder.employee, holder.project.findTask(title, description, startDate, endDate));
        assertTrue(holder.project.getTasks().stream().anyMatch(m -> m.getTile().contentEquals(title) && m.getDescription().contentEquals(description)));
        // ikke godt ^. Checker kun om titel og description matcher
    }

    @When("the employee inputs {int} hours worked on the task")
    public void theEmployeeInputsHoursWorkedOnTheTask(Integer hours) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("{int} hours is registered as worked on the task by the employee")
    public void hoursIsRegisteredAsWorkedOnTheTaskByTheEmployee(Integer hours) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
