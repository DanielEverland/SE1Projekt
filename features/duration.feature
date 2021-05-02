Feature: Duration
Description: Employee enters duration of hours worked
Actor: Employee

Scenario: Valid time passed is logged
Given time passed is 1.5 hours
When duration is set to time passed
Then duration is equal to 1.5 hours

Scenario:
Given time passed is 0.0 hours
When duration is set to time passed
Then an assertion error is triggered

Scenario:
Given time passed is -1.0 hours
When duration is set to time passed
Then an assertion error is triggered

Scenario:
Given time passed is 0.0001 hours
When duration is set to time passed
Then an assertion error is triggered 