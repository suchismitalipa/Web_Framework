package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtenReportListener {

	public static ExtentHtmlReporter report = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	
	public static ExtentReports setUp() {
		String ReportLocation = "./Reports/Extent_Report.html";
		report = new ExtentHtmlReporter(ReportLocation);
		report.config().setDocumentTitle("Automation Test Report");
		report.config().setReportName("Automation test");
		report.config().setTheme(Theme.DARK);
		report.start();
		System.out.println("Extent Report LOcation initialized");
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Application", "TLCGO");
		extent.setSystemInfo("OS Name", System.getProperty("OS.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		System.out.println("System info set in exttent report");
		return extent;
	}
	
	public static void TestHandle(String teststatus, WebDriver driver, ExtentTest extenttest, Throwable throwable) {
		switch (teststatus) {
		case "FAIL":
			extenttest.fail(MarkupHelper.createLabel("Test Case is Failed", ExtentColor.RED));
			extenttest.error(throwable.fillInStackTrace());
			try {
				extenttest.addScreenCaptureFromPath(captureScreenshot(driver));
			}catch (IOException e) {
				e.printStackTrace();
			}
			if (driver != null) {
				driver.quit();
			}
			break;
		case "PASS":
			extenttest.pass(MarkupHelper.createLabel("Test Case is Passed", ExtentColor.GREEN));
			break;
		default:
			break;
		}
}
	
	public static String captureScreenshot(WebDriver driver) throws IOException {
		TakesScreenshot Ts = (TakesScreenshot)driver;
		File Src = Ts.getScreenshotAs(OutputType.FILE);
		String destLoc = "E:\\Selenium\\Cucumber_Listener\\Screenshot\\Selenium.cucumber"+getcurrentdatetime()+".png";
		File Dest = new File(destLoc);
		FileUtils.copyFile(Src, Dest);
		return destLoc;	
	}
	
	public static String getcurrentdatetime() {
		String str = null;
		try {
			DateFormat dt = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss:SSS");
			Date date = new Date();
			str = dt.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		}catch(Exception e){}
		return str;
	}
}

