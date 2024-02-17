package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;


public class ErrorValidationsTest extends BaseTests{
	@Test(groups= {"errorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		//ProductCatalog pc=lp.loginApplication("idontcare@gmail.com", "Abcde@123");
		lp.loginApplication("idontcre@gmail.com", "Abcde@123");
		Assert.assertEquals("Incorrect email password.", lp.getErrorMessage());
		//lp.getErrorMessage().equals("Incorrect email of password.");
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName="ZARA COAT 93";
		
		ProductCatalog pc=lp.loginApplication("mailnew@gmail.com", "Abcde@123");
		
		List<WebElement> products=pc.getProductList();
		pc.addProductToCart(productName);
		MyCartPage mp=pc.goToCartPage();
		Boolean match=mp.verifyProductPresent(productName);
		Assert.assertFalse(false);
	}
	
}
