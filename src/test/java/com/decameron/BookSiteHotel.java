package com.decameron;

import org.testng.annotations.Test;

import com.decameron.pages.HomePage;
import com.decameron.pages.QuotePage;
import com.decameron.pages.SecurePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class BookSiteHotel {
	
	private WebDriver driver;
	private static final String URL = "https://www.decameron.com/es/co-inicio";
	private static final String MSG_ADULT_NUMBER_ERR = "This is an incorrect adult number";
	private static final String MSG_NO_QUOTE = "Validate quote dates";
	private static final String MSG_NO_HOTEL = "There isn't any hotel quote";
	
	@Test
	@Parameters({"departure-city", "arrival-hotel", "arrival-date", "departure-date", "adults-number",
		"first-passengers-name", "first-passengers-middlename", "first-passengers-lastname",
		"first-passengers-country","first-passengers-city","first-passengers-phone",
		"first-passengers-id-type", "first-passengers-id", "first-passengers-birth-date", "first-passengers-genre",
		"first-passengers-email"})
	public void bookAHotel(String depatureCity, String arrivalHotel, String arrivalDate, String departureDate, 
			int adultsNumber, String firtPassengersName, String firstPassengersMiddleName, String firstPassengersLastName,
			String firstPassengersCountry, String firstPassengersCity, String firstPassengersPhone,
			String firstPassengersIDType, String firstPassengersID, String firstPassengersBirthDate, String firstPassengersGenre,
			String firstPassengersEmail) {
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.fillArrivalAndDepartureBooking(depatureCity, arrivalHotel, arrivalDate, departureDate);
		Assert.assertTrue(homePage.validateMaxAdultsSelector(adultsNumber), MSG_ADULT_NUMBER_ERR);
		
		QuotePage quotePage = homePage.fillOccupationBooking(adultsNumber);
		Assert.assertFalse(quotePage.validateNoQuote(), MSG_NO_QUOTE);
		Assert.assertTrue(quotePage.validateHotelQuote(), MSG_NO_HOTEL);
		SecurePage securePage = quotePage.selectARoom();
		securePage.fillSecureForm(firtPassengersName, firstPassengersMiddleName, firstPassengersLastName,
				firstPassengersCountry, firstPassengersCity, firstPassengersPhone, firstPassengersIDType,
				firstPassengersID, firstPassengersBirthDate, firstPassengersGenre, firstPassengersEmail);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void beforeTest() {
		driver = new ChromeDriver();
		driver.get(URL);
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
		driver.quit();
	}

}
