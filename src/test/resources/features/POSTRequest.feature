Feature: POST requests

	Scenario: Add a new game
		Given User has access to an api
		When User sends a post request with values
					| name | Free Fire |
					| releaseDate | 2018-11-22 |
					| reviewScore | 7 |
					| category | Battleground |
					| rating | Mature |
		Then the request is successful with status 200
		And response contains message "Record Added Successfully"
		
		@createAndVerify
		Scenario: Add new game and verify id
		Given User has access to an api
		When User sends a post request with values
					| name | PUB-G |
					| releaseDate | 2018-11-22 |
					| reviewScore | 9 |
					| category | Battleground |
					| rating | Mature |
		Then the request is successful with status 200
		When User retrieves the newly added game
		Then the request is successful with status 200

		
