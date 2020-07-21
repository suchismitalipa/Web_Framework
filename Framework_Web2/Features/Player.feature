Feature: Player 

@sanity
Scenario: Validate Player Functionality
	Given User Launch Chrome browser 
	When User Select a Video
	And User Start a Series 
	And User Click a Video
	Then User Rewind a Video
	When User Forward a Video
