package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver)//receiving from homepage class
	{
		this.driver = driver;//assigning received driver to this class driver
		PageFactory.initElements(driver, this);//initiate the driver from here
	}
	

}
