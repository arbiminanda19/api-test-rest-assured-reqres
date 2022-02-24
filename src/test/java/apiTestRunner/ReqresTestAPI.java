package apiTestRunner;

import payload.ListAPI;
import payload.reqresAPIdata;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReqresTestAPI {

	ListAPI ip = new ListAPI();
	Response response;
	reqresAPIdata reqresAPIdata = new reqresAPIdata();
	public String user_id;
	Random rand = new Random();
		
	@BeforeMethod
    public void setUp() {
        RestAssured.baseURI = ip.getReqresAPI();
    }
	
	@Test(priority = 0)
    public void getListUserPageAPI(){
		response = given()
                .when()
                .get("api/users?page=" + rand.nextInt(10))
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
	}
	
	@Test(priority = 1)
    public void createAPI(){
        response = given()
                .header("Content-Type", "application/json")
                .body(reqresAPIdata.createUsers().toJSONString())
                .when()
                .post("api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();
        user_id = response.getBody().path("id");
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
		System.out.print(RestAssured.baseURI + "api/users/" + user_id);
	}
	
}
