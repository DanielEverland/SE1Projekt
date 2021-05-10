Feature: Manage the application class
  Description: The application class contains all projects in the service
 
Scenario: Quitting will end the application
	When quitting the application
	Then the application has been quit
	
Scenario: Cannot sign in with non-existing employee
	When logging in with id "i-do-not-exist"
	Then the error message "No employee exists with the ID \"i-do-not-exist\"" is given
	
Scenario: Get all activities available to a given user
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	And the employee creates a vacation with start date "31/12/1998" and end date "31/12/2000"
	Then all activities visible to the user includes a vacation with start date "31/12/1998" and end date "31/12/2000"
	And all activities visible to the user includes a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	And all activities visible to the user include 2 activities in total

Scenario: Get all activities available without being logged in
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	And the employee creates a vacation with start date "31/12/1998" and end date "31/12/2000"
	And the employee is signed out
	And all activities visible to the user includes a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	And all activities visible to the user include 1 activities in total

Scenario: Cannot get specific project with duplicate name
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee creates a new project with title "ProjectName" 
	And the employee creates a new project with title "ProjectName" 
	And the employee gets the project with title "ProjectName"
	Then the error message "More than one project with the title \"ProjectName\" found" is given 