package ProjectManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ProjectManagement.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProjectSteps {

    private MainHolder holder;

    public ProjectSteps(MainHolder holder) {
        this.holder = holder;
    }

    @Given("an employee with id {string} exists in the application")
    public void an_employee_with_id_exists_in_the_application(String id) {
        holder.employee = new Employee(id);
        holder.app.addEmployee(holder.employee);
    }

    @Given("the employee is signed in")
    public void the_employee_is_signed_in() {
        holder.app.signIn(holder.employee.getId());
    }

    @Given("the employee is a project leader")
    public void the_employee_is_a_project_leader() {
        holder.project.assignProjectLeader(holder.employee);
    }

    @When("the employee creates a new project with title {string}")
    public void the_employee_creates_a_new_project_with_title(String title) {
        try {
            int id = holder.app.createProject(title);
            holder.project = holder.app.getProject(id);
        } catch (Throwable e) {
            holder.errorMessage = e.getMessage();
        }
    }

    @Then("project with title {string} is created")
    public void project_with_title_is_created(String title) {
        assertNotNull(holder.project);
        assertThat(holder.project.getTitle(), is(equalTo(title)));
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertEquals(errorMessage, holder.errorMessage);
    }

    @When("The project leader creates a task with title {string}, description {string}, start date {int} and end date {int}")
    public void the_project_leader_creates_a_task_with_title_description_start_date_and_end_date(String title, String description, Integer startDateUnix, Integer endDateUnix) {
        holder.project.createTask(new TaskConstructorInfo(title, description, startDateUnix, endDateUnix));
    }

    @Then("A task exists with title {string}, description {string}, start date {int} and end date {int}")
    public void a_task_exists_with_title_description_start_date_and_end_date(String title, String description, Integer startDateUnix, Integer endDateUnix) {
        assertThat(holder.project.containsTask(title, description, startDateUnix, endDateUnix), is(equalTo(true)));
    }
}
