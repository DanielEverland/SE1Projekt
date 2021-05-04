package ProjectManagement;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.ArrayList;

public class SearchProjectSteps {
	
	private MainHolder holder;
	private ArrayList<Project> projects;
	
	public SearchProjectSteps(MainHolder holder) {
		this.holder = holder;
	}
	
	@When("the employee searches for the project with title {string}")
	public void the_employee_searches_for_the_project_with_title(String title) {
		projects = holder.app.findProjectsContainingTitle(title);
	}
	
	@Then("there are more than one project with the title {string}")
	public void there_are_more_than_one_project_with_the_title(String title) {
		assertThat(holder.app.multipleProjectsContainingTitleFound(title), is(equalTo(true)));
	}

	@Then("all projects that contain {string} in the title are found")
	public void all_projects_that_contain_in_the_title_are_found(String title) {
		ArrayList<Project> foundProjectsContainingTitle = holder.app.findProjectsContainingTitle(title);
		assertThat(projects.equals(foundProjectsContainingTitle), is(equalTo(true)));
	}
	
	@Then("there are no projects with the title {string}")
	public void there_are_no_projects_with_the_title(String string) {
		assertThat(holder.app.noProjectContainingTitleFound(string), is(equalTo(true)));
	}
	
	
}
