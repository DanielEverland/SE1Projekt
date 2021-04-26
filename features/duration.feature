Feature: Duration
Description: Employee enters duration of hours worked
Actor: Employee

Scenario:
Given Employee enters 1.5 hours worked
Then the hours worked can be logged

Scenario:
Given Employee enters 0.0 hours worked
Then an assertion error is triggered

Scenario:
Given Employee enters -1.0 hours worked
Then an assertion error is triggered

Scenario:
Given Employee enters 0.0001 hours worked
Then an assertion error is triggered

Scenario:
Given Employee enters 0.000001 hours worked
Then an assertion error is triggered

#De 3 sidste scenarios giver assertionError. 