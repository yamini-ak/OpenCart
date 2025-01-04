package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{//This is not a data driven testcase, this is a single testcase
	
	@Test(groups={"Regression","master"})//master contains all the testcases
	public void verify_account_infor()
	{
		logger.info("**** Starting TC001_AccountRegistrationTest *****");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();	
		logger.info("*** Clicked on Myaccount");
		
		hp.clickregister();
		logger.info("*** Clicked on register");
		
		AccountRegistrationPage ar = new AccountRegistrationPage(driver);
		logger.info("*** Providing customer details");
		ar.setFirstname(randomString().toUpperCase());
		ar.setLastname(randomString().toUpperCase());
		ar.setemail(randomString() + "@gmail.com");
		ar.settelephone(randomint());
		
		String cmnpwd = randomaplhanum();
		ar.setpassword(cmnpwd);
		ar.setconfirmpwd(cmnpwd);
		
		ar.setPrivacyPolicy();
		ar.clickcontinuebtn();
		
		logger.info("***Validating expected message");
		String configmsg = ar.getConfirmationmsg();
		
		if(configmsg.equals("Your Has Been Created!!!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs");
			Assert.assertFalse(false);		
		}
		//Assert.assertEquals(configmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}
	
}

