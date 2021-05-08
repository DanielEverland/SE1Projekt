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