Feature: Get list of available employees 
	Description: Employee requests a list of available employees in a time period. Typically used by project leaders looking to assign employees to a task.
Actor: Employee

Scenario:
Successfully request a list of available employees from employees that are available 
	Given an employee with id "test1" exists in the application 
	And an employee with id "test2" exists in the application 
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000" 
	Then the application returns a list containing employee with id "test1" 
	And the application returns a list containing employee with id "test2" 
	
Scenario:
Successfully request a list of available employees from employees of varying availability 
	Given an employee with id "test1" exists in the application 
	And an employee with id "test2" and maxTasks 1 exists in the application 
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000" 
	Then the application returns a list containing employee with id "test1" 
	And the application returns a list that does not contain employee with id "test2"

Scenario: Request available employees from an employee with non-overlapping tasks
	Given an employee with id "test" and maxTasks 2 exists in the application
	And the employee has an existing task with title "Task1", description "test", start date "01/01/2020" and end date "05/01/2020"
	And the employee has an existing task with title "Task2", description "test", start date "06/01/2020" and end date "10/01/2020"
	When a list of available employees is requested from date "01/01/2020" to date "10/01/2020"
	Then the application returns a list containing employee with id "test"

Scenario: Request available employees from an employee with overlapping tasks
	Given an employee with id "test" and maxTasks 2 exists in the application
	And the employee has an existing task with title "Task1", description "test", start date "01/01/2020" and end date "06/01/2020"
	And the employee has an existing task with title "Task2", description "test", start date "05/01/2020" and end date "10/01/2020"
	When a list of available employees is requested from date "01/01/2020" to date "10/01/2020"
	Then the application returns a list that does not contain employee with id "test"

Scenario: Request available employees from an employee with tasks overlapping outside the requested interval
	Given an employee with id "test" and maxTasks 2 exists in the application
	And the employee has an existing task with title "Task1", description "test", start date "01/01/2020" and end date "06/01/2020"
	And the employee has an existing task with title "Task2", description "test", start date "05/01/2020" and end date "10/01/2020"
	And the employee has an existing task with title "Task2", description "test", start date "15/01/2020" and end date "30/01/2020"
	When a list of available employees is requested from date "10/01/2020" to date "20/01/2020"
	Then the application returns a list containing employee with id "test"

	Scenario: Request a list of available employees between invalid dates
	Given an employee with id "test1" exists in the application 
	When a list of available employees is requested from date "31/12/2000" to date "30/12/2000" 
	Then the error message "Start date cannot be greater than end date" is given