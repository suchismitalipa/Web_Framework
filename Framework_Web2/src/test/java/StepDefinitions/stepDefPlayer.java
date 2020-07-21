package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;


import Page.PlayerPage;
import Utilities.ExtenReportListener;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class stepDefPlayer {
	
	public static WebDriver driver;
	public static ExtenReportListener El;
	public static Logger logger;
	public static Properties configProp;
	public static PlayerPage pp;
	
	public static String randomestring() {
		String str = RandomStringUtils.randomAlphabetic(5);
		return str;
	}
	
	@Before
	public void setup() throws IOException
	{
		//Logger
				logger=Logger.getLogger("TLCGO"); //Added logger
				PropertyConfigurator.configure("Log4j.properties");//Added logger
				
				//Reading properties
				configProp=new Properties();
				FileInputStream configPropfile=new FileInputStream("config.properties");
				configProp.load(configPropfile);
				
				String br=configProp.getProperty("browser");
				
				if(br.equals("chrome"))
				{
				System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
				driver=new ChromeDriver();
				}
				else if (br.equals("firefox")) {
					System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
					driver = new FirefoxDriver();
				}
				else if (br.equals("ie")) {
					System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
					driver = new InternetExplorerDriver();
				}
				
				logger.info("******** Launching browser*********");
		
	}
	
	 @After
	    public void afterScenario() {
	        driver.quit();
	    }
	 
	 
	@Given("^User Launch Chrome browser$")
	public void user_Launch_Chrome_browser() throws Throwable {
		
		ExtentTest loginfo = null; 
		try {


			El.test = El.extent.createTest(Feature.class,"Player");
			El.test = El.test.createNode(Scenario.class, "Validate Player Functionality");
			driver.get(configProp.getProperty("url"));
			loginfo = El.test.createNode(new GherkinKeyword("Given"), "user_opens_the_browser");
			loginfo.pass("Browser opened");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}
	@When("^User Select a Video$")
	public void user_Select_a_Video() throws Throwable {
		
		ExtentTest loginfo = null; 
		try {
			loginfo = El.test.createNode(new GherkinKeyword("When"), "user_Select_a_Video");
			pp = new PlayerPage(driver);
			pp.SelectVideo();
			loginfo.pass("Video Selected");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}

	@When("^User Start a Series$")
	public void user_Start_a_Series() throws Throwable {
		ExtentTest loginfo = null; 
		try {
			loginfo = El.test.createNode(new GherkinKeyword("When"), "user_Start_a_Series");
			pp.clickStartSeries();
			loginfo.pass("Start Series");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}

	@When("^User Click a Video$")
	public void user_Click_a_Video() throws Throwable {
		ExtentTest loginfo = null; 
		try {
			loginfo = El.test.createNode(new GherkinKeyword("When"), "user_Select_a_Video");
			pp.clickVideo();
			loginfo.pass("Click Video");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}

	@Then("^User Rewind a Video$")
	public void user_Rewind_a_Video() throws Throwable {
		ExtentTest loginfo = null; 
		try {
			loginfo = El.test.createNode(new GherkinKeyword("Then"), "user_Rewind_a_Video");
			pp.clickSRewind();
			loginfo.pass("Rewind Video");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}

	@When("^User Forward a Video$")
	public void user_Forward_a_Video() throws Throwable {
		ExtentTest loginfo = null; 
		try {
			
			loginfo = El.test.createNode(new GherkinKeyword("When"), "user_Forward_a_Video");
			pp.clickForward();
			loginfo.pass("Forward Video");

		}
			catch(AssertionError | Exception e) {
				El.TestHandle("FAIL",driver,loginfo,e);
			}}



}
