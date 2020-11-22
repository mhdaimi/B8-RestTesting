package stepDefinition;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestPOSTRequest {

	public static void main(String[] args) {

		String url = "http://localhost:8080/app/videogames";
		
		JSONObject requestBody = new JSONObject();
		requestBody.put("id", 14);
		requestBody.put("name", "FAUG-14");
		requestBody.put("releaseDate", "2020-11-22");
		requestBody.put("reviewScore", 9);
		requestBody.put("category", "Battleground");
		requestBody.put("rating", "Worlwide");
				
		Response resp = RestAssured.given()
						.contentType("application/json")
						//.body(  "{  \r\n\"id\": 13,   \r\n\"name\": \"PUBG-13\",   \r\n\"releaseDate\": \"2020-11-22T03:27:50.569Z\",   \r\n\"reviewScore\": 8,   \r\n\"category\": \"Fights\",  \r\n\"rating\": \"Super\"}")
						.body(requestBody.toString())
						.post(url);
		
		System.out.println(resp.getStatusCode());
				
		
	}

}
