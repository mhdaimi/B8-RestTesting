package stepDefinition;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class POSTRequestSteps {
	
	Response resp;
	RequestSpecification request;
	static String url = "http://localhost:8080/app/videogames";
	static int gameId;
	
	@Given("User has access to an api")
	public void user_has_access_to_an_api() {
	    request = RestAssured.given().contentType("application/json");
	}

	@When("User sends a post request with values")
	public void user_sends_a_post_request_with_values(Map<String, String> values) {
		JSONObject requestBody = new JSONObject();
		
		for (String key : values.keySet()) {
			System.out.println(key + ", " + values.get(key));
			requestBody.put(key, values.get(key));
		}
		
		gameId = getNumberOfGames()+1;
		requestBody.put("id", gameId);
		resp = request.body(requestBody.toString()).post(url);
		
	}

	@Then("the request is successful with status {int}")
	public void the_request_is_successful_with_status(int status) {
	    Assert.assertEquals(status, resp.getStatusCode());
	}
	
	@When("User retrieves the newly added game")
	public void user_retrieves_newly_added_game() {
		resp = RestAssured.given().header("Accept", "application/json").get(url+"/"+gameId);	
		System.out.println(resp.getStatusCode());
	}
	
	
	public static int getNumberOfGames() {
		Response resp = RestAssured.given().header("Accept", "application/json").get(url);
		JsonPath jsonResp = resp.jsonPath();
		List<Object> list = jsonResp.get("id");
		return list.size();
		
	}

}
