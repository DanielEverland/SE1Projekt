package ProjectManagement;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WhiteBoxLogWorkHoursSteps {
	MainHolder holder;
	private boolean assertionTriggered;

	public WhiteBoxLogWorkHoursSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("the employee has not worked on the task before")
	public void the_employee_has_not_worked_on_the_task_before() {
		holder.task.getDurationWorked().clear();
	}

	@Given("the employee has worked on the task before")
	public void the_employee_has_worked_on_the_task_before() {
		holder.task.getDurationWorked().put(holder.employee, new Duration());
	}

	@When("the employee inputs {double} hours as worked on the task")
	public void the_employee_inputs_hours_as_worked_on_the_task(Double hoursWorked) {
		try {
			holder.task.logWorkHours(holder.employee, hoursWorked);
		} catch (Throwable e) {
			assertionTriggered = true;
		}
	}
}
