package com.invoiceapp.pages;

import org.openqa.selenium.By;

import invoiceapp.base.Testengine;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class Company_Setup extends Testengine {
	
	public AppiumDriver<?> driver;
	public static Boolean assertion = false;

	public Company_Setup(AppiumDriver<?> driver) {
		this.driver = driver;
	}
	
	// Variables are declared as static to access directly in methods
	static By addLogo = MobileBy.id("frag_obup_RlAddCompLogo");
	static By addSignature = MobileBy.id("frag_obup_RlAddSignature");


	public Boolean clickOnLogobutton() {
		assertion = false;
		try {
			driver.findElement(addLogo).click();
			assertion = true;
			log.info("Add Logo, button clicked successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click on the Add Logo button, " + e);
		}
		return assertion;
	}

	public Boolean clickOnSignaturebutton() {
		assertion = false;
		try {
			driver.findElement(addSignature).click();
			assertion = true;
			log.info("Add Logo, button clicked successfully");
		} catch (Exception e) {
			assertion = false;
			log.error("Unable to click on the Add Logo button, " + e);
		}
		return assertion;
	}
}
