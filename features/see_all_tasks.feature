Feature: See all tasks 
	Description: The employee wishes to see all tasks in the application
Actor: Employee


Scenario: All tasks are found 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "Test project" exists
	And the project with title "Test project" has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"  
	When the employee clicks to see all tasks in the application
	Then all tasks in the application are shown
