package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class validateTitle extends base{
	public WebDriver driver;
	 public static Logger log=LogManager.getLogger(base.class.getName());
@BeforeTest
   
	public void initialize() throws Exception
	{
	driver=initializeDriver();
	log.info("Driver is initialized");
	driver.get(prop.getProperty("URL"));
	log.info("Navigated to Homepage");
	}



@Test()

public void validateTitle() throws Exception
	{
	
	LandingPage lp=new LandingPage(driver);
	
	//lp.getLogin().click();
	Assert.assertEquals(lp.getText().getText(), "FEATUREDCOURSES");
	log.info("Successfully validated text message");
	Assert.assertTrue(lp.checkLink().isDisplayed());
	log.info("Navigation bar is displayed");
	}

	@ AfterTest
	public void teardown()
	{
		driver.close();
	}




}

