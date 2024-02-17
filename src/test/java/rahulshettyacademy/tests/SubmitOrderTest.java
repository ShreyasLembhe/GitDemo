package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTests;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.PaymentPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.ThankYouPage;

public class SubmitOrderTest extends BaseTests{
	String productName="IPHONE 13 PRO";
	
	@Test(dataProvider="getData",groups= {"purchaseOrder"},retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//LandingPage lp=launchApplication();
		
		//ProductCatalog pc=lp.loginApplication("idontcare@gmail.com", "Abcde@123");
		//We are writing the objexts like below pattern because we know that action method takes us to new page i.e.
		//after submitting login button it will redirect us to ProductCatelog page and hence we can use the below syntax
		ProductCatalog pc=lp.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> products=pc.getProductList();
		pc.addProductToCart(input.get("productName"));
		MyCartPage mp=pc.goToCartPage();
		Boolean match=mp.verifyProductPresent(input.get("productName"));
		Assert.assertTrue(match);
		
		PaymentPage pp=mp.goToCheckout();
		pp.selectCountry(driver,"india");
		ThankYouPage tp=pp.clickSubmitButton(driver);
		String confMessage=tp.getConfMessage();
		Assert.assertTrue(confMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		/**
		 * driver.findElement(By.cssSelector(".form-group .text-validated")).sendKeys("ind");
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-group section")));
		
		List<WebElement> countryList=driver.findElements(By.cssSelector(".form-group section"));
		countryList.stream().filter(countries->countries.getText().equalsIgnoreCase("India")).;
		 */
	}
	
	@Test(dependsOnMethods= {"submitOrder"},retryAnalyzer=Retry.class)
	public void orderHistory() {
		ProductCatalog pc=lp.loginApplication("idontcare@gmail.com", "Abcde@123");
		OrderPage op=pc.goToOrdersPage();
		Assert.assertTrue(op.verifyOrderDisplayed(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/**
		 * HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "idontcare@gmail.com");
		map.put("password", "Abcde@123");
		map.put("product", "IPHONE 13 PRO");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "mailnew@gmail.com");
		map1.put("password", "Abcde@123");
		map1.put("product", "ZARA COAT 3");
		return new Object[][] {{map},{map1}};
		 */
		
		//return new Object[][] {{"idontcare@gmail.com","Abcde@123","IPHONE 13 PRO"},{"mailnew@gmail.com", "Abcde@123","ZARA COAT 3"}};
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data/PurchaseOrder.json");
		System.out.println(new Object[][] {{data.get(0)},{data.get(1)}});
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
