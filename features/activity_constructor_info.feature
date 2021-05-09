Feature: Create and use ActivityConstructorInfo and TaskConstructorInfo class
  Description: The constructor info classes help initialize activities and tasks
  Actor: System

Scenario: Default ActivityConstructorInfo constructor
  When an ActivityConstructorInfo with no arguments is created
  Then the ActivityConstructorInfo has title "N/A", start date "", end date "", and not isBlocking

Scenario: ActivityConstructorInfo constructor with values
  When an ActivityConstructorInfo with title "Title", start date "01/01/2000", end date "20/01/2000" and isBlocking is created
  Then the ActivityConstructorInfo has title "Title", start date "01/01/2000", end date "20/01/2000", and isBlocking

Scenario: ActivityConstructorInfo with correct values is valid
  When an ActivityConstructorInfo with title "Title", start date "01/01/2000", end date "20/01/2000" and isBlocking is created
  Then the ActivityConstructorInfo is valid

Scenario: ActivityConstructorInfo with short title is invalid
  When an ActivityConstructorInfo with title "a", start date "01/01/2000", end date "20/01/2000" and isBlocking is created
  Then the ActivityConstructorInfo is invalid

Scenario: ActivityConstructorInfo with flipped dates is invalid
  When an ActivityConstructorInfo with title "Title", start date "20/01/2000", end date "01/01/2000" and isBlocking is created
  Then the ActivityConstructorInfo is invalid

Scenario: Default TaskConstructorInfo constructor
  When a TaskConstructorInfo with no arguments is created
  Then the TaskConstructorInfo has title "N/A", description "N/A", start date "", end date "", and not isBlocking

Scenario: TaskConstructorInfo constructor with values
  When a TaskConstructorInfo with title "Title", description "Description", start date "01/01/2000", and end date "20/01/2000"
  Then the TaskConstructorInfo has title "Title", description "Description", start date "01/01/2000", end date "20/01/2000", and not isBlocking