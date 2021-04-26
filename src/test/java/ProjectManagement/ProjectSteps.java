package ProjectManagement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ProjectManagement.*;
import static org.hamcrest.CoreMatchers.equalTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

    @When("The project leader creates a task with title {string}, description {string}, start date {string} and end date {string}")
    public void the_project_leader_creates_a_task_with_title_description_start_date_and_end_date(String title, String description, String startDate, String endDate) {
        holder.project.createTask(new TaskConstructorInfo(title, description, Date.FromString(startDate), Date.FromString(endDate)));
    }

    @Then("the error message {string} is given")
    public void the_error_message_is_given(String errorMessage) {
        assertEquals(errorMessage, holder.errorMessage);
    }

    @Then("A task exists with title {string}, description {string}, start date {string} and end date {string}")
    public void a_task_exists_with_title_description_start_date_and_end_date(String title, String description, String startDate, String endDate) {
        assertThat(holder.project.containsTask(title, description, Date.FromString(startDate), Date.FromString(endDate)), is(equalTo(true)));
    }
}
