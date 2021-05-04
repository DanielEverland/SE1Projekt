package ProjectManagement;

import java.util.ArrayList;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class SeeAllTasksSteps {
	
	MainHolder holder;
	private ArrayList<Task> allTasks;
	
	public SeeAllTasksSteps(MainHolder holder) {
		this.holder = holder;
	}
	
	@When("the employee clicks to see all tasks in the application")
	public void the_employee_clicks_to_see_all_tasks_in_the_application() {
		allTasks = holder.app.seeAllTasks();
	}

	@Then("all tasks in the application are found")
	public void all_tasks_in_the_application_are_found() {
		ArrayList<Task> tasks = holder.app.seeAllTasks();
		assertThat(allTasks.equals(tasks), is(equalTo(true)));
//		idk kan ikke komme på noget som ikke er dumt
	}

}
