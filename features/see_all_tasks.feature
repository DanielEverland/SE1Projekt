Feature: See all tasks 
	Description: The employee wishes to see all tasks in the application
Actor: Employee


Scenario: All tasks are found 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the project with title "Test project" exists
	And the project with title "Test project" has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"  
	When the employee clicks to see all tasks in the application
	Then all tasks in the application are found

Scenario: No tasks exist 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the application has no tasks
	When the employee clicks to see all tasks in the application
	Then the error message "No tasks found" is given

Scenario: Employee not signed in 
	Given an employee with id "test" exists in the application 
	And the application has no tasks
	When the employee clicks to see all tasks in the application
	Then the error message "Employee must be signed in" is given

Scenario: All tasks found across two projects
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the project with title "Test project" exists
	And the project with title "Project example" exists
	And the project with title "Test project" has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"  
	And the project with title "Project example" has an existing task with title "Example title", description "Example description", start date "1/1/2000" and end date "1/1/2001"  
	When the employee clicks to see all tasks in the application
	Then all tasks in the application are found

