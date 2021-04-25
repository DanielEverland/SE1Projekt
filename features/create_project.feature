Feature: Create Project
Description: The employee creates a project.
Actor: Employee.

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