@tag
Feature: Purchase the order from eCommerce website

Background:
Given I Landed on eCommerce page

  @Regression
  Scenario: Positive Test of Purchasing the Order
  Given Logged in with <username> and <password>
  When I Add product <productName> to cart
  And Checkout <productName> and submit the order
  Then "THANKYOU FOR THE ORDER." is displayed on ConfirmationPage
    
    Examples: 
      | username  | password | productName |
      | idontcare@gmail.com | Abcde@123 | IPHONE 13 PRO|