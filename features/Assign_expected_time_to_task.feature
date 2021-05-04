Feature: Assign expected time to task
Description: A project manager assigns an expected amount of time required to finish the task (minimumtime is half an hour)
Actor: Project leader

Scenario: Assign expected time to task
Given The project with title "default_test_project" exists
And an employee with id "emp1" exists in the application
And the employee is signed in
And the employee is a project leader
And the project with title "default_test_project" contains a task with name "default_test_task", description "test_task", start date "01/01/2000" and end date "01/01/2001"
When the project leader with id "emp1" enters the amount of hours 10.5 that he expect that the task will take to complete
Then the task now has 10.5 hours remaining until completion