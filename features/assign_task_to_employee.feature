Feature: Assign task to employee 
	Description: The project leader assigns a task to an available employee
Actor: Project leader, employee

Scenario: Project leader successfully assigns task to employee 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And an employee with id "emp2" exists in the application 
	When The project leader creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the project leader assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the employee with id "emp2" is assigned to the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	
Scenario: The employee is unavailable 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And an employee with id "emp2" and maxTasks 0 exists in the application 
	When The project leader creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the project leader assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the error message "Employee is unavailable" is given