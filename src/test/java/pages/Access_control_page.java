package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;

public class Access_control_page {

	BrowserUtils actionutils = new BrowserUtils();
	
		
	public Access_control_page () {
		
		PageFactory.initElements(Driver.getDriver(), this);
		
	}
	
	@FindBy (xpath="//input[@name='email']")
	public WebElement login_username;
	
	@FindBy (xpath="//input[@name='password']")
	public WebElement login_password;
	
	@FindBy (xpath="//button[text()='Login']")
	public WebElement login_Btn;
	
	@FindBy (linkText="Forgot Password?")
	public WebElement login_forgetPassword_link;
	
	@FindBy (xpath="//p[text()='Success!']")
	public WebElement login_success_message;
	
	@FindBy (xpath="//p[text()='Logged in successfully!']")
	public WebElement login_successful_message;

	
	public void login (String user_type) {
		
		Driver.getDriver().get(DataReader.getProperty("url"));
		
		Assert.assertTrue(login_username.isDisplayed());
		Assert.assertTrue(login_password.isDisplayed());
		Assert.assertTrue(login_Btn.isDisplayed());
		Assert.assertTrue(login_forgetPassword_link.isDisplayed());
		
		switch(user_type) {
		
		case "level1": 
			actionutils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level1_username"));
			actionutils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level1_password"));
			actionutils.clickWithActionsClass(login_Btn);
			
			actionutils.waitUntilUrlChanged("dashboard");
			String current_url = Driver.getDriver().getCurrentUrl();
			Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_url);
			Assert.assertTrue(current_url.contains("dashboard"));
			break;
				
		case "level2": 
			actionutils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level2_username"));
			actionutils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level2_password"));
			actionutils.clickWithActionsClass(login_Btn);
			
			actionutils.waitUntilUrlChanged("dashboard");
			String current_url2 = Driver.getDriver().getCurrentUrl();
			Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_url2);
			Assert.assertTrue(current_url2.contains("dashboard"));
			break;
		
		case "level3": 
			actionutils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level3_username"));
			actionutils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level3_password"));
			actionutils.clickWithActionsClass(login_Btn);
			
			actionutils.waitUntilUrlChanged("dashboard");
			String current_url3 = Driver.getDriver().getCurrentUrl();
			Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_url3);
			Assert.assertTrue(current_url3.contains("dashboard"));
			break;
		
		default : 
			actionutils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level3_username"));
			actionutils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level3_password"));
			actionutils.clickWithActionsClass(login_Btn);
			
			actionutils.waitUntilUrlChanged("dashboard");
			String current_urldefault = Driver.getDriver().getCurrentUrl();
			Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", current_urldefault);
			Assert.assertTrue(current_urldefault.contains("dashboard"));
			break;

			
			
		}
		
	
	
	}
	
	
	
}
