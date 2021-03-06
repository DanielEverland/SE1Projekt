Feature: Search for project 

	Description: An employee searches for projects with by title
Actor: Employee


Scenario: Search for substring of project title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "test" exists 
	And The project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then all projects that contain "Project" in the title are found 

Scenario: More than one project with the same title are found 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "test" exists 
	And The project with title "Project test" exists 
	And The project with title "Project test" exists 
	And The project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then there are more than one project with the title "Project" 
	And all projects that contain "Project" in the title are found 
	
Scenario: No project was found
	Given an employee with id "test" exists in the application
	And the employee is signed in
	And The project with title "Example test" exists 
	When the employee searches for the project with title "Project"
	Then there are no projects with the title "Project"
	And the error message "No project with the title \"Project\" has been found" is given
	
Scenario: Employee not signed in
	Given an employee with id "test" exists in the application 
	And The project with title "test" exists 
	And The project with title "Project test" exists 
	And The project with title "Project test" exists 
	And The project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then the error message "Employee must be signed in" is given

Scenario: Searching for title with more than one project with the same title exists 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "test" exists 
	And The project with title "Project test" exists 
	And The project with title "Project test" exists 
	And The project with title "Example Project" exists 
	When the employee searches for the project with title "Project test" 
	Then there are more than one project with the title "Project test" 
	And all projects that contain "Project test" in the title are found 

Scenario: Searching for a specific title with exactly one project with that title
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "Specific project title test" exists 
	When the employee searches specifically for the project with title "Specific project title test"
	Then the project with the title "Specific project title test" is found

Scenario: Searching for a specific title without being logged in
	Given an employee with id "test" exists in the application 
	And The project with title "Specific project title test" exists 
	When the employee searches specifically for the project with title "Specific project title test"
	Then the project is not found
	And the error message "Employee must be signed in" is given
	
Scenario: Searching for a specific title with more than one project of that title
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And The project with title "Project test" exists 
	And The project with title "Project test" exists
	When the employee searches specifically for the project with title "Project test"
	Then the error message "More than one project with the title \"Project test\" found" is given

Scenario: Less than one project exists 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the application has no existing projects
	And The project with title "project example" exists
	When the employee searches for the project with title "example" 
	Then there is not more than one project with the title "example"

Scenario: No projects exist in the application
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the application has no existing projects
	When the employee searches for the project with title "test" 
	Then there are no projects with the title "test"
	And there is no projects in the application

Scenario: Searching for specific project when no projects exist in the application
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the application has no existing projects
	When the employee searches specifically for the project with title "test" 
	Then there are no projects with the title "test"
	And there is no projects in the application