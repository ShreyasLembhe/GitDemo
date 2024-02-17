package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	WebDriver driver;
	
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);//this points to driver of current class
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
	
	//Page Factory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	
	By productsBy=By.cssSelector(".mb-3");
	//By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	//List<WebElement> cartbtn=driver.findElements(By.cssSelector(".card-body button:last-of-type"));
	
	@FindBy(css=".card-body button:last-of-type")
	List<WebElement> addToCartbtns;
	
	public List<WebElement> cartelementsList() {
		waitForElementToAppear(productsBy);
		return addToCartbtns;
	}
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public void getProductByName(String productName) {
		for(int i=0;i<products.size();i++) {
			for(int j=0;j<addToCartbtns.size();j++) {
				if(products.get(i).getText().contains(productName)) {
					addToCartbtns.get(i).click();
					break;
				}
			}
		}
//		WebElement prod=getProductList().stream().filter(product->
//		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
//		return prod;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName);
//		WebElement prod=getProductByName(productName);
//		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisAppear(spinner);
	}
	
}
