Feature: Create and use employee class
Description: The employee represents an employee of Softwarehuset A/S
Actor: Employee

Scenario: Create an employee with default maximum workload
  When an employee is created with id "test"
  Then the employee has id "test" and maxTasks 10

Scenario: Create an employee with custom maximum workload
  When an employee is created with id "test" and max tasks 20
  Then the employee has id "test" and maxTasks 20

Scenario: Assign and get activities assigned to employee
  Given an employee with id "test" exists in the application
  And the employee is signed in
  And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists
  And the task is assigned to the employee
  And vacation from "01/01/2020" to "10/10/2020" is assigned to the employee
  When a list of the employee's assigned activities is requested
  Then the list has 2 elements
  And the list contains a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000"
  And the list contains a vacation from "01/01/2020" to "10/10/2020"

Scenario: Assign duplicate activity to employee
  Given an employee with id "test" exists in the application
  And vacation from "01/01/2020" to "10/10/2020" is assigned to the employee
  When the same vacation is attempted assigned to the employee
  Then the error message "The activity is already assigned to employee" is given