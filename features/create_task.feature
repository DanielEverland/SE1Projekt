Feature: Create Task 
	Description: Project leaders can create new tasks
Actor: Project Leader

Scenario: A project leader successfully creates a task 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then A task exists with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	
Scenario: Cannot create task when no project leader exists
	Given an employee with id "test" exists in the application 
	And the employee is signed in
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	Then the error message "No project lead has been assigned to this project" is given

Scenario: A non signed-in user cannot create a task
	Given an employee with id "test" exists in the application 
	And the employee is signed in
	And the employee is a project leader
	And the employee is signed out
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	Then the error message "Cannot create tasks when not signed in" is given

Scenario: A non project-lead user cannot create a task
	Given an employee with id "test" exists in the application
	And the employee is signed in
	And the employee is a project leader
	Given an employee with id "test2" exists in the application
	And the employee is signed in
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/1998" and end date "31/12/2000"
	Then the error message "Currently signed in employee is not project leader" is given
	
Scenario: Illegal character in title 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The employee creates a task with title "§§§§§§§", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then No task with title "§§§§§§§", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists 
	
Scenario: Too short title entered 
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The employee creates a task with title "g", description "Test Description", start date "31/12/1998" and end date "31/12/2000" 
	Then No task with title "g", description "Test Description", start date "31/12/1998" and end date "31/12/2000" exists 
	
Scenario: Start date after end date fails
	Given an employee with id "test" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	When The employee creates a task with title "Test Title", description "Test Description", start date "31/12/2000" and end date "31/12/1990"
	Then No task with title "Test Title", description "Test Description", start date "31/12/2000" and end date "31/12/1990" exists 