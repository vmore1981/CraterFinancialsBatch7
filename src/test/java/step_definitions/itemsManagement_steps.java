package step_definitions;


import org.junit.Assert;
import org.openqa.selenium.By;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Access_control_page;
import pages.Dashboard_page;
import pages.Items_page;
import utils.BrowserUtils;
import utils.Driver;


public class itemsManagement_steps {

	Access_control_page acp = new Access_control_page();
	Dashboard_page dash_page = new Dashboard_page();
	BrowserUtils utils = new BrowserUtils();
	Items_page items = new Items_page();
	String itemname = "";
	String itemprice = "";
	
	
	
	@Given("As a {string} user, I am logged in")
	public void as_a_user_i_am_logged_in(String user_type) {
			acp.login(user_type);
		
			
	}	

	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
			dash_page.Items_tab.click();	
			utils.waitUntilUrlChanged("items");
			Assert.assertTrue(items.items_items_text.isDisplayed());
			
	}

	
	@When("I am click on Add Item button")
	public void i_am_click_on_add_item_button() {
			items.items_add_item_button.click();
	}

	
	@Then("I should be on New Item Page")
	public void i_should_be_on_new_item_page() {
			utils.waitUntilUrlChanged("items/create");
			Assert.assertTrue(items.items_Input_page_newItem_text.isDisplayed());
	}

	
	@When("I provide Item name {string} and price {string} unit {string} and description {string}")
	public void i_provide_item_name_and_price_unit_and_description(String item_name, String item_price, String item_unit, String item_des) {
		itemname = item_name + utils.randomNumber();
		itemprice = item_price;
		
		System.out.println("Random item number is: " + itemname);
		
		items.items_input_page_name_box.sendKeys(itemname);
		items.items_input_page_price_box.sendKeys(item_price);
		
		items.items_input_page_unit_dropdown.click();
		utils.waitForElementToBeVisible(items.items_input_page_unit_pc_option);
		items.items_input_page_unit_pc_option.click();
		items.items_input_page_description.sendKeys(item_des);
		
	}

	
	@When("I click Save Item button")
	public void i_click_save_item_button() {
		
		items.items_page_saveltem_btn.click();
		utils.waitUntilUrlChanged("items");
		Assert.assertTrue(items.items_items_text.isDisplayed());

	}

	
	@Then("The item is added to the item list table")
	public void the_item_is_added_to_the_item_list_table() throws InterruptedException {
		
		
		//if (items.items_success_message.isDisplayed()) {
		//	Thread.sleep(5000);			
		//}
	
		items.items_success_message.click();
		utils.waitForElementToBeVisible(items.items_page_filter_btn);
		items.items_page_filter_btn.click();
		
		utils.waitForElementToBeVisible(items.items_page_filter_name_box);
		items.items_page_filter_name_box.sendKeys(itemname);
		
		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+itemname+"']")).isDisplayed());
		
		//Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//span[contains(test(),'"+ itemprice.substring(0,2) +"')]")).isDisplayed());
		
		
	}

	
	@Then("I delete the item")
	public void i_delete_the_item() throws InterruptedException {

		utils.waitForElementToBeVisible(items.items_page_3dot_menu);
		utils.waitUntilElementClickable(items.items_page_3dot_menu);
		Thread.sleep(1000);
	    items.items_page_3dot_menu.click();
	    utils.waitForElementToBeVisible(items.items_page_3dot_delete_option);
	    Assert.assertTrue(items.items_page_3dot_delete_option.isDisplayed());
	    items.items_page_3dot_delete_option.click();
	    utils.waitForElementToBeVisible(items.items_page_delete_ok_btn);
	    items.items_page_delete_ok_btn.click();
	   
	    utils.waitForElementToBeVisible(items.items_Input_noResultFound_text);
	    Assert.assertTrue(items.items_Input_noResultFound_text.isDisplayed());
	    
		
		
	}
	
	
	@When("I update the item price with {string}")
	public void i_update_the_item_price_with(String newPrice) {
		
		itemprice = newPrice;
		
		
		
		items.items_page_3dot_menu.click();
		utils.waitForElementToBeVisible(items.items_page_3dot_edit_option);
		items.items_page_3dot_edit_option.click();
		utils.waitForElementToBeVisible(items.items_page_edit_item_headerText);
		Assert.assertTrue(items.items_page_edit_item_headerText.isDisplayed());
		
		items.items_input_page_price_box.clear();
		items.items_input_page_price_box.sendKeys(newPrice);
		
		items.items_page_update_item_btn.click();
		
		utils.waitUntilUrlChanged("items");

		Assert.assertTrue(items.items_items_text.isDisplayed());
	
		//*
		//items.items_page_filter_btn.click();
		//utils.waitForElementToBeVisible(items.items_page_filter_name_box);
		//items.items_page_filter_name_box.sendKeys(itemname);
		
		//utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
		

		
		
	
	}
	
	
	
	
	}
