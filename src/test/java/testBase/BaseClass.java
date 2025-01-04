package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@BeforeClass(groups = {"Sanity","Regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		//loading config.prop file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("no matching OS..");
				return;
			}
		
			//browser
			switch(br.toLowerCase())
			{
				case "chrome": capabilities.setBrowserName("chrome"); break;//break the statements not the switch case
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default: System.out.println("no matching broswer..."); return;//returns from switch case
			} 
			
			driver = new RemoteWebDriver(new URL("http://192.168.1.108:4444/wd/hub"),capabilities);
		}

		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
				case "chrome": driver = new ChromeDriver(); break;
				case "edge": driver = new EdgeDriver(); break;
				case "firefox": driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid entry"); return;//returns from switch case
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));//reading URL from properties file
		driver.manage().window().maximize();
				 
	}
	@AfterClass(groups = {"Sanity","Regression","master"})
	public void teardown()
	{
		driver.close();
	}

	public String randomString()
	{
		String randomalpha = RandomStringUtils.randomAlphabetic(6);
		return randomalpha;
	}
	public String randomint()
	{
		String randomnum = RandomStringUtils.randomNumeric(10);
		return randomnum;
	}
	public String randomaplhanum()
	{
		String randomalpha = RandomStringUtils.randomAlphabetic(6);
		String randomnum = RandomStringUtils.randomNumeric(10);
		return randomalpha + "@"+ randomnum;
	}
	public String captureScreen(String tname) throws IOException//tname = name which is provided for secreenshot
	{
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot  = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timestamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
