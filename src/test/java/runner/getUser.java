package runner;

import org.testng.Assert;
import payload.baseUrl;
import payload.data;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class getUser {

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
	public void getListUserPageAPI(){
		response = given()
                .when()
                .get("api/users?page=" + rand.nextInt(2))
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
		JsonPath responseParsed = response.jsonPath();
		user_id = responseParsed.getInt("data[0].id");
	}
	
	@Test(priority = 2)
	public void getListByIdAPI() {
		response = given()
                .when()
                .get("api/users/" + user_id)
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
		JsonPath responseParsed = response.jsonPath();
		Assert.assertTrue(user_id == responseParsed.getInt("data.id"));
	}
	
}
