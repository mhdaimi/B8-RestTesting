Feature: Verify GET method for Video Game API
Background: 
				Given there is an api
	
	Scenario: GET call to fetch all games and validate status
		When user sends a get request
		Then request is successful with status 200
		
	Scenario: GET call to fetch all games and validate single header
		When user sends a get request
		Then request is successful with status 200
		And header "content-type" has value "application/json"
		
	Scenario: GET call to fetch all games and validate headers
		When user sends a get request
		Then request is successful with status 200
		And response header contains
					| content-type | application/json |
					| content-length | 1183 |
					
	Scenario: GET call to fetch all games and validate count
		When user sends a get request
		Then request is successful with status 200
		And number of games is 10
		
	Scenario: GET call to fetch specific game details by id
	When user sends a get request with gameid 4
	Then request is successful with status 200
	And response header contains
					| content-type | application/json |
					| content-length | 119 |
	And response body contains
				| id | 4 |
				| name | Super Mario 64 |
				| releaseDate | 1996-10-20 |
				| reviewScore | 90 |
				| category | Platform |
				| rating | Universal |
				
  Scenario: Get specific game and validate schema
  	When user sends a get request with gameid 4
		Then request is successful with status 200
		And schema validation is successful
					