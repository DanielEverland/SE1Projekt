Feature: See all tasks 
	Description: The employee wishes to see all tasks in the application
Actor: Employee


Scenario: All tasks are found 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee clicks to see all tasks in the application
	Then all tasks in the application are shown
