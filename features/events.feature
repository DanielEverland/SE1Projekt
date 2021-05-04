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

Scenario: Assign sick leave to an employee
Given an employee with id "emp1" exists in the application
When sick leave from "01/05/2020" to "01/05/2020" is assigned to the employee
Then the employee has sick leave from "01/05/2020" to "01/05/2020"

Scenario: Employee with tasks takes sick leave
Given an employee with id "emp1" exists in the application
And the employee has an existing task with title "task", description "description", start date "01/01/2000" and end date "01/01/2001"
When sick leave from "01/10/2000" to "05/10/2000" is assigned to the employee
Then the employee has sick leave from "01/10/2000" to "05/10/2000"

Scenario: Employee on sick leave is unavailable
Given an employee with id "emp1" exists in the application
When sick leave from "01/05/2020" to "01/05/2020" is assigned to the employee
Then the employee is unavailable from "01/05/2020" to "01/05/2020"
And the employee is unavailable from "20/03/2020" to "20/05/2020"

Scenario: Assign course to an employee
Given an employee with id "emp1" exists in the application
When course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020" is assigned to the employee
Then the employee has a course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020"

Scenario: Employee with tasks is assigned a course
Given an employee with id "emp1" exists in the application
And the employee has an existing task with title "task", description "description", start date "01/01/2020" and end date "01/01/2021"
When course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020" is assigned to the employee
Then the employee has a course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020"

Scenario: Employee on course is still available
Given an employee with id "emp1" exists in the application
When course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020" is assigned to the employee
Then the employee is available from "01/01/2020" to "01/05/2020"

  Scenario: Description of course assigned to employee is changed
    Given an employee with id "emp1" exists in the application
    And course with description "Software Engineering 1" from "01/01/2020" to "01/05/2020" is assigned to the employee
    When the course has its description changed to "Software Engineering 2"
    Then the employee has a course with description "Software Engineering 2" from "01/01/2020" to "01/05/2020"