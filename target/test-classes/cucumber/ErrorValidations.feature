@tag
Feature: Error Validations

#Background:
#Given I Landed on eCommerce page

  @ErrorValidation @Regression
  Scenario Outline: Positive Test of Purchasing the Order
  Given I Landed on eCommerce page
  When Logged in with <username> and <password>
  Then "Incorrect email or password." message is displayed
    
    Examples: 
      | username  | password |
      | idontcre@gmail.com | Abcde@123 |