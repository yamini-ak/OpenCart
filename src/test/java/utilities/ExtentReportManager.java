package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException; 
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports. ExtentReports;
import com.aventstack.extentreports. ExtentTest;
import com.aventstack.extentreports. Status;
import com.aventstack.extentreports.reporter. ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration. Theme;
import testBase.BaseClass;
public class ExtentReportManager implements ITestListener 
	{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		String repName;
		public void onStart(ITestContext testContext)//test method details stored in the variable 
		{
			/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt=new Date();
			String currentdatetimestamp=df.format(dt); */

			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date() );
			repName = "Test-Report-" + timeStamp + ".html"; 
			sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify loc
			sparkReporter.config().setDocumentTitle("opencart Automation Report"); 
		    sparkReporter.config().setReportName("opencart Functional Testing");
		    sparkReporter.config().setTheme (Theme.DARK);

			extent= new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "opencart"); 
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Sub Module", "Customers");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environemnt", "QA");

			String os = testContext.getCurrentXmlTest().getParameter("os"); 
			extent.setSystemInfo("Operating System", os);
			String browser= testContext.getCurrentXmlTest().getParameter("browser"); 
			extent.setSystemInfo("Browser", browser);

			List<String> includedGroups= testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) 
			{ 
				 extent.setSystemInfo("Groups", includedGroups.toString());
			}
		}

			public void onTestSuccess (ITestResult result) //will capture result from the test methods
		{
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS, result.getName()+"got successfully executed");
		}
			public void onTestFailure(ITestResult result)
			{
				test =extent.createTest(result.getTestClass().getName());
				test.assignCategory(result.getMethod().getGroups());

				test.log(Status.FAIL, result.getName()+" got failed");
				test.log(Status.INFO, result.getThrowable().getMessage());

				try {
					String imgPath =new BaseClass().captureScreen(result.getName()); 
					/*
					 * as the capture screen method is not static we cannot access it directly
					 * so created object for baseclass but didnot store it into seperate variable but I directly invoked capturescreen method from it and returning its data into impath
					 */
					test.addScreenCaptureFromPath(imgPath);//attaching screenshot to the report
				}
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}

			}

			public void onTestSkipped (ITestResult result) 
			{
				test =extent.createTest(result.getTestClass().getName()); 
				test.assignCategory(result.getMethod().getGroups());
				test.log(Status.SKIP, result.getName()+" got skipped");
				test.log(Status.INFO, result.getThrowable().getMessage());
			}
			public void onFinish (ITestContext testContext) 
			{
				extent.flush();//consolidate all the info to the report and generates it
				String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
				File extentReport = new File(pathofExtentReport);
				try 
				{
				Desktop.getDesktop().browse(extentReport.toURI());//opens the report on the browser automatically
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			
			}
	}

