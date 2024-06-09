package api_tests;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utils.BrowserUtils;
import utils.DataReader;

 public class Items_management_api {

	
	String token;
	String baseurl = DataReader.getProperty("url");
	BrowserUtils utils = new BrowserUtils();
	int item_id;
	
	Response response;
	 
	@Test
	public void login_test() {
		
		String payload = "{\n"
				+ "\n"
				+ "    \"username\": \""+DataReader.getProperty("username")+"\",\n"
				+ "    \"password\": \""+DataReader.getProperty("password")+"\",\n"
				+ "    \"device_name\": \"mobile_app\"\n"
				+ "\n"
				+ "}";
		 
		response = given().contentType("application/json").body(payload)
				.when().post(baseurl + "/api/v1/auth/login");
				
		response.prettyPrint();
		
		//System.out.println(response);
		
		token = response.jsonPath().get("token");
		System.out.println(token);		
	}
	

	
	@Test (dependsOnMethods = {"login_test"})
	public void list_all_items() {
				
		response = given().accept("application/json").auth().oauth2("Bearer " + token)
				.when().get(baseurl + "/api/v1/items");
		
		
		// response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
		
	}
	
	
	@Test (dependsOnMethods= {"login_test"})
	public void create_item() {
		
		Map<String, Object> payload = new HashMap<>();
		
		payload.put("name", "Backpack" + utils.randomNumber());
		payload.put("price", 3800);
		payload.put("unit_id", 11);
		payload.put("description", "nice backpack");
		
		response = 
				given().auth().oauth2("Bearer " + token).body(payload).contentType("application/json")
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
	
}
