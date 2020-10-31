Feature: GET Calls to the application

Scenario: Get all video games
	Given User has access to API
	When User makes GET call to API
	Then All resources are displayed