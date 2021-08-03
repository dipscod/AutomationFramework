package Academy;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage extends base{
	public WebDriver driver;
public static Logger log=LogManager.getLogger(base.class.getName());
@BeforeTest
	
	public void initialize() throws Exception
	{
	driver=initializeDriver();
	log.info("Driver is initialized");
	}
	
@Test(dataProvider="getData")

public void basePageNavigation(String username,String password,String text) throws Exception
	{
	
	driver.get(prop.getProperty("URL"));
	LandingPage lp=new LandingPage(driver);
	lp.getLogin().click();
	
	
	LoginPage l=new LoginPage(driver);
	l.getEmail().sendKeys(username);
	l.getPassword().sendKeys(password);
	l.getSigin().click();
	System.out.println(text);
	}


@DataProvider
public Object[][] getData()
{
	// rows stands for how many different data types the test should run
	// cols stands for how many values per each test
	
	
	Object[][] data=new Object[2][3];
	data[0][0]="nonrestricteduser";
	data[0][1]="hello";
	data[0][2]="test";
	
	
	
	
	data[1][0]="restricteduser";
	data[1][1]="helloworld";
	data[1][2]="texting";
	
	
	
	return data;
}
@ AfterTest
public void teardown()
{
	driver.close();
}


}

