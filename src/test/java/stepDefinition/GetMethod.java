package stepDefinition;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class GetMethod {
	
	Response res;
	
	
	@Given("User has access to API")
	public void user_has_access_to_API() {
	    System.out.println("Making Get call to API");
	}

	@When("User makes GET call to API")
	public void user_makes_GET_call_to_API() {
		res = RestAssured.given()
				.header("Accept", "application/json")
				.get("http://localhost:8080/app/videogames");
	    
	}

	@Then("All resources are displayed")
	public void all_resources_are_displayed() {
		int statusCode = res.getStatusCode();
		System.out.println("Response Code: " + statusCode);
		
		List<Header> headers = res.getHeaders().asList();
		System.out.println(headers);
		
	    String responseAsString = res.asString();
	    		System.out.println(responseAsString);
	}
	

}
