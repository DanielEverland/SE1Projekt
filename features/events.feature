Feature: Assign activities that aren't tasks
  Description: An activity that is not a task (e.g. vacation, sick leave or courses) is assigned to an employee
  Actor: Employee

Scenario: Assign vacation to an employee
Given an employee with id "emp1" exists in the application
When vacation from "31/12/1998" to "31/01/1999" is assigned to the employee
Then the employee has a vacation from "31/12/1998" to "31/01/1999"

Scenario: Employee with tasks goes on vacation
Given an employee with id "emp1" exists in the application
And the employee has an existing task with title "task", description "description", start date "31/12/1998" and end date "31/12/2000"
When vacation from "31/12/1998" to "31/01/1999" is assigned to the employee
Then the employee has a vacation from "31/12/1998" to "31/01/1999"

Scenario: Vacationing employee is unavailable
Given an employee with id "emp1" exists in the application
When vacation from "31/12/1998" to "31/01/1999" is assigned to the employee
Then the employee is unavailable from "31/12/1998" to "31/01/1999"
And the employee is unavailable from "20/01/1999" to "20/02/1999"