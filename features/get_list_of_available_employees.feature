Feature: Get list of available employees 
	Description: Employee requests a list of available employees in a time period. Typically used by project leaders looking to assign employees to a task.
Actor: Employee

Scenario: (White Box A)
Request a list of available employees between invalid dates
	Given an employee with id "test1" exists in the application
	When a list of available employees is requested from date "31/12/2000" to date "30/12/2000"
	Then the error message "Start date cannot be greater than end date" is given

Scenario: (White Box B)
Request a list of available employees from an employee with maximum workload of 0
	Given an employee with id "test" and maxTasks 0 exists in the application
	When a list of available employees is requested from date "10/01/2020" to date "20/01/2020"
	Then the application returns a list that does not contain employee with id "test"

Scenario: (White Box C)
Successfully request a list of available employees from an employee with no activities
	Given an employee with id "test" exists in the application
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000" 
	Then the application returns a list containing employee with id "test"

Scenario: (White Box D)
Request a list of available employees from an employee assigned activities outside the requested date interval
	Given an employee with id "test" exists in the application
	And vacation from "01/01/1999" to "01/02/1999" is assigned to the employee
	When a list of available employees is requested from date "31/12/1999" to date "31/12/2000"
	Then the application returns a list containing employee with id "test"

Scenario: (White Box E)
Request a list of available employees from an employee assigned a blocking activity
	Given an employee with id "test" exists in the application
	And vacation from "01/01/1999" to "01/02/1999" is assigned to the employee
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000"
	Then the application returns a list that does not contain employee with id "test"

Scenario: (White Box F)
Request a list of available employees from an employee assigned a non-blocking, non-task activity
	Given an employee with id "test" exists in the application
	And course with description "description" from "01/01/1999" to "01/02/1999" is assigned to the employee
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000"
	Then the application returns a list containing employee with id "test"

Scenario: (White Box G)
Request a list of available employees from an employee with 1 task equal to their maximum workload
	Given an employee with id "test" and maxTasks 1 exists in the application
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000"
	Then the application returns a list that does not contain employee with id "test"

Scenario: (White Box H)
Request a list of available employees from an employee with a single-day task
	Given an employee with id "test" exists in the application
	And the employee has an existing task with title "Task title", description "Task description", start date "01/01/2020" and end date "01/01/2020"
	When a list of available employees is requested from date "31/12/2019" to date "31/12/2020"
	Then the application returns a list containing employee with id "test"

Scenario: (White Box I)
Request a list of available employees from an employee with a task beginning but not ending in the requested date interval
	Given an employee with id "test" exists in the application
	And the employee has an existing task with title "Task title", description "Task description", start date "01/01/2020" and end date "01/12/2020"
	When a list of available employees is requested from date "31/12/2019" to date "01/02/2020"
	Then the application returns a list containing employee with id "test"

Scenario:
Successfully request a list of available employees from employees of varying availability 
	Given an employee with id "test1" exists in the application 
	And an employee with id "test2" and maxTasks 1 exists in the application 
	And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"
	And an employee with id "test3" exists in the application
	When a list of available employees is requested from date "31/12/1998" to date "31/12/2000" 
	Then the application returns a list containing employee with id "test1"
	And the application returns a list containing employee with id "test3"
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

