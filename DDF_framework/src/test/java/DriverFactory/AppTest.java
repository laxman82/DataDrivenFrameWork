package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commanFunctions.FunctionLibrary;
import config.AppUtils;
import utilities.ExcelFileUtils;

public class AppTest extends AppUtils
{
String inputpath="./FileInput/LoginData.xlsx";
String outputpath="./FileOutput/loginResult.xlsx";
ExtentReports reports;
ExtentTest logger;
@Test
public void starttest() throws Throwable 
{     
	reports=new ExtentReports("./Reports/LoginReports.html");
	ExcelFileUtils xl=new ExcelFileUtils(inputpath);
	int rc=xl.rowCount("Login");
	Reporter.log("rowcount:"+rc,true);
	for(int i=1;i<=rc;i++) 
	{
		logger=reports.startTest("login Test");
		logger.assignAuthor("laxman reddy");
		logger.assignCategory("login function");
		String user=xl.getCellData("Login", i, 0);
		String pass=xl.getCellData("Login", i, 1);
		boolean res=FunctionLibrary.adminLogin(user, pass);
		if(res)
		{
			xl.setcelldata("Login", i, 2, "login sucess",outputpath );
			xl.setcelldata("Login", i, 3, "Pass",outputpath );
			logger.log(LogStatus.PASS, "valid data");
		}
		else {
			File sc=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sc, new File("./screenshot/loginscreenshot/"+i+"Login.png"));
			xl.setcelldata("Login", i, 2, "login fail",outputpath );
			xl.setcelldata("Login", i, 3, "Fail",outputpath );
			logger.log(LogStatus.FAIL, "invalid data");
		}
	}
	reports.endTest(logger);
	reports.flush();
}
     


}
