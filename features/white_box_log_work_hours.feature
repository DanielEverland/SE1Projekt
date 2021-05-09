Feature: White box test of logWorkHours() method 
	Description: Employee registers hours worked on task
Actor: Employee

Scenario: White box A 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has not worked on the task before 
	When the employee inputs 0.001 hours as worked on the task 
	Then the hours are not logged 
	
Scenario: White box B 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has worked on the task before 
	When the employee inputs 0.001 hours as worked on the task 
	Then the hours are not logged 
	
Scenario: White box C
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has not worked on the task before 
	And the task has expected time of 3.0 hours 
	When the employee inputs 1.5 hours as worked on the task 
	Then 1.5 hours is registered as worked on the task by the employee 

Scenario: White box D
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has not worked on the task before 
	And the task has expected time of 1.0 hours 
	When the employee inputs 1.5 hours as worked on the task 
	Then 1.5 hours is registered as worked on the task by the employee 
	And the error message "Too much time spent on task" is given
	
Scenario: White box E
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has worked on the task before 
	And the task has expected time of 3.0 hours 
	When the employee inputs 1.5 hours as worked on the task 
	Then 1.5 hours is registered as worked on the task by the employee 

Scenario: White box F
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And A task with title "White box title", description "White box description", start date "11/11/2011" and end date "12/12/2012" exists 
	And the employee has worked on the task before 
	And the task has expected time of 1.0 hours 
	When the employee inputs 1.5 hours as worked on the task 
	Then 1.5 hours is registered as worked on the task by the employee 
	And the error message "Too much time spent on task" is given 