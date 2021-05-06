package ProjectManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.ArrayList;

public class SearchProjectSteps {

	private MainHolder holder;
	private ArrayList<Project> projects;
	private Project project;

	public SearchProjectSteps(MainHolder holder) {
		this.holder = holder;
	}
	
	@Given("the application has no existing projects")
	public void the_application_has_no_existing_projects() {
		holder.app.getProjects().clear();
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
	public void there_are_no_projects_with_the_title(String title) {
		assertThat(holder.app.noProjectContainingTitleFound(title), is(equalTo(true)));

	}

	@When("the employee searches specifically for the project with title {string}")
	public void the_employee_searches_specifically_for_the_project_with_title(String title) {
		try {
			project = holder.app.getSpecificProjectByTitle(title);

		} catch (Throwable e) {
			holder.errorMessage = (e.getMessage());
		}
	}

	@Then("the project with the title {string} is found")
	public void the_project_with_the_title_is_found(String title) {
		projects = holder.app.findProjectsContainingTitle(title);
		assertThat(project, is(equalTo(projects.get(0))));

	}


	@Then("there is no projects in the application")
	public void there_is_no_projects_in_the_application() {
		assertTrue(holder.app.getProjects().isEmpty());
	}

	@Then("the project is not found")
	public void the_project_is_not_found() {
		assertTrue(project == null);
	}

	@Then("there are not more than one project with the title {string}")
	public void there_are_not_more_than_one_project_with_the_title(String title) {
		assertThrows(java.lang.AssertionError.class, () -> {
			holder.app.multipleProjectsContainingTitleFound(title);
		});
		assertThrows(java.lang.AssertionError.class, () -> {
			holder.app.noProjectContainingTitleFound(title);
		});
	}

}
