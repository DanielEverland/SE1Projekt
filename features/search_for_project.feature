Feature: Search for project
Description: An employee searches for projects
Actor: Employee

Scenario: More than one project with the same title exists
Given an employee with id "test" exists in the application
And the employee is signed in
And The project with title "Project test" exists
And The project with title "Project test" exists
When the employee searches for the project with title "Project test"
Then there are more than one project with the title "Project test"
And the error message "More than one project with the title Project test has been found" is given

Scenario: No project was found
Given an employee with id "test" exists in the application
And the employee is signed in
When the employee searches for the project with title "Project test"
Then there are no projects with the title "Project test"
And the error message "No project with the title Project test has been found" is given