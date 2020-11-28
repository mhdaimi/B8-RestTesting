package stepDefinition;

import org.json.JSONObject;
import org.junit.Assert;

import com.google.gson.JsonObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class PUTSteps {
	
	Response resp;
	RequestSpecification request;
	static String url = "http://localhost:8080/app/videogames/";
	static int gameID;
	Response getResponse;
	Response putResponse;
	Response deleteResponse;
	
	@Given("User gets game with id {int}")
	public void user_gets_game_with_id(Integer gameID) {
		this.gameID = gameID;
		
		getResponse = RestAssured.given()
						.header("Accept", "application/json")
						.get(url + gameID);
		Assert.assertEquals(500, getResponse.getStatusCode());
	    
	}

	@When("User modifies the {string} to {string}")
	public void user_modifies_the_to(String field, String value) {
	    
		JSONObject requestBody = new JSONObject(getResponse.getBody().asString());
		System.out.println(requestBody);
		requestBody.put(field, value);
		System.out.println(requestBody);
			
		putResponse = RestAssured.given().contentType("application/json")
						.accept("application/json")
						.body(requestBody.toString())
						.put(url + this.gameID);		
	}

	@Then("Put request is successful with status {int}")
	public void put_request_is_successful_with_status(int status) {
	    Assert.assertEquals(status, putResponse.getStatusCode());
	}
	
	@Then("the {string} is changed to {string}")
	public void put_request_is_successful_with_status(String field, String value) {
		getResponse = RestAssured.given()
				.header("Accept", "application/json")
				.get(url + this.gameID);
			Assert.assertEquals(value, getResponse.jsonPath().getString(field));
	}
	
	@When("User sends a delete request")
	public void user_sends_a_delete_request() {
	    deleteResponse = RestAssured.given().accept("application/json")
	    				.delete(url + this.gameID);
	}
	
	@Then("Game is deleted successfully with status {int}")
	public void game_is_deleted_successfully_with_status(int status) {
		Assert.assertEquals(status, deleteResponse.getStatusCode());
	}

}
