package commanFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtils;

public class FunctionLibrary extends AppUtils
{
public static boolean adminLogin(String username,String password) throws Throwable  
{
	driver.get(conpro.getProperty("url"));
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	
	driver.findElement(By.xpath(conpro.getProperty("objreset"))).click();
	driver.findElement(By.xpath(conpro.getProperty("objuser"))).sendKeys(username);
	driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(password);
	driver.findElement(By.xpath(conpro.getProperty("objlogin"))).click();
	String expected="dashboard";
	String actual=driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		Reporter.log("username and password are valid");
		Thread .sleep(5000);
		driver.findElement(By.xpath(conpro.getProperty("objlogout"))).click();
		
	 return true;
	}
	else 
	{
	
		String errormsg=driver.findElement(By.xpath(conpro.getProperty("objerrormsg"))).getText();
		Thread .sleep(5000);
		driver.findElement(By.xpath(conpro.getProperty("objok"))).click();
		Reporter.log(errormsg+expected+"-----"+actual,true);
		return false;
	}
	
	
}
	
	
	
	
	
	
	
	
}
