Feature: Create Project
Description: The employee creates a project.
Actor: Employee.

Scenario: Employee successfully creates a new project
Given an employee with id "test" exists in the application
And the employee is signed in
When The employee creates a new project with title "ProjectName"
Then project with title "ProjectName" is created

#Scenario: Invalid project name
#Given An employee exists in the application
#When The employee tries to create a new project with an invalid project name
#Then The system notifies the employee that the name for the project is not valid
#
#Scenario The input data is not valid
#Given An employee exists in the application
#When The employee creates a new project with a given project name
#Then A new project is created with the given name
#When The employee tries to add invalid data to the project
#Then The system notifies the employee of the fact