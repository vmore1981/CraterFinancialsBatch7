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


public class itemsManagement_steps {

	Access_control_page acp = new Access_control_page();
	Dashboard_page dash_page = new Dashboard_page();
	BrowserUtils utils = new BrowserUtils();
	Items_page items = new Items_page();
	String itemname;
	
	
	
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
	public void the_item_is_added_to_the_item_list_table() {

		items.items_page_filter_btn.click();
		utils.waitForElementToBeVisible(items.items_page_filter_name_box);
		items.items_page_filter_name_box.sendKeys(itemname);
		
		utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
		//Assert.assertTrue(Driver.getdriver().findElement(By.xpath("//a[text()='"+itemname+"']").isDisplayed));
		
		
		
	}

	
	@Then("I delete the item")
	public void i_delete_the_item() {

		items.items_page_3dot_menu.click();
		utils.waitForElementToBeVisible(items.items_page_3dot_delete_option);
		items.items_page_3dot_delete_option.click();		
		utils.waitForElementToBeVisible(items.items_Input_delete_youSure_text);
		Assert.assertTrue(items.items_Input_delete_youSure_text.isDisplayed());
		items.items_page_delete_ok_otn.click();
		utils.waitForElementToBeVisible(items.items_Input_noResultFound_text);
	}
	
	
	
	
	
	
	
	}
