package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
	
		this.driver=driver;
		PageFactory.initElements(driver,this);
	
	}
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	public void clickOnMyAccountDropMenu() {
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginButton() {
		loginOption.click();
		
		return new LoginPage(driver);// This is Coming From Login Java Class and this is For Send Keys Method.....
	}
	
	

}
