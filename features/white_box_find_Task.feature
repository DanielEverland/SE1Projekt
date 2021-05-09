Feature: White box test of findTask(String title, String description, Date startDate, Date endDate) method
	Description: A specific task on a project needs to be found
Actor: Employee

Scenario: White box A 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then null is returned
	
Scenario: White box B 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	And A task with title "Bad Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" exists 
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then null is returned
	
Scenario: White box C
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	And A task with title "Task title", description "Bad Task description", start date "01/01/2000" and end date "01/01/2001" exists 
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then null is returned

Scenario: White box D
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	And A task with title "Task title", description "Task description", start date "01/01/2002" and end date "01/01/2001" exists 
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then null is returned
	
Scenario: White box E
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	And A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2002" exists 
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then null is returned

Scenario: White box F
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "default_test_project" exists
	And A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" exists 
	When A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is searched for 
	Then A task with title "Task title", description "Task description", start date "01/01/2000" and end date "01/01/2001" is found 