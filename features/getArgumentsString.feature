Feature: Returns a string of command arguments
  Description: returns a string representing arguments for a given command. Used throughout the UI
  Actor: Employee

Scenario: (White Box A)
  Given no command exists
  When the string representation of the commands arguments is requested
  Then an exception is thrown
 
Scenario: (White Box B null)
  Given a command exists with null arguments
  When the string representation of the commands arguments is requested
  Then the argument string is ""
  
Scenario: (White Box B Empty)
  Given a command exists with empty arguments
  When the string representation of the commands arguments is requested
  Then the argument string is ""
 
Scenario: (White Box C)
  Given a command exists with the arguments
  | firstArg | 
  When the string representation of the commands arguments is requested
  Then the argument string is "[firstArg]"
 
Scenario: (White Box D)
  Given a command exists with the arguments
  | firstArg | secondArg |
  When the string representation of the commands arguments is requested
  Then the argument string is "[firstArg] [secondArg]"