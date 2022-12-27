package runner;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import payload.baseUrl;
import payload.data;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class createUser {

	baseUrl ip = new baseUrl();
	Response response;
	data data = new data();
	public String name;
	public Integer user_id;
	Random rand = new Random();
		
	@BeforeMethod
    public void setUp() {
			RestAssured.baseURI = ip.getReqresAPI();
    }
	
	@Test(priority = 1)
	public void createUser() {
		String payload = data.createUsers().toJSONString();
		response = given()
				.header("Content-Type", "application/json")
				.body(payload)
				.when()
				.post("api/users")
				.then()
				.log().body()
				.statusCode(201)
				.extract().response();
		name = response.getBody().path("name");
		Assert.assertTrue(payload.contains(name));
	}

	@Test(priority = 2)
	public void createUserByList() {
		String payload = data.createUsersByList().toJSONString();
		response = given()
				.header("Content-Type", "application/json")
				.body(payload)
				.when()
				.post("api/users")
				.then()
				.log().body()
				.statusCode(201)
				.extract().response();
		JsonPath responseParsed = response.jsonPath();
		name = responseParsed.getString("user.name");
		Assert.assertTrue(payload.contains(name));
	}
	
}
