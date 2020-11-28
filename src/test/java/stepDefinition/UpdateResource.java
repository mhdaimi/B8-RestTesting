package stepDefinition;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UpdateResource {

	public static void main(String[] args) {
		
		String url = "http://localhost:8080/app/videogames";
		
		JSONObject requestBody = new JSONObject();
		System.out.println(requestBody);
		requestBody.put("id", 10);
		requestBody.put("name", "Grand Theft Auto III");
		requestBody.put("releaseDate", "2010-12-13");
		requestBody.put("reviewScore", 95);
		requestBody.put("category", "Driving");
		requestBody.put("rating", "Mature");
		
		
		Response resp1 = RestAssured.given()
				.contentType("application/json")
				.accept("application/json")
				.body(requestBody.toString())
				.put(url+"/10");
		
		System.out.println(resp1.getStatusCode());
		System.out.println(resp1.getBody().asString());
		System.out.println(resp1.jsonPath().getString("releaseDate"));

	}

}
