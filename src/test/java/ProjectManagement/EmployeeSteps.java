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
        assertTrue(holder.project.getTasks().stream().anyMatch(m -> m.getTitle().contentEquals(title) && m.getDescription().contentEquals(description)));
        // ikke godt ^. Checker kun om titel og description matcher
    }
    
    @When("the employee inputs {double} hours worked on the task with title {string}, description {string}, start date {int} and end date {int}")
    public void the_employee_inputs_hours_worked_on_the_task_with_title_description_start_date_and_end_date(Double hoursWorked, String title, String description, Integer startDate, Integer endDate) {
    	holder.project.findTask(title, description, startDate, endDate).logWorkHours(holder.employee, hoursWorked);
    	
    }

    @Then("{double} hours is registered as worked on the task with title {string}, description {string}, start date {int} and end date {int}  by the employee")
    public void hours_is_registered_as_worked_on_the_task_with_title_description_start_date_and_end_date_by_the_employee(Double hoursWorked, String title, String description, Integer startDate, Integer endDate) {
    	
    }

}
