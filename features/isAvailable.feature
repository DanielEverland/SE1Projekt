Feature: Check if an employee is available in a given date interval
  Description: returns the availability of an employee. Used when determining if an employee can take on another task
  Actor: Employee

Scenario: (White Box A)
Request a list of available employees between invalid dates
  Given an employee with id "test1" exists in the application
  When the employee's availability is checked from date "31/12/2000" to date "30/12/2000"
  Then the error message "Start date cannot be greater than end date" is given

Scenario: (White Box B)
Request a list of available employees from an employee with maximum workload of 0
  Given an employee with id "test" and maxTasks 0 exists in the application
  When the employee's availability is checked from date "10/01/2020" to date "20/01/2020"
  Then the employee is not available

Scenario: (White Box C)
Successfully request a list of available employees from an employee with no activities
  Given an employee with id "test" exists in the application
  When the employee's availability is checked from date "31/12/1998" to date "31/12/2000"
  Then the employee is available

Scenario: (White Box D)
Request a list of available employees from an employee assigned activities outside the requested date interval
  Given an employee with id "test" exists in the application
  And vacation from "01/01/1999" to "01/02/1999" is assigned to the employee
  When the employee's availability is checked from date "31/12/1999" to date "31/12/2000"
  Then the employee is available

Scenario: (White Box E)
Request a list of available employees from an employee assigned a blocking activity
  Given an employee with id "test" exists in the application
  And vacation from "01/01/1999" to "01/02/1999" is assigned to the employee
  When the employee's availability is checked from date "31/12/1998" to date "31/12/2000"
  Then the employee is not available

Scenario: (White Box F)
Request a list of available employees from an employee assigned a non-blocking, non-task activity
  Given an employee with id "test" exists in the application
  And course with description "description" from "01/01/1999" to "01/02/1999" is assigned to the employee
  When the employee's availability is checked from date "31/12/1998" to date "31/12/2000"
  Then the employee is available

Scenario: (White Box G)
Request a list of available employees from an employee with 1 task equal to their maximum workload
  Given an employee with id "test" and maxTasks 1 exists in the application
  And the employee is signed in
  And A task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" exists
  And the task is assigned to the employee
  When the employee's availability is checked from date "31/12/1998" to date "31/12/2000"
  Then the employee is not available

Scenario: (White Box H)
Request a list of available employees from an employee with a single-day task
  Given an employee with id "test" exists in the application
  And the employee is signed in
  And A task with title "Task title", description "Task description", start date "01/01/2020" and end date "01/01/2020" exists
  And the task is assigned to the employee
  When the employee's availability is checked from date "31/12/2019" to date "31/12/2020"
  Then the employee is available

Scenario: (White Box I)
Request a list of available employees from an employee with a task beginning but not ending in the requested date interval
  Given an employee with id "test" exists in the application
  And the employee is signed in
  And A task with title "Task title", description "Task description", start date "01/01/2020" and end date "01/12/2020" exists
  And the task is assigned to the employee
  When the employee's availability is checked from date "31/12/2019" to date "01/02/2020"
  Then the employee is available