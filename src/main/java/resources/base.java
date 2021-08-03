package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class base {

	public WebDriver driver;
	public Properties prop;
	
	DesiredCapabilities caps;
	
	public static final String USERNAME="dummy";
	public static final String ACCESS_KEY="dummy";
	
	public static final String sauceURL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	public WebDriver initializeDriver() throws Exception
	{
		prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\dasde\\eclipse-workspace\\E2EProject\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\dasde\\Downloads\\selenium\\chromeDriver\\chromedriver.exe");
			driver=new ChromeDriver();
			
		}
		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\dasde\\Downloads\\selenium\\firefoxDriver\\geckodriver.exe");
			driver = new FirefoxDriver ();
			
		}
		else if (browserName.equals("remoteWebDriver"))
		{
			caps = DesiredCapabilities.chrome();
		    caps.setCapability("platform", "Windows 10");
		    caps.setCapability("version", "latest");
		    driver = new RemoteWebDriver(new URL(sauceURL), caps);
			
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
{
	TakesScreenshot ts=(TakesScreenshot) driver;
	File source =ts.getScreenshotAs(OutputType.FILE);
	String destinationFile= System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
	FileUtils.copyFile(source, new File(destinationFile));
	return destinationFile;
}

}
