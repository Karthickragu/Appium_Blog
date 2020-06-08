package invoiceapp.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

/***
 * 
 * @author Ragav
 * @date May 10, 2020
 * 
 */

/**
 * 
 * @Usage: This class is used to initiate the Appium Driver and all other
 *         reporting stuffs. All static variables are declared here and
 *         inherited by other classes.
 *
 */

public class Testengine {

	public static Logger log;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static FileInputStream fInputStream;
	protected static Properties properties;

	// Initiating Appium Driver
	public static AppiumDriver<?> driver;

	/*
	 * This method is used for initializing the Log4j and Config.properties
	 */
	@BeforeSuite
	public static void startSuite() {
		log = Logger.getLogger("devpinoyLogger");
		log.info("Test Started successfully");

		log.info("Initializing the Config.properties file");
		// Path where Config.properties file is placed
		String propertyFilePath = "src//main//resources//config.properties";

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
				log.info("Properties file loaded successfully:");
				// Config file is loaded successfully, now with the help of
				// properties.getProperty(key) we can retrieve the value.
			} catch (IOException ioE) {
				ioE.printStackTrace();
			}
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			log.fatal("Unable to find/Load the Properties file ");
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	

	/*
	 * This method is used for init the Appium Driver and Extent report
	 */
	@BeforeTest
	public static void startTest() {

		// Getting the driver declared using our Capabilities
		try {
			driver = startAppium();
		} catch (Exception e) {
			log.fatal("Driver is not Initiated as Expected" + e.getMessage());
		}
		// LOC for initializing the Extent Report
		log.info("Initializing the Extent Report");
		try {
			log.info("Extent report is available under the directory " + System.getProperty("user.dir") + "/Reports/");
			// starting the HTML report
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/testReport.html");
			// To Initialize the reporters
			extent = new ExtentReports();
			// attach only HtmlReporter
			extent.attachReporter(htmlReporter);
			// Providing internal name for the report
			htmlReporter.config().setReportName("Test Report");
			// To create a test case and steps, we need to use reference variable for
			// "ExtentTest" class.
			// This test reference will help us to create test.
			test = extent.createTest("testcase Name");
		} catch (Exception e) {
			log.error("Unable to Initialize the Extent Report" + e.getMessage());
		}
	}


	/*
	 * This method is used for initiate the AppiumDriver with caps and connection protocol
	 */
	public static AppiumDriver<?> startAppium() {
		// Initializing the Appium driver
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			// All Capability values are retrieved from Config.properties file.
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("PLATFORM_NAME"));
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("PLATFORM_VERS"));
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("DEVICE_NAME"));
			cap.setCapability("appActivity", "com.invoiceapp.InvoiceLaunchScreenAct");
			cap.setCapability("appPackage", "com.invoiceapp");
			cap.setCapability("autoLaunch", true);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);

			// Declaring the driver as "Android driver" with the Host and Port number to communicate with Appium desktop
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			//Printing the driver details in Log file
			log.info("Driver declared successfully : " +driver);
		} catch (Exception e) {
			driver = null;
			log.fatal("Driver declaration failed : " +driver);
			log.fatal(e.getStackTrace());
		}
		//Returning the instance of the driver to the parent method
		return driver;
	}

	@BeforeMethod
	public static void openApp() {
		log.info("Test case started successfully");
		log.info("Trying to launch the Application under Test");
		try{
		driver.launchApp();
		log.info("Application launched successfully");
		}catch(Exception e) {
			log.info("Unable to launch the application :" +e.getMessage());
		}
	}
	

}
