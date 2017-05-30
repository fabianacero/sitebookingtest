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
	private static final String MSG_NO_QUOTE = "There's not any hotel quote";
	
	@Test
	@Parameters({"departure-city", "arrival-hotel", "arrival-date", "departure-date", "adults-number",
		"first-passengers-name", "first-passengers-middlename", "first-passengers-lastname"})
	public void bookAHotel(String depatureCity, String arrivalHotel, String arrivalDate, String departureDate, 
			int adultsNumber, String firtPassengersName, String firstPassengersMiddleName, String firstPassengersLastName) {
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.fillArrivalAndDepartureBooking(depatureCity, arrivalHotel, arrivalDate, departureDate);
		Assert.assertTrue(homePage.validateMaxAdultsSelector(adultsNumber), MSG_ADULT_NUMBER_ERR);
		
		QuotePage quotePage = homePage.fillOccupationBooking(adultsNumber);
		Assert.assertTrue(quotePage.validateHotelQuote(), MSG_NO_QUOTE);
		SecurePage securePage = quotePage.selectARoom();
		securePage.fillSecureForm(firtPassengersName, firstPassengersMiddleName, firstPassengersLastName);
		
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
