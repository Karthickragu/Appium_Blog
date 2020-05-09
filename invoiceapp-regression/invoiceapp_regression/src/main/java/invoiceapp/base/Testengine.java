package invoiceapp.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Testengine {

	public static Logger log;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static FileInputStream fInputStream;
	protected static Properties properties;
	
	
	@BeforeSuite
	public static void startSuite() {
		log = Logger.getLogger("devpinoyLogger");
		log.info("Test Started successfully");
	}

	@BeforeTest
	public static void startTest() {
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
			} catch (IOException ioE) {
				ioE.printStackTrace();
			}
		} catch (FileNotFoundException fnfE) {
			fnfE.printStackTrace();
			log.fatal("Unable to find/Load the Properties file ");
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}

		// Config file is loaded successfully, now with the help of properties.getProperty(key) we can retrieve the value.

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
			
			//Providing internal name for the report
			htmlReporter.config().setReportName("Test Report");
			
			// To create a test case and steps, we need to use reference variable for "ExtentTest" class. 
			// This test reference will help us to create test. 
			test = extent.createTest("testcase Name");
			
		} catch (Exception e) {
			log.error("Unable to Initialize the Extent Report" + e.getMessage());
		}
	}
	
}
