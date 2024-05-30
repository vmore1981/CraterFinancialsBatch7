package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class Dashboard_page {

	
	
	public Dashboard_page () {
		
		PageFactory.initElements(Driver.getDriver(), this);
		
	}
	
	@FindBy (xpath="//span[text()='Amount Due']")
	public WebElement dashboard_amountDueText;
	
	@FindBy (xpath = "//a[text()=' Items']")
	public WebElement Items_tab;
	



	
	
}
