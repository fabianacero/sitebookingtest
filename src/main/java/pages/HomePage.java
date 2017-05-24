package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class HomePage extends WebPage{

	@FindBy(how = How.ID, using = "tabHotel")
	WebElement tabHotel;
	
	@FindBy(how = How.ID, using = "origenCompHotel")
	WebElement inputDepartureCity;
	
	@FindBy(css = "div.ac_results > ul > li")
	List<WebElement> autoCompleteResults;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public void fillBooking(String departureDate){
		
		tabHotel.click();
		setInputValue(inputDepartureCity, departureDate);
		for(WebElement results: autoCompleteResults){
			System.out.println(results.getText());
		}
		
	}

}
