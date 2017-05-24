package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebPage {

	WebDriver driver;
	
	public WebPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void setInputValue(WebElement element, String value){
		element.sendKeys(value);
	}
}
