package ProjectManagement;

import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApplicationSteps {
	
	private MainHolder holder;
	
	public ApplicationSteps(MainHolder holder) {
		this.holder = holder;
	}

	@When("quitting the application")
	public void quitting_the_application() {
	    holder.app.quit();
	}

	@Then("the application has been quit")
	public void the_application_has_been_quit() {
	    assertTrue(holder.app.getIsQuitting());
	}
	
	@When("logging in with id {string}")
	public void logging_in_with_id(String employeeID) {
	    holder.app.signIn(employeeID);
	}
	
	@When("the employee creates a vacation with start date {string} and end date {string}")
	public void the_employee_creates_a_vacation_with_start_date_and_end_date(String startDate, String endDate) {
	    holder.app.getSignedInEmployee().assignToActivity(new Vacation(Date.FromString(startDate), Date.FromString(endDate)));
	}

	@Then("all activities visible to the user includes a vacation with start date {string} and end date {string}")
	public void all_activities_visible_to_the_user_includes_a_vacation_with_start_date_and_end_date(String startDate, String endDate) {
	    List<Activity> visibleActivities = holder.app.getAllActivities();
	    boolean foundTarget = false;
	    
	    for(Activity activity : visibleActivities) {
	    	if(activity instanceof Vacation && activity.startDate.equals(Date.FromString(startDate)) && activity.endDate.equals(Date.FromString(endDate))) {
	    		foundTarget = true;
	    		break;
	    	}
	    }
	    
	    assertTrue(foundTarget);
	}

	@Then("all activities visible to the user includes a task with title {string}, description {string}, start date {string} and end date {string}")
	public void all_activities_visible_to_the_user_includes_a_task_with_title_description_start_date_and_end_date(String title, String description, String startDate, String endDate) {
		List<Activity> visibleActivities = holder.app.getAllActivities();
	    boolean foundTarget = false;
	    
	    for(Activity activity : visibleActivities) {
	    	if(activity instanceof Task) {
	    		Task task = (Task)activity;
	    		if(task.title.equals(title) && task.getDescription().equals(description) && task.startDate.equals(Date.FromString(startDate)) && task.endDate.equals(Date.FromString(endDate))) {
	    			foundTarget = true;
		    		break;
	    		}
	    	}
	    }
	    
	    assertTrue(foundTarget);
	}

	@Then("all activities visible to the user include {int} activities in total")
	public void all_activities_visible_to_the_user_include_activities_in_total(Integer targetAmountOfActivities) {
	    assertTrue(targetAmountOfActivities == holder.app.getAllActivities().size());
	}
	
	@When("the employee gets the project with title {string}")
	public void the_employee_gets_the_project_with_title(String projectTitle) {
		try {
			holder.app.getSpecificProjectByTitle(projectTitle);			
		}
	    catch(Throwable e) {
	    	
	    }
	}
}
