package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement firstname;
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lastname;
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemail;
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txttelephone;
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtconfirmpwd;
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chckpolicy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continuebtn;
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement message;
	
	public void setFirstname(String fname)
	{
		firstname.sendKeys(fname);
	}
	public void setLastname(String lname)
	{
		lastname.sendKeys(lname);
	}
	public void setemail(String email)
	{
		txtemail.sendKeys(email);
	}
	public void settelephone(String phone)
	{
		txttelephone.sendKeys(phone);
	}
	public void setpassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	public void setconfirmpwd(String confrimpwd)
	{
		txtconfirmpwd.sendKeys(confrimpwd);
	}
	public void setPrivacyPolicy()
	{
		chckpolicy.click();
	}
	public void clickcontinuebtn()
	{
		continuebtn.click();
	}
	
	public String getConfirmationmsg()
	{
		try
		{
			return message.getText();
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}
	
	

}
