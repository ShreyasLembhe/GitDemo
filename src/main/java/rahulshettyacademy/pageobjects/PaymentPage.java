package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css=".form-group .text-validated")
	WebElement countryField;
	
	@FindBy(xpath="(//section/button/span)[2]")
	WebElement india;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
//	@FindBy(css=".hero-primary")
//	WebElement confirmMsg;
	
	By countryList=By.cssSelector(".form-group section");
	
	
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void selectCountry(WebDriver driver, String country) {
		Actions a=new Actions(driver);
		a.sendKeys(countryField,country).build().perform();
		waitForElementToAppear(countryList);
		india.click();
	}
	
	public ThankYouPage clickSubmitButton(WebDriver driver) {
		waitForWebElementToAppear(submitButton);
		submitButton.click();
		return new ThankYouPage(driver);
	}

}
