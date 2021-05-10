package ProjectManagement;

import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WhiteBoxFindTaskSteps {
	MainHolder holder;
	private boolean assertionTriggered;
	private Task task;

	public WhiteBoxFindTaskSteps(MainHolder holder) {
		this.holder = holder;
	}
	
	@When("A task with title not given as string, description {string}, start date {string} and end date {string} is searched for")
	public void a_task_with_title_not_given_as_string_description_start_date_and_end_date_is_searched_for(String Description, String startDate, String endDate) {
		this.task = holder.project.findTask(null, Description, Date.FromString(startDate), Date.FromString(endDate));
	}

	@When("A task with title {string}, description not given as string, start date {string} and end date {string} is searched for")
	public void a_task_with_title_description_not_given_as_string_start_date_and_end_date_is_searched_for(String Title, String startDate, String endDate) {
		this.task = holder.project.findTask(Title, null, Date.FromString(startDate), Date.FromString(endDate));
	}

	@When("A task with title {string}, description {string}, start date not given as string and end date {string} is searched for")
	public void a_task_with_title_description_start_date_not_given_as_string_and_end_date_is_searched_for(String Title, String Description, String endDate) {
		this.task = holder.project.findTask(Title, Description, null, Date.FromString(endDate));
	}

	@When("A task with title {string}, description {string}, start date {string} and end date not given as string is searched for")
	public void a_task_with_title_description_start_date_and_end_date_not_given_as_string_is_searched_for(String Title, String Description, String startDate) {
		this.task = holder.project.findTask(Title, Description, Date.FromString(startDate), null);
	}
	
	@When("A task with title {string}, description {string}, start date {string} and end date {string} is searched for")
	public void a_task_with_title_description_start_date_and_end_date_is_searched_for(String Title, String Description, String startDate, String endDate) {
	    this.task = holder.project.findTask(Title, Description, Date.FromString(startDate), Date.FromString(endDate));
	}
	
	@Then("null is returned")
	public void null_is_returned() {
	    assertTrue(task == null);
	}

	@Then("A task with title {string}, description {string}, start date {string} and end date {string} is found")
	public void a_task_with_title_description_start_date_and_end_date_is_returned(String Title, String Description, String startDate, String endDate) {
	   	assertTrue(task.getTitle().equals(Title) && task.getDescription().equals(Description) && task.getStartDate().equals(Date.FromString(startDate)) && task.getEndDate().equals(Date.FromString(endDate)));
	}


	
}
