package testCases;

import java.io.FileReader;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity", "master"})//master contains all the testcases
	public void login()
{
		try {
		logger.info("**** Starting TC002_Login test*****");
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		logger.info("*** Clicked on Myaccount");
		
		hp.clicklogin();
		logger.info("*** Clicked on login");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("*** Providing login details");
		lp.setEmail(p.getProperty("email"));
		lp.setpwd(p.getProperty("password"));
		lp.clicklogin();
		
		MyAccountPage myac = new MyAccountPage(driver);
		boolean status = myac.isMyaccountPageExists();
		
		//Assert.assertEquals(status,true,"login failed");
		Assert.assertTrue(status);
		
		}
		catch(Exception e)
		{
			Assert.fail();
		}
}
}