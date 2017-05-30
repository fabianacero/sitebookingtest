package com.decameron.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class QuotePage extends WebPage{

	@FindBy(how = How.ID, using = "backimagen")
	WebElement loaderImage;
	
	@FindBy(how = How.ID, using = "vlrLiquidacionM")
	WebElement quoteValueDisplay;
	
	@FindBy(how = How.ID, using = "blockrandom")
	WebElement frameQuoteResults;
	
	@FindBy(css = ".habitacionMultiple button")
	List<WebElement> quoteResults;
	
	@FindBy(how = How.ID, using = "actualizarHotel")
	WebElement updateHotelSelection;
	
	public QuotePage(WebDriver driver) {
		super(driver);
	}
	
	public Boolean validateHotelQuote(){
		waitUntilLoaderFinish(loaderImage);
		waitElement(quoteValueDisplay);
		return quoteValueDisplay.isDisplayed();
	}
	
	public SecurePage selectARoom(){
		
		scrollTo("0", "450");
		driver.switchTo().frame(frameQuoteResults);
		
		for(WebElement submitButton: quoteResults){
			submitButton.click();
			break;			
		}
		
		waitUntilLoaderFinish(loaderImage);
		driver.switchTo().frame(frameQuoteResults);
		updateHotelSelection.click();
		return PageFactory.initElements(driver, SecurePage.class);
	}

}
