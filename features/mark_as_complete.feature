Feature: Mark task or project as complete 
	Description: An employee marks a task or a project as complete
Actor: Employee

Scenario: Mark task as complete 
	Given an employee with id "test" exists in the application 
	And the employee is signed in
	And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists
	When the employee marks the task as complete 
	Then the task is marked as completed 
	
Scenario: Mark project as complete 
	Given an employee with id "test" exists in the application 
	And the employee is a project leader 
	When the employee marks the project as complete 
	Then the project is marked as completed 
	
Scenario: Mark completed task as complete 
	Given an employee with id "test" exists in the application
	And the employee is signed in
	And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists
	And the employee marks the task as complete 
	When the employee marks the task as complete 
	Then the error message "Task already marked as complete" is given 
	
Scenario: Mark completed project as complete 
	Given an employee with id "test" exists in the application 
	And the employee is a project leader 
	And the employee marks the project as complete 
	When the employee marks the project as complete 
	Then the error message "Project already marked as complete" is given 
