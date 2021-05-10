Feature: White box test of multipleProjectsWithSameTitle() method 
	Description: Employee searches for multiple projects with the same title
Actor: Employee

Scenario: White box A 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And no projects exists in the application
	When the employee searches for project with title "Title 1"
	Then the error message "List of projects cannot be empty" is given

Scenario: White box B 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And project with title "Project title" exists in the application
	When the employee searches for project with title "Title 1"
	Then multiple projects with the title "Title 1" are not found

Scenario: White box C 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And project with title "Project title" exists in the application
	When the employee searches for project with title "Project title"
	Then multiple projects with the title "Project title" are not found

Scenario: White box D 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And project with titles "Project title" and "Project title 2" exists in the application
	When the employee searches for project with title "Project title"
	Then multiple projects with the title "Project title" are not found

Scenario: White box E 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And project with titles "Project title" and "Project title" exists in the application
	When the employee searches for project with title "Project title"
	Then multiple projects with the title "Project title" are found