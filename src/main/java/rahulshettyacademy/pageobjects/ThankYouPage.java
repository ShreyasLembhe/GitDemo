package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ThankYouPage extends AbstractComponent{
	WebDriver driver;
	//By confirmMsg=By.cssSelector(".hero-primary");
	@FindBy(css=".hero-primary")
	WebElement confirmMsg;
	
	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);//this points to driver of current class
	}
	
	public String getConfMessage() throws InterruptedException {
		waitForWebElementToAppear(confirmMsg);
//		String confMessage=driver.findElement(confirmMsg).getText();
//		Assert.assertTrue(confMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		
//		driver.close();
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOf(confirmMsg));
		return confirmMsg.getText();
	}

}
