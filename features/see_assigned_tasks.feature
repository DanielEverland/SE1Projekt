Feature: See assigned tasks 
	Description: The employee wishes to see all tasks assigned to the employee
Actor: Employee

Scenario: Employee wants to see ass tasked assigned to the employee 
	Given an employee with id "test" exists in the application 
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee has an existing task with title "test title", description "test description", start date "1/1/1998" and end date "1/1/2000" 
	And the employee is signed in 
	When the employee enters the ID "test" 
	Then all tasks that are assigned to the ID are found 
	
Scenario: No assigned tasks for input 
	Given an employee with id "abcd" exists in the application 
	And the employee is signed in 
	When the employee enters the ID "abcd" 
	Then the employee with id "abcd" has no assigned tasks 
	And the error message "No assigned tasks for this identification code" is given 
