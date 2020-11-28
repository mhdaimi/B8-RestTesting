Feature: PUT methods
	@put
	Scenario: Update the name of game
		Given User gets game with id 7
		When User modifies the "category" to "Battleground"
		Then Put request is successful with status 200
		And the "category" is changed to "Battleground"
		
	@delete	
	Scenario: Delete a game
		Given User gets game with id 7
		When User sends a delete request
		Then Game is deleted successfully with status 200