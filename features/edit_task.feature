Feature: Edit task 
	Description: The project leader edits a task
Actor: Project leader

Scenario: Project leader edits the title of a task 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And A task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists 
	When the employee edits title to "New title" for task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"' 
	Then A task exists with title "New title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	And the task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" does not exist 
	
