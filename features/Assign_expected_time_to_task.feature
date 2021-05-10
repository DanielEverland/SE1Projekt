Feature: Assign expected time to task 
	Description: A project manager assigns an expected amount of time required to finish the task (minimumtime is half an hour)
Actor: Project leader

Scenario: Assign expected time to task 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And The project with title "test project" exists 
	And the employee is a project leader 
	And the project with title "test project" contains a task with name "default_test_task", description "test_task", start date "01/01/2000" and end date "01/01/2001" 
	When the project leader with id "emp1" enters the amount of hours 10.5 that he expect that the task will take to complete 
	Then the task now has 10.5 hours remaining until completion 
	
Scenario: Task has no expected time 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And The project with title "test project" exists 
	And the employee is a project leader 
	And the project with title "test project" contains a task with name "default_test_task", description "test_task", start date "01/01/2000" and end date "01/01/2001"
	When the project leader with id "emp1" enters the amount of hours 0.0 that he expect that the task will take to complete  
	Then the error message "Expected time must be greater than 0.0" is given