Feature: Get list of available employees
Description: Employee requests a list of available employees in a time period. Typically used by project leaders looking to assign employees to a task.
Actor: Employee

Scenario: Successfully request a list of available employees from employees that are available
Given an employee with id "test1" exists in the application
And an employee with id "test2" exists in the application
When a list of available employees is requested from date "31/12/1998" to date "31/12/2000"
And the application returns a list containing employee with id "test1"
And the application returns a list containing employee with id "test2"

Scenario: Successfully request a list of available employees from employees of varying availability
Given an employee with id "test1" exists in the application
And an employee with id "test2" and maxTasks 1 exists in the application
And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"
When a list of available employees is requested from date "31/12/1998" to date "31/12/2000"
And the application returns a list containing employee with id "test1"
And the application returns a list that does not contain employee with id "test2"