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
    private Application app;
    private Employee employee;
    private MainHolder holder;

    public ProjectSteps(Application app, MainHolder holder) {
        this.app = app;
        this.holder = holder;
    }

    @Given("an employee with id {string} exists in the application")
    public void an_employee_with_id_exists_in_the_application(String id) {
        employee = new Employee(id);
        app.addEmployee(employee);
    }

    @Given("the employee is signed in")
    public void the_employee_is_signed_in() {
        app.signIn(employee.getId());
    }

    @When("The employee creates a new project with title {string}")
    public void the_employee_creates_a_new_project_with_title(String title) {
        app.createProject(title);
    }

    @Then("project with title {string} is created")
    public void project_with_title_is_created(String title) {
        Project project = app.getProject(title);
        assertNotNull(project);
        assertThat(project.getTitle(),is(equalTo(title)));
    }
}
