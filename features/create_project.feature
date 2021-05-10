Feature: Create Project 
	Description: The employee creates a project.
Actor: Employee

Scenario: Employee successfully creates a new project 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee creates a new project with title "ProjectName" 
	Then project with title "ProjectName" is created 
	
Scenario: Invalid project title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee creates a new project with title "Invalid|Project|Name" 
	Then the error message "Title cannot be empty or contain '|'" is given 
	
Scenario: Missing sign in 
	Given an employee with id "test" exists in the application 
	When the employee creates a new project with title "ProjectName" 
	Then the error message "Employee must be signed in to create project" is given 
	
Scenario: More than one project is created 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee creates a new project with title "Project name 1" 
	Then project with title "Project name 1" is created 
	When the employee creates a new project with title "Project name 2" 
	Then project with title "Project name 2" is created
	
Scenario: ToString of project matches id and title
	Given an employee with id "test" exists in the application
	And the employee is signed in 
	When the employee creates a new project with title "ProjectName"
	Then projects toString is "1: ProjectName"

Scenario: Cannot create project with empty title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	When the employee creates a new project with title "" 
	Then the error message "Title cannot be empty or contain '|'" is given 