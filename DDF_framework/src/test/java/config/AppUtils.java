package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtils
{
	public static Properties conpro;
	public static WebDriver driver;
	@BeforeTest
	public static void setup() throws Throwable
	{
		conpro =new Properties();
		conpro.load(new FileInputStream("E:\\Live Project\\DDF_framework\\PropertyFile\\Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.manage().deleteAllCookies();
			//driver.manage().window().maximize();
			driver.manage().window().minimize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		}
		else {
			Reporter.log("Browser is not matched",true);
		}
	}
	@AfterTest
	public static void teardown()
	{
		//driver.close();
		driver.quit();
	}	
}
