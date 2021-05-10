Feature: Date 
	Description: Date wraps Java's built-in Date class while extending its functionality to help streamline how we use it in our project

Scenario: Different hashcode for different dates 
	Given the current date is "13/04/1997" 
	When another date is "14/04/1997" 
	Then their hashcodes are different 
	
Scenario: Can calculate hashcode from unspecified date 
	Given the current date is unspecified 
	When the hashcode is calculated 
	Then no assertion error is triggered

Scenario: Can get a string from unspecified date
	Given the current date is unspecified
	Then the date's string value is "N/A"

Scenario: Can get a string from specified date
	Given the current date is "01/01/2000"
	Then the date's string value is "01/01/2000"
	
Scenario: Invalid date will print error message 
	Given the current date is "NOT A VALID DATE" 
	Then the error message "Couldn't parse date NOT A VALID DATE" is given 
	
Scenario: Different dates are not equal 
	Given the current date is "13/04/1997" 
	When another date is "14/04/1997" 
	Then their dates are not equal 
	
Scenario: Same dates are equal 
	Given the current date is "13/04/1997" 
	When another date is "13/04/1997"
	Then their dates are equal 
	
Scenario: A null object is not equal 
	Given the current date is "13/04/1997" 
	Then their dates are not equal 
	
Scenario: A different object type is not equal 
	Given the current date is "13/04/1997" 
	Then another object type is not equal 
	
Scenario: An uninitialized date is not equal 
	Given the current date is unspecified 
	When another date is "13/04/1997" 
	Then their dates are not equal 

Scenario: An initialized date is specified 
	Given the current date is "13/04/1997" 
	Then the current date is specified

Scenario: An uninitialized date is unspecified 
	Given the current date is unspecified
	Then the current date is unspecified  
	
Scenario: Two uninitialized dates are equal 
	Given the current date is unspecified 
	And another date is unspecified 
	Then their dates are equal

Scenario: AfterOrEqual success with A > B
	Given the current date is "14/04/1997" 
	When another date is "13/04/1997"
	Then their dates are after or equal

Scenario: AfterOrEqual success with A = B
	Given the current date is "13/04/1997" 
	When another date is "13/04/1997"
	Then their dates are after or equal	

Scenario: AfterOrEqual fails with A < B
	Given the current date is "13/04/1997" 
	When another date is "14/04/1997"
	Then their dates are not after or equal	

Scenario: BeforeOrEqual fails with A > B
	Given the current date is "14/04/1997" 
	When another date is "13/04/1997"
	Then their dates are not before or equal

Scenario: BeforeOrEqual success with A = B
	Given the current date is "13/04/1997" 
	When another date is "13/04/1997"
	Then their dates are before or equal

Scenario: BeforeOrEqual succeeds with A < B
	Given the current date is "13/04/1997" 
	When another date is "14/04/1997"
	Then their dates are before or equal