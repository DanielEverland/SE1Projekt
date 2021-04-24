Feature: Create Task
Description: Project leaders can create new tasks
Actor: Project Leader

Scenario: A project leader successfully creates a task
Given an employee with id "test" exists in the application
And the employee is signed in
And the employee is a project leader
When The project leader creates a task with title "Test Title", description "Test Description", start date 1 and end date 2
Then A task exists with title "Test Title", description "Test Description", start date 1 and end date 2