package api_tests;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.BrowserUtils;
import utils.DataReader;

public class Items_Mngt_Improved {

	String token;
	String baseurl = DataReader.getProperty("url");
	BrowserUtils utils = new BrowserUtils();
	int item_id;
	
	Response response;
	
	RequestSpecification specs;
	
	
	@Test (groups= {"regression", "smoke_test"})
	public void login_test() {
		
		
		
		String payload = "{\n"
				+ "\n"
				+ "    \"username\": \""+DataReader.getProperty("username")+"\",\n"
				+ "    \"password\": \""+DataReader.getProperty("password")+"\",\n"
				+ "    \"device_name\": \"mobile_app\"\n"
				+ "\n"
				+ "}";
		 
		specs = RestAssured.given();
		specs.baseUri(baseurl);
		specs.contentType("application/json").body(payload);

		response = specs.post(baseurl + "/api/v1/auth/login");
				
		response.prettyPrint();
		token = response.jsonPath().get("token");
		System.out.println(token);		
		
		RestAssured.baseURI = DataReader.getProperty("app_url");
		
	}
	

	
	@Test (dependsOnMethods = {"login_test"}, groups = {"smoke_test"})
	public void list_all_items() {
				
		specs.baseUri(baseurl).accept("application/json").auth().oauth2("Bearer " + token);
		
		response = specs.get(baseurl + "/api/v1/items");
		
		// response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
		
	}
	
	
	@Test (dependsOnMethods= {"login_test"}, groups = {"smoke_test"})
	public void create_item() {
		
		Map<String, Object> payload = new HashMap<>();
		
		payload.put("name", "Backpack" + utils.randomNumber());
		payload.put("price", 3800);
		payload.put("unit_id", 11);
		payload.put("description", "nice backpack");
		
		Map<String, Object> headerObj = new HashMap<>();
		headerObj.put("Content_Type", "application/json");
		headerObj.put("Authorization", "Bearer " + token);
		
		response = given()
				.headers(headerObj)
				.body(payload)
				.when().post(baseurl + "/api/v1/items");

		item_id = response.jsonPath().get("data.id");
		
		String item_name = response.jsonPath().get("data[0].name");
		
        System.out.println(item_name);		
		
	}
	
	
	
	@Test  (dependsOnMethods= {"create_item"})
	public void get_item() {
		
		response = given().auth().oauth2("Bearer " + token).accept("application/json")
				.when().get(baseurl + "/api/v1/items/" + item_id);
		
		response.then().statusCode(200).contentType("application/json");
		
		int id = response.jsonPath().get("data.id");
		Assert.assertEquals(id, item_id);
		
	}

	
	@Test (dependsOnMethods= {"create_item"})
	
	public void update_item() {
		
		File payload = new File("./src/test/resources/testfiles/update_item_data.json");
		
		response = 
				given().auth().oauth2("Bearer " + token).contentType("application/json").body(payload)
				.when().put(baseurl + "/api/v1/items/" + item_id);
		
		response.prettyPrint();
		response.then().statusCode(200).contentType("application/json");
		
	}

	@Test (dependsOnMethods= {"get_item"})
	public void delete_item() {
		
		String payload = "{\n"
				+ "    \"ids\": \""+ item_id + "\"\n"
				+ "}";
		
		response = 
				given().auth().oauth2("Bearer " + token).contentType("application/json").body(payload)
				.when().post(baseurl + "/api/v1/items/delete");

		response.prettyPrint();
		response.then().statusCode(200).contentType("application/json");
		boolean deleteSuccess = response.jsonPath().get("success");		
	
		Assert.assertTrue(deleteSuccess);
	}
	
	// demo test for query parameters
	
	@Test
	public void petStore_findByStatus() {
		
		response = given()
		.accept("application/json")
		.queryParam("status", "available")
		.when().get("https://petstore.swagger.io/v2/pet/findByStatus");
		
		response.prettyPrint();
		response.then().statusCode(200);
		
	}
	
	@Test 
	public void petStore_findByStatus_chainValidation() {
		
		given()
		.accept("application/json")
		.queryParam("status", "available")
		.when()
		.get("https://petstore.swagger.io/v2/pet/findByStatus")
		.then()
		.assertThat().statusCode(200)
		.and().assertThat().contentType("application/json")
		.and().assertThat().body("[0].name", Matchers.equalTo("doggie"));
	}
	
	// invalid API test case demo

	@Test 
	public void petStore_invalid_endpoint() {
		
		response = given()
		.accept("application/json")
		.when()
		.get("https://petstore.swagger.io/v2/pet/" + 7);
		
		response.prettyPrint();
		response
		.then()
		.assertThat().statusCode(404)
		.and().assertThat().contentType("application/json")
		.and().assertThat().body("message", Matchers.equalTo("Pet not found"));
		
	
	}
	
	
	
	
	
}
