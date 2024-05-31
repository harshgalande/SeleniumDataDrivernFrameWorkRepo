package base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	public Properties prop;
	
	public WebDriver openBrowserAndApplicationURL(String browserName) {
	
	prop=new Properties();
	File propfile =new File("src\\test\\resources\\data.properties");
	try {
	FileInputStream fis=new FileInputStream(propfile);
	prop.load(fis);
	}catch(Throwable e) {
		e.printStackTrace();
	}
	WebDriver driver = null;
	if(browserName.equalsIgnoreCase("chrome")) {
		 driver=new ChromeDriver();
	}else if(browserName.equalsIgnoreCase("firefox")) {
		
		 driver=new FirefoxDriver();
	}else if(browserName.equalsIgnoreCase("edge")) {
		 driver=new EdgeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	driver.get(prop.getProperty("url"));// Removing the hardcoding Stuff and url is Moved to Resources and impliment With properties File.....

	return driver;
	}
}
