package stepdefinitions;

import static io.restassured.RestAssured.given;


import java.io.IOException;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.DataBuild;
import resources.Utils;

public class StepDefinition extends Utils {	
	RequestSpecification request;
	ResponseSpecification res;
	Response response ,finalres;
	String stringRes;
	static String place_id;
	DataBuild data = new DataBuild();
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		request = given().spec(requestSpec()).body(data.addPlacePayload(name,address,language));
		
	}
	@When("user call the {string} with http {string} method")
	public void user_call_the_add_place_api_with_http_post_method(String resource ,String method) {
		
		APIResource apires=APIResource.valueOf(resource);
		if(method.equalsIgnoreCase("post"))
			response  =request.when().post(apires.getResource());
		else if(method.equalsIgnoreCase("get"))
			response  =request.when().get(apires.getResource());
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(int statuscode) {
	    // Write code here that turns the phrase above into concrete actions
//		res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType("application/json").build();
		finalres= response.then().spec(responseSpec()).extract().response();
		assertEquals(finalres.getStatusCode(),statuscode);
//		stringRes =  response.asString();
//		System.out.println(stringRes);
	}
	@Then("the {string} in response body is {string}")
	public void the_status_in_response_body_is_ok(String key,String string2) {
		
		assertEquals(getJsonValue(finalres,key),string2);
	}
	@Then("verify the place_id creted maps to {string} using {string}")
	public void verify_the_place_id_creted_maps_to_using(String name, String resource) throws IOException {
		place_id = getJsonValue(finalres,"place_id");
		request=given().spec(requestSpec()).queryParam("place_id", place_id);
//		APIResource apires=APIResource.valueOf(resource);
//		Response getapi=given().spec(requestSpec()).queryParam("place_id", place_id)
//		.when().get(apires.getResource()).then().extract().response();
		user_call_the_add_place_api_with_http_post_method(resource,"GET");
		finalres= response.then().spec(responseSpec()).extract().response();
		assertEquals(getJsonValue(finalres,"name"),name);
	    
	}
	
	@Given("DeletePlace Payload with {string}")
	public void delete_place_payload_with(String place) throws IOException {
		request =  given().spec(requestSpec()).body(data.deletePlacePayload(place_id));
	}


}
