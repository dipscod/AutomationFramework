package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

	
	
	public WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[contains(text(),'Login')]") WebElement signIn;
	
	@FindBy(xpath="//div[@class='text-center']//h2") WebElement text;
	
	@FindBy(xpath="//a[contains(text(),'Contact')]") WebElement link;
	
	
	//@FindBy(id="password") WebElement password;
	//@FindBy(className="signinbtn") WebElement signIn;
	
	
	public WebElement getLogin()
	{
		return signIn;
	} 
	
	public WebElement getText()
	{
		return text;
	} 
	
	public WebElement checkLink()
	{
		return link;
	} 
}
