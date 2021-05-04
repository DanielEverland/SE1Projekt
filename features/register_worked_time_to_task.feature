Feature: Register worked time to task 
	Description: The employee registers time worked on a given task with 0.5 hours precision.
Actor: Employee

Scenario: Employee register worked time to task 
	Given an employee with id "test" exists in the application 
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee is signed in 
	When the employee inputs 5.0 hours worked on the task 
	Then 5.0 hours is registered as worked on the task by the employee 
	
	#Scenario: Invalid input
	#Given an employee with id "test" exists in the application
	#And the employee has an existing task with title "Task title", description "Task description", start date 01 and end date 02 
	#And the employee is signed in
	#When the employee inputs "abc" hours worked on the task with title "Task title", description "Task description", start date 01 and end date 02 
	#Then "abc" hours is not registered as worked on the with title "Task title", description "Task description", start date 01 and end date 02 task by the employee
	
Scenario: Input greater than expected time 
	Given an employee with id "test" exists in the application 
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee is signed in 
	And the task has expected time of 3.0 hours 
	And worked time of 5.0 hours is larger than expected time of 3.0 hours 
	When the employee inputs 5.0 hours worked on the task 
	Then 5.0 hours is registered as worked on the task by the employee 
	And the error message "Too much time spent on task" is given