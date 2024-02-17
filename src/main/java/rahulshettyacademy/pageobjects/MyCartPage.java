package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent{
	WebDriver driver;
	ProductCatalog productCatalog=new ProductCatalog(driver);
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);//this points to driver of current class
	}
	

	public boolean verifyProductPresent(String productName) throws InterruptedException {
		//WebElement pname=productCatalog.getProductByName(productName);
		//String selectedProduct=pname.getText();
		Thread.sleep(5000);
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		return match;
	}
	
	public PaymentPage goToCheckout() {
		checkOutButton.click();
		return new PaymentPage(driver);
	}
}
