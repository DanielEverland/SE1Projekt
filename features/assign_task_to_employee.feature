Feature: Assign task to employee 
	Description: The project leader assigns a task to an available employee
Actor: Project leader, employee

Scenario: Project leader successfully assigns task to employee 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And an employee with id "emp2" exists in the application 
	When The employee creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the employee with id "emp2" is assigned to the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	
Scenario: The employee is unavailable 
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And an employee with id "emp2" and maxTasks 0 exists in the application 
	When The employee creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the error message "Employee is unavailable" is given

Scenario: Non-project leader cannot create task
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in
	And an employee with id "emp2" exists in the application 
	When The employee creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the error message "Currently signed in employee is not project leader" is given

Scenario: Cannot assign null tasks
	Given an employee with id "emp1" exists in the application
	And the employee is signed in
	And the employee is a project leader
	And an employee with id "emp2" exists in the application
	When the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2"
	Then the error message "Employee being assigned task or assigned task was null" is given

Scenario: Cannot assign tasks to null employee
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader
	When The employee creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	Then the error message "Employee being assigned task or assigned task was null" is given

Scenario: You can get all employees assigned to a given task
	Given an employee with id "emp1" exists in the application 
	And the employee is signed in 
	And the employee is a project leader 
	And an employee with id "emp2" exists in the application 
	When The employee creates a task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp2" 
	And the employee assigns the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" to employee with id "emp1"
	Then the task with title "Task title", description "Task description", start date "31/12/1998" and end date "31/12/2000" is assigned to the employees with ids
	| emp1 | emp2 |