package Academy;


import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.base;


public class Listeners extends base implements ITestListener{

	ExtentReports extent= ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal <ExtentTest> extentTest= new ThreadLocal <ExtentTest> ();
	@Override
	public void onTestStart(ITestResult result) {
		String testMethodName=result.getMethod().getMethodName();
		
		test=extent.createTest(testMethodName);
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
		
		WebDriver driver=null;
		String testMethodName=result.getMethod().getMethodName();
		
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			getScreenShotPath(testMethodName,driver);
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), testMethodName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//test.log(Status.PASS, "Test Passed");
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		
		WebDriver driver=null;
		String testMethodName=result.getMethod().getMethodName();
		
		try {
			
			driver =(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
			extentTest.get().addScreenCaptureFromPath(getScreenShotPath(testMethodName,driver), testMethodName);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}


}
