package tests;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pageobjects.AccountPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import util.DataUtil;
import util.MyXLSReader;

public class Login extends Base  {
	WebDriver driver;
	MyXLSReader excelReader;
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
	@Test(dataProvider="dataSuplier")
	public void testLogin(HashMap<String ,String>hMap) {
		
		if(!DataUtil.isRunnable(excelReader, "LoginTest", "Testcases")||hMap.get("Runmode").equals("N")) {
			throw new SkipException("Run mode is set to N ,hence not excecuted");
		}
		
		driver =openBrowserAndApplicationURL(hMap.get("Browser"));
		
		HomePage homepage=new HomePage(driver); // Shifting xpath Locators to Page object modal and removing hard coding stuff...
		homepage.clickOnMyAccountDropMenu();
		
		LoginPage loginpage =homepage.selectLoginButton();// This is Coming From Login Page and this is implimented  and return method which is in home page.java file 
		loginpage.enterEmailAddress(hMap.get("Username"));// This is For Entering Password into the Email Field
		loginpage.enterPassword(hMap.get("Password"));// This is For Entering Password inton the Password Field
		
		
//		String password = String.valueOf(hMap.get("Password"));
//		System.out.println("Entering password: " + password);
//		driver.findElement(By.name("password")).sendKeys(password);
		
	    AccountPage accountpage=	loginpage.clickOnLoginButton();
	      
	

			
		String  expectedResult=hMap.get("ExpectedResult");
	
		boolean expectedConvertedResult = false;
		
		if(expectedResult.equalsIgnoreCase("Success")) {
			
			expectedConvertedResult=true;
		}else if (expectedResult.equals("Failure")) {
			expectedConvertedResult=false;
		}
		boolean actualResult=false;
		actualResult=accountpage.verifyTheDisplayofEditYourInformationOption();
		
		Assert.assertEquals(actualResult,expectedConvertedResult );
				
			}

	@DataProvider
	public Object[][] dataSuplier() {
		Object[][] data=null;
		try {
		 excelReader=new MyXLSReader("src\\test\\resources\\TutorialsNinja.xlsx");
	   data= DataUtil.getTestData(excelReader,"LoginTest","Data");
		
		}catch(Throwable e) {
			e.printStackTrace();
		}
		return data;
				
		
	}
	
	
}
