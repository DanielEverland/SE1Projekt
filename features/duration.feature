Feature: Duration 
	Description: Employee enters duration of hours worked
Actor: Employee

Scenario: Valid time passed is logged 
	Given time passed is 1.5 hours 
	When duration is set to time passed 
	Then duration is equal to 1.5 hours 
	
Scenario: No time passed will trigger assertion 
	Given time passed is 0.0 hours 
	When duration is set to time passed
	Then the error message "Input is less than one minute" is given
	
Scenario: Negative time passed will trigger assertion 
	Given time passed is -1.0 hours 
	When duration is set to time passed
	Then the error message "Input is less than one minute" is given
	
Scenario: Near-zero time passed will trigger assertion 
	Given time passed is 0.0001 hours 
	When duration is set to time passed
	Then the error message "Input is less than one minute" is given
	
Scenario: Adding time properly increments 
	Given time passed is 1.0 hours 
	When duration is set to time passed 
	When adding 1.0 hours and 30 minutes to duration 
	Then duration is equal to 2.5 hours 
	
Scenario: Adding negative minutes will trigger assertions 
	Given time passed is 1.0 hours 
	When duration is set to time passed 
	When adding -30 minutes to duration 
	Then the error message "Input is less than one minute" is given 
	
Scenario: Adding valid minutes increments 
	Given time passed is 1.0 hours 
	When duration is set to time passed 
	When adding 30 minutes to duration 
	Then duration is equal to 1.5 hours 
	
Scenario: Adding less than a 1/60th of a minute gives an error
	Given time passed is 1.0 hours
	When duration is set to time passed
	When adding 0.01 hours to duration
	Then the error message "Input is less than one minute" is given
	And duration is equal to 1.0 hours
	
Scenario: Get minutes passed returns total 
	Given time passed is 1.5 hours 
	When duration is set to time passed 
	Then duration minutes passed is equal to 90 minutes