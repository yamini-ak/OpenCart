package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid -login success - test pass - logout
 * Data is valid - login fail - test fail
 * Data is invalid - login fail - test pass 
 * Data is invalid - login success - test fail - logout
 */
public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven")//if the data providers are created on the same class then dataProviderClass = DataProvider.class is not required
	public void verify_loginDDT(String email, String pwd, String expres)
	{
		try {
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();	
		hp.clicklogin();
		//login
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setpwd(pwd);
		lp.clicklogin();
		//My account page
		MyAccountPage myac = new MyAccountPage(driver);
		boolean status = myac.isMyaccountPageExists();
		
		Thread.sleep(5000);
		if(expres.equalsIgnoreCase("valid"))
		{
			if(status == true)
			{
				Assert.assertTrue(true);
				myac.clicklogout();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(expres.equalsIgnoreCase("invalid"))
		{
			if(status == true)
			{
				myac.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
	}

}
