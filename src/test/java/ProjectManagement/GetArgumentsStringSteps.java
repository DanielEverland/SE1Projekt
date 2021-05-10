package ProjectManagement;

import java.util.ArrayList;
import java.util.List;

import ProjectManagement.UserInterface.Controller;
import ProjectManagement.UserInterface.GenericArgumentsCommand;
import ProjectManagement.UserInterface.UserCommand;

import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class GetArgumentsStringSteps {
	
	private UserCommand command;
	private String stringRepresentation;
	private boolean hasThrown;
	
	@Given("no command exists")
	public void no_command_exists() {
		command = null;
	}
	
	@When("the string representation of the commands arguments is requested")
	public void the_string_representation_of_the_commands_arguments_is_requested() {
		try {
			stringRepresentation = Controller.convertArgumentsString(command);
		}
		catch (Throwable e) {
			hasThrown = true;
		}	    
	}
	
	@Then("an exception is thrown")
	public void an_exception_is_thrown() {
	    assertTrue(hasThrown);
	}
	
	@Given("a command exists with empty arguments")
	public void a_command_exists_with_empty_arguments() {
	    command = new GenericArgumentsCommand("name", (List<String> args) -> { }, new ArrayList<String>());
	}
	
	@Given("a command exists with null arguments")
	public void a_command_exists_with_null_arguments() {
		command = new GenericArgumentsCommand("name", (List<String> args) -> { }, null);
	}

	@Given("a command exists with the arguments")
	public void a_command_exists_with_the_arguments(DataTable data) {
		command = new GenericArgumentsCommand("name", (List<String> args) -> { }, data.asList());
	}
		
	@Then("the argument string is {string}")
	public void the_argument_string_is(String argumentString) {
	    assertEquals(argumentString, stringRepresentation);
	}
	
	
}