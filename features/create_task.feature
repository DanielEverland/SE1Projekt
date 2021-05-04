Feature: Create Task 
	Description: Project leaders can create new tasks
Actor: Project Leader

Scenario: A project leader successfully creates a task 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The project leader creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then A task exists with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	
Scenario: Illegal character in title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The project leader creates a task with title "§§§§§§§", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then No task with title "§§§§§§§", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists 
	
Scenario: Too short title entered 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The project leader creates a task with title "g", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then No task with title "g", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists 
