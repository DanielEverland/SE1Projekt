Feature: See assigned tasks
Description: The employee wishes to see all tasks assigned to the employee
Actor: Employee

Scenario: Employee wants to see ass tasked assigned to the employee
Given an employee with id "test" exists in the application
And the employee has an existing task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
And the employee has an existing task with title "test title", description "test description", start date "1/1/1998" and end date "1/1/2000" 
And the employee is signed in
When the employee enters the ID "test"
Then all tasks that are assigend to the ID "test" is presented


#Scenario: Invalid input
#Given an employee with ID 123 exists
#And the employee is signed in
#When the employee enters the ID 123
#Then the error message "Invalid identification code" is given
#
#Scenario: No assigned tasks for input
#Given an employee with ID "abcd" exists
#And the employee is signed in
#When the employee enters the ID "abcd"
#And the employee with ID "abcd" does not have any assigned tasks
#Then the error message "No assigned tasks for this identification code" is given
