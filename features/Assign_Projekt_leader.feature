Feature: Assign new project leader 
	Description: New project leader is assigned to a project
Actor: Employee

Scenario: An employee is assigned as project leader to project 
	Given The project with title "default_test_project" exists 
	And an employee with id "test" exists in the application 
	When An employee enters the ID of employee "test" who shall be the project manager of the project with title "default_test_project" 
	Then The employee with ID "test" is now the project manager of the project with title "default_test_project" 
	
	#Scenario: Project is already assigned a manager
	#Given an employee with id "test" exists in the application
	#And The project with ID 0 and title "default_test_project" exists
	#And The project with ID 0 already have a project manager
	#When an employee with id "default_test_employee" tries to overwrite the project manager with id "default_test_employee2"
	#Then The system notifies the employee there is already a project manager assigned
	#Then The employee with id "default_test_employee" chooses to overwrite the manager by typing "y"
	#Then the employee whose id was entered is now the project manager
	
	#Scenario: Project is already assigned a manager
	#Given an employee with id "test" exists in the application
	#And The project with ID 0 and title "default_test_project" exists
	#And The project with ID 0 already have a project manager
	#When an employee with id "default_test_employee" tries to overwrite the project manager with id "default_test_employee2"
	#Then The system notifies the employee there is already a project manager assigned
	#Then The employee with id "default_test_employee" chooses not to overwrite the manager by typing "n"
	#Then the employee whose id was entered is not set as project manager
	
	#Scenario: Invalid input
	#Given an employee with id "test" exists in the application
	#And The project with ID 0 and title "default_test_project" exists
	#When The employee "default_test_employee" enters an invalid ID "invalid_ID" who shall be the project manager
	#Then The system notifies the employee that the input was invalid
	#When the employee enters a valid employee name "default_test_employee"
	#Then the employee whose id was entered is now the project manager
