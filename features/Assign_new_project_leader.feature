Feature: Assign new project leader
Description: New project leader is assigned to a project
Actor: Employee

Scenario: An employee is assigned as project leader to project
Given The project with title "default_test_project" exists
And an employee with id "test" exists in the application
And the employee is signed in
When an employee enters the ID of employee "test" who shall be the project manager of the project with title "default_test_project"
Then the employee with ID "test" is now the project manager of the project with title "default_test_project"

Scenario: An employee tries to assign a project leader but the project already has a project leader
Given The project with title "default_test_project" exists
And an employee with id "test_project_Leader" exists in the application
And an employee with id "test" exists in the application
And the employee is signed in
And the project with title "default_test_project" already has an assigned project leader with ID "test_project_Leader"
When an employee enters the ID of employee "test" who shall be the project manager of the project with title "default_test_project"
Then the employee with id "test_project_Leader" remains the project leader of the project "default_test_project"
And the error message "The project already has an assigned project leader" is given