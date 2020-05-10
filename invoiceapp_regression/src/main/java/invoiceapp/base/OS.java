package invoiceapp.base;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class OS {
	public static AppiumDriver<?> driver;
	public static String platform = "ios";
	
	@BeforeMethod
	public static void startTest(String platform) {
		if(platform.equalsIgnoreCase("Android")) {
			driver = startAppium_Android();
		} else {
			driver = startAppium_IOS();
		}
		}
	
	/*
	 * This method is used for initiate the AppiumDriver with caps and connection protocol
	 */
	public static AndroidDriver<?> startAppium_Android() {
		// Initializing the Appium driver
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "5642c6b9");
			cap.setCapability("appActivity", "com.invoiceapp.InvoiceLaunchScreenAct");
			cap.setCapability("appPackage", "com.invoiceapp");
			cap.setCapability("autoLaunch", false);
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
			driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		//Returning the instance of the driver to the parent method
		return (AndroidDriver<?>) driver;
	}
	
	public static IOSDriver<?> startAppium_IOS() {
		// Initializing the Appium driver
		try {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0.1");
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
			cap.setCapability(MobileCapabilityType.UDID, "3jtn3j4n3ijn3ji4nrj34inrj34nr34nrk");
			cap.setCapability("bundleId", "com.invoiceapp");
			cap.setCapability("autoLaunch", false);
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
			driver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		//Returning the instance of the driver to the parent method
		return (IOSDriver<?>) driver;
	}

	
	
}
