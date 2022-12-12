Feature: Verify Add Place APIs

@AddPlaceAPI
Scenario Outline: Verify that place is succesfully added with API
	Given Add place payload with "<name>" "<Address>" "<Language>"
	When user call the "addPlaceAPI" with http "post" method
	Then the API call is success with status code 200
	And the "status" in response body is "OK"
	And the "scope" in response body is "APP"
	And verify the place_id creted maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name	|Address	|Language	|
	|Akshay	|305/1 krishnapuri	|Hindi	|

@DeletePlace
Scenario: Verify if deleteplace api is working
	Given DeletePlace Payload with "place_id"
	When user call the "deletePlaceAPI" with http "post" method
	Then the API call is success with status code 200
	And the "status" in response body is "OK"
#Examples:
#	|place_id|
#	|50e58685691ef8f878f41bf787665e04|