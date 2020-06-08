package test;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.invoiceapp.pages.Company_Setup;

import invoiceapp.base.Testengine;

public class SmokeTest extends Testengine implements ITestListener {
	
	public static Company_Setup com_Setup;
	
	@BeforeClass
	public static void initiateDrivers() {
		com_Setup = new Company_Setup(driver);
	}

	@Test
	public void test_TC001_verifyAppLaunch() {		
		Assert.assertTrue(com_Setup.clickOnLogobutton());
		Assert.assertTrue(com_Setup.clickOnSignaturebutton());
	}

	public void onTestStart(ITestResult result) {
		log.info("Test case started successfully");
		log.info("Trying to launch the Application under Test");
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
