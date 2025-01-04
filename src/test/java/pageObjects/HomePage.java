package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
		
		public HomePage(WebDriver driver)//driver coming from the another page object class
		{
			super(driver);//passing the received driver to basepage class constructor
		}
		
		@FindBy(xpath = "//a[@title='My Account']")
		WebElement linkMyaacount;
		@FindBy(xpath = "//a[normalize-space()='Register']")
		WebElement linkregister;
		@FindBy(xpath = "//a[normalize-space()='Login']") //added in step5 login
		WebElement linklogin;

		public void clickMyaccount()
		{
			linkMyaacount.click();
		}
		
		public void clickregister()
		{
			linkregister.click();
		}
		public void clicklogin()
		{
			linklogin.click();
		}
}
