package api_tests;


import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.cucumber.java.BeforeAll;



public class TestNGDemo {

	
	
	@BeforeMethod
	public void setup() {
	
		System.out.println("Before method");
		
	}
	
	
	
	@AfterMethod
	public void wrapup() {
		
		System.out.println("After method");
		
		
	}
	
	
	
	@Test (priority = 1)
	
	public void demoTest() {
		
		System.out.println("Test1");
		
	}
	
	
	
	@Test (priority = 2)

	public void anotherTest() {

		System.out.println("Test2");

		
	}
	
	

	
	
}
