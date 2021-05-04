Feature: Search for project 

	Description: An employee searches for projects with by title
Actor: Employee


Scenario: Search for substring of project title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the project with title "test" exists 
	And the project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then all projects that contain "Project" in the title are found 


Scenario: More than one project with the same title are found 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the project with title "test" exists 
	And the project with title "Project test" exists 
	And the project with title "Project test" exists 
	And the project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then there are more than one project with the title "Project" 
	And all projects that contain "Project" in the title are found 
	
Scenario: No project was found
	Given an employee with id "test" exists in the application
	And the employee is signed in
	And the project with title "Example test" exists 
	When the employee searches for the project with title "Project"
	Then there are no projects with the title "Project"
	And the error message "No project with the title \"Project\" has been found" is given
	
Scenario: Employee not signed in
	Given an employee with id "test" exists in the application 
	And the project with title "test" exists 
	And the project with title "Project test" exists 
	And the project with title "Project test" exists 
	And the project with title "Example Project" exists 
	When the employee searches for the project with title "Project" 
	Then the error message "Employee must be signed in" is given

Scenario: Searching for specific title with more than one project with the same title exists 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the project with title "test" exists 
	And the project with title "Project test" exists 
	And the project with title "Project test" exists 
	And the project with title "Example Project" exists 
	When the employee searches for the project with title "Project test" 
	Then there are more than one project with the title "Project test" 
	And all projects that contain "Project test" in the title are found 