Feature: Get list of available employees
Description: Employee requests a list of available employees in a time period. Typically used by project leaders looking to assign employees to a task.
Actor: Employee

Scenario: Successfully request a list of available employees from employees that are available
Given an employee with id "test1" exists in the application
And an employee with id "test2" exists in the application
When a list of available employees is requested from date 1 to date 2
And the application returns a list containing employee with id "test1"
And the application returns a list containing employee with id "test2"
