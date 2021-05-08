Feature: Register worked time to task 
	Description: The employee registers time worked on a given task with 0.5 hours precision.
Actor: Employee

Scenario: Employee register worked time to task 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists 
	When the employee inputs 5.0 hours worked on the task 
	Then 5.0 hours is registered as worked on the task by the employee 
	
Scenario: Input greater than expected time 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists 
	And the task has expected time of 3.0 hours 
	And worked time of 5.0 hours is larger than expected time of 3.0 hours 
	When the employee inputs 5.0 hours worked on the task 
	Then 5.0 hours is registered as worked on the task by the employee 
	And the error message "Too much time spent on task" is given 
	
Scenario: See registered time to a task 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists 
	When the employee inputs 5.0 hours worked on the task 
	And the employee clicks to see number of hours worked on the task 
	Then the employee can see 5.0 hours registered to the task 
