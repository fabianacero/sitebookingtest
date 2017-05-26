package com.decameron.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.decameron.utility.DatePicker;

public class HomePage extends WebPage{

	@FindBy(how = How.ID, using = "tabHotel")
	WebElement tabHotel;
	
	@FindBy(how = How.ID, using = "origenCompHotel")
	WebElement inputDepartureCity;
	
	@FindBy(css = "div.ac_results > ul > li")
	List<WebElement> autoCompleteResults;
	
	@FindBy(how = How.CSS, using = "button[data-id='hotelCompHotel1']")
	WebElement selectorHotel;
	
	@FindBy(css="div.bootstrap-select.open > div.dropdown-menu.open span.text")
	List<WebElement> selectorHotelList;
	
	@FindBy(how = How.ID, using = "entradaCompHotel1")
	WebElement arrivalDatePicker;
	
	@FindBy(how = How.ID, using = "salidaCompHotel1")
	WebElement departureDatePicker;
	
	@FindBy(how = How.ID, using = "selectHotelHab1Adultos")
	WebElement selectorAdults;
	
	@FindBy(how = How.CSS, using = "button[id='buscarHotel']")
	WebElement submitButton;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public Boolean validateMaxAdultsSelector(int adultsNumber){
		return validateMaxSelectorValue(selectorAdults, adultsNumber);
	}
	
	public void fillArrivalAndDepartureBooking(String departureCity, String arrivalHotel, String arrivalDate, String departureDate){
		
		tabHotel.click();
		setInputValue(inputDepartureCity, departureCity);
		selectListElement(autoCompleteResults, departureCity);
		//new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(contentCookiesPolicy));	
		selectorHotel.click();
		selectListElement(selectorHotelList, arrivalHotel);
		
		arrivalDatePicker.click();
		selectDatePickers(new DatePicker(arrivalDatePicker, arrivalDate));
		departureDatePicker.click();
		selectDatePickers(new DatePicker(departureDatePicker, departureDate));		
		
	}
	
	public void fillOccupationBooking(int adultsNumber){
		
		selectorAdults.click();
		//submitButton.click();
	}
	
	public void findRoomAccomodation(){
		
	}

}
