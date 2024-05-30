


Feature: Items Management


@create_items
Scenario: As a user, I am able to create an item or service
		Given As a "level1" user, I am logged in
		And I navigate to Items tab
		When I am click on Add Item button
		Then I should be on New Item Page
		When I provide Item name "coffee mug" and price "1200" unit "pc" and description "very wierd cofee mug"
		And I click Save Item button
		Then The item is added to the item list table
		And I delete the item 
		
				


@update_items
Scenario: As a user, I am able to updat e an item or service
		Given As a "level1" user, I am logged in

		
		
		
		
		
		
		
		
		
#		Given I am on the login page
#    And login page components exist
#    When I enter valid username and valid password
#    And I click login button
#    Then I should be on the Dashboard page 
		