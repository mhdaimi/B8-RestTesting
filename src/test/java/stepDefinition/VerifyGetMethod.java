package stepDefinition;


import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;


public class VerifyGetMethod {
	
	Response response;
	String url = "http://localhost:8080/app/videogames";
	RequestSpecification request;
	
	@Given("there is an api")
	public void there_is_an_api() {
	    request = RestAssured.given().header("Accept", "application/json");
	}

	@When("user sends a get request with gameid {int}")
	public void user_sends_a_get_request_for_gameid(int gameID) {
	    response = request.get(url + "/" + gameID);
	}
	
	@When("user sends a get request")
	public void user_sends_a_get_request() {
	    response = request.get(url);
	}

	@Then("request is successful with status {int}")
	public void request_is_successful_with_status(int status) {
	    Assert.assertEquals(status, response.getStatusCode());
	}
	
	@Then("header {string} has value {string}")
	public void request_is_successful_with_header(String headerName, String headerValue) {
		Assert.assertEquals(headerValue, response.getHeader(headerName));
	}
	
	@Then("response header contains")
	public void response_header_contains(Map<String, String> headers) {
		System.out.println(headers);
		for (String key : headers.keySet()) {
			Assert.assertEquals(headers.get(key), response.getHeader(key));
		}
	}
	
	@Then("number of games is {int}")
	public void number_of_games_is(int totalGames) {
		JsonPath jsonResp = response.jsonPath();
		List<Object> list = jsonResp.get("id");
		Assert.assertEquals(totalGames, list.size());
	}
	
	@Then("response body contains")
	public void response_body_contains(Map<String, String> body) {
		System.out.println(body);
		JsonPath jsonResp = response.jsonPath();
		for (String key : body.keySet()) {
			Assert.assertEquals(body.get(key), jsonResp.get(key).toString());
		}
	}
	
	@Then("schema validation is successful")
	public void schema_validation_is_successful() {
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
	}
	

}
