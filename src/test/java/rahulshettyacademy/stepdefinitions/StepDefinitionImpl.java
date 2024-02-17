package rahulshettyacademy.stepdefinitions;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.PaymentPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class StepDefinitionImpl extends BaseTests{
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ThankYouPage thankYouPage;
	public PaymentPage paymentPage;
	public MyCartPage myCartPage;
	
	@Given("^I Landed on eCommerce page$")
	public void landedOnECommercePage() throws IOException{
		landingPage=launchApplication();
	}
	@Given("^Logged in with (.+) and (.+)$")
	public void login(String username, String password) {
		productCatalog=landingPage.loginApplication(username,password);
	}
	@When("^I Add product (.+) to cart$")
	public void addProductToCart(String productName) {
		List<WebElement> products=productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkoutSubmitOrder(String productName) throws InterruptedException {
		myCartPage=productCatalog.goToCartPage();
		Boolean match=myCartPage.verifyProductPresent(productName);
		Assert.assertTrue(match);
		
		paymentPage=myCartPage.goToCheckout();
		paymentPage.selectCountry(driver,"india");
		thankYouPage=paymentPage.clickSubmitButton(driver);
	}
	@Then("{string} is displayed on ConfirmationPage")
	public void messageDisplayedConfirmationPage(String string) throws InterruptedException {
		String confMessage=thankYouPage.getConfMessage();
		Assert.assertTrue(confMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void verifyloginErrorMessage(String message) {
		Assert.assertEquals(message, landingPage.getErrorMessage());
		driver.close();
	}
}
