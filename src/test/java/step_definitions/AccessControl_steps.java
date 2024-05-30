package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Access_control_page;
import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;



public class AccessControl_steps {

	
	Access_control_page acp = new Access_control_page();
	BrowserUtils actionutils = new BrowserUtils();
	
		
	
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
	
		Driver.getDriver().get(DataReader.getProperty("url"));
		

	}

	@Given("login page components exist")
	public void login_page_components_exist() {

		Assert.assertTrue(acp.login_username.isDisplayed());
		Assert.assertTrue(acp.login_password.isDisplayed());
		Assert.assertTrue(acp.login_Btn.isDisplayed());
		Assert.assertTrue(acp.login_forgetPassword_link.isDisplayed());
		
	}
	
	@When("I enter valid username and valid password")
	public void i_enter_valid_username_and_valid_password() {
		
		actionutils.sendkeysWithActionsClass(acp.login_username, DataReader.getProperty("username"));
		actionutils.sendkeysWithActionsClass(acp.login_password, DataReader.getProperty("password"));

	}
	
	@When("I click login button")
	public void i_click_login_button() {
	
		actionutils.clickWithActionsClass(acp.login_Btn);
		
	}
	
	@Then("I should be on the Dashboard page")
	public void i_should_be_on_the_dashboard_page() {

		actionutils.waitUntilUrlChanged("dashboard");
		String current_url = Driver.getDriver().getCurrentUrl();
		Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_url);
		Assert.assertTrue(current_url.contains("dashboard"));
	
	}
	
	@Then("The success message displays")
	public void the_success_message_displays() {

		Assert.assertTrue(acp.login_success_message.isDisplayed());
	}
	
	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String menu_item) {
		
		String classValues = Driver.getDriver().findElement(By.xpath("//a[text()='"+menu_item+"']")).getAttribute("class");
		Assert.assertTrue(classValues.contains("active"));
		
		
		
		//actionutils.waitUntilUrlChanged( "dashboard" );
		//String current_url = Driver.getDriver().getCurrentUrl();
		//Assert.assertTrue(current_url.contains( "dashboard" ));
	
	}
	
	@Then("{string} message displays")
	public void message_displays(String successMessage) {
		
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//p[text()='"+successMessage+"']")).isDisplayed());
		
	}
 	
}
