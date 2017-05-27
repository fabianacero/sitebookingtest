package com.decameron.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class QuotePage extends WebPage{

	@FindBy(how = How.ID, using = "backimagen")
	WebElement loaderImage;
	
	@FindBy(how = How.ID, using = "vlrLiquidacionM")
	WebElement quoteValueDisplay;
	
	public QuotePage(WebDriver driver) {
		super(driver);
	}
	
	public void validateHotelQuote(){
		System.out.println("validateHotelQuote");
		waitUntilLoaderFinish(loaderImage);
		System.out.println(quoteValueDisplay.isDisplayed());
		scrollTo("0", "450");
	}

}
