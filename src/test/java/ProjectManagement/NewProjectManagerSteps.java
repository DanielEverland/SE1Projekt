package ProjectManagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewProjectManagerSteps {

	private MainHolder holder;
	
	private Scanner in = new Scanner(System.in);
	String sHolder="";

	public NewProjectManagerSteps(MainHolder holder) {
		this.holder = holder;
	}

	@Given("The project with ID {int} and title {string} exists")
	public void the_project_with_id_and_title_exists(Integer IDproject, String title) {
		assertTrue(holder.project.getId() == IDproject && holder.project.getTitle().equals(title));
	}

	@When("The employee {string} enters the ID of employee {string} who shall be the project manager of the project with ID {int} and title {string}")
	public void the_employee_enters_the_id_of_employee_who_shall_be_the_project_manager_of_the_project_with_id_and_title(
			String emp1, String emp2, Integer IDproject, String title) {
		holder.project.assignProjectLeader(holder.app.getEmployee(emp2));
	}

	@Then("The employee with ID {string} is now the project manager")
	public void the_employee_with_id_is_now_the_project_manager(String emp2) {
		assertTrue(holder.project.isProjectLeader(holder.app.getEmployee(emp2)));
	}

	@Given("The project with ID {int} already have a project manager")
	public void the_project_with_id_already_have_a_project_manager(Integer IDproject) {
		holder.project.assignProjectLeader(holder.app.getEmployee("default_test_employee2"));
	}

	@When("an employee with id {string} tries to overwrite the project manager with id {string}")
	public void an_employee_with_id_tries_to_overwrite_the_project_manager_with_id(String emp1, String emp2) {
		String test2 = emp2;
		assertTrue(holder.project.getProjectLeader() != null);
	}

	@Then("The system notifies the employee there is already a project manager assigned")
	public void the_system_notifies_the_employee_there_is_already_a_project_manager_assigned() {
		System.out.println(
				"A project manager is already assigned. Would you like to overwrite the existing manager (y/n)?");
	}

	@Then("The employee with id {string} chooses to overwrite the manager by typing {string}")
	public void the_employee_with_id_chooses_to_overwrite_the_manager_by_typing(String string, String s) {
		if (s.contentEquals("y")) {
			holder.project.assignProjectLeader(holder.app.getEmployee("default_test_employee"));
			System.out.println("The manager was overwritten.");
		}
		
	}

	@Then("the employee whose id was entered is now the project manager")
	public void the_employee_whose_name_was_entered_is_now_the_project_manager() {
		assertTrue(holder.project.getProjectLeader() == holder.app.getEmployee("default_test_employee"));
		
	}
	
	@Then("The employee with id {string} chooses not to overwrite the manager by typing {string}")
	public void the_employee_with_id_chooses_not_to_overwrite_the_manager_by_typing(String string, String s) {
		if (s.contentEquals("n")) {
			System.out.println("The manager was not overwritten.");
		}
	}
	
	@Then("the employee whose id was entered is not set as project manager")
	public void the_employee_whose_id_was_entered_is_not_set_as_project_manager() {
		assertFalse(holder.project.getProjectLeader() == holder.app.getEmployee("default_test_employee"));
	}
	
	@When("The employee {string} enters an invalid ID {string} who shall be the project manager")
	public void the_employee_enters_an_invalid_id_who_shall_be_the_project_manager(String emp1, String invalid) {
	    
	}
	
	
	@Then("The system notifies the employee that the input was invalid")
	public void the_system_notifies_the_employee_that_the_input_was_invalid() {
		System.out.println("The entered ID is invalid, try again.");
	}

	@When("the employee enters a valid employee name {string}")
	public void the_employee_enters_a_valid_employee_name(String valid) {
		while (true) {
			holder.project.assignProjectLeader(holder.app.getEmployee(valid));
			if (valid.contentEquals("default_test_employee") ) {
				holder.project.assignProjectLeader(holder.app.getEmployee("default_test_employee"));
				System.out.println("Project manager assigned");
				break;
			}
		}
			
	}

}
