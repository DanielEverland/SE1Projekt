Feature: Edit task 
Description: The project leader edits the task
Actor: Project leader

Scenario: Project leader edits the title of a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists
	When the employee edits title to "New title" for task
	Then A task with title "New title" exists
	And the task with title "Test Title" does not exist
	
Scenario: Project leader edits the description of a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists
	When the employee edits description to "New Description" for task
	Then A task with description "New Description" exists
	And the task with description "Test Description" does not exist
	
Scenario: Project leader edits the start date of a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists
	When the employee edits start date to "01/12/2000" for task
	Then A task with start date "01/12/2000" exists
	And the task with start date "31/12/1998" does not exist

Scenario: Project leader edits the end date of a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists
	When the employee edits end date to "01/12/2000" for task
	Then A task with end date "01/12/2000" exists
	And the task with end date "31/12/2000" does not exist
	
Scenario: Project leader edits the expected time of a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists
	And the task has 3.0 hours of expected time
	When the employee edits expected time to 5.0 hours for task
	Then the task now has 5.0 hours of expected time
