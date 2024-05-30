#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
# Feature: Title of your feature
#   I want to use this template for my feature file
#   @tag1
#   Scenario: Title of your scenario
#     Given I want to write a step with precondition
#     And some other precondition
#     When I complete action
#     And some other action
#     And yet another action
#     Then I validate the outcomes
#     And check more outcomes
#   @tag2
#   Scenario Outline: Title of your scenario outline
#     Given I want to write a step with <name>
#     When I check for the <value> in step
#     Then I verify the <status> in step
#     Examples:
#       | name  | value | status  |
#       | name1 |     5 | success |
#       | name2 |     7 | Fail    |
Feature: Access control user management. Use cases for app access control

  @valid_login
  Scenario: As a user, I should be able to login
    Given I am on the login page
    And login page components exist
    When I enter valid username and valid password
    And I click login button
    Then I should be on the Dashboard page
   

  @valid_login_variable
  Scenario: As a user, I should be able to login
    Given I am on the login page
    And login page components exist
    When I enter valid username and valid password
    And I click login button
    Then I should be on the " Dashboard" page
    And "Success!" message displays

  @invalid_login
  Scenario: As a user, I should not be able to login with invalid credentials
    Given I am on the login page
    When I enter invalid username <"username"> and invalid password <"password">
    And I click login button
    Then I should not be logged in

	Examples:
  |		username										|		password					|    
  |		dummy@primetechschool.com		|		nottrealPass			|    
  |		notreal@primetehcschool.com	|		primetech@school	|	    
  |		dummy@primetechschool.com		|											|    
  |																|		primetech@school	|    