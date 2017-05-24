package com.decameron;

import org.testng.annotations.Test;

import com.decameron.pages.HomePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;

public class BookSiteHotel {
	
	private WebDriver driver;
	private static final String URL = "https://www.decameron.com/es/co-inicio";
	
	@Test
	@Parameters({"departure-city", "arrival-hotel", "arrival-date", "departure-date", "adults-number"})
	public void bookAHotel(String depatureCity, String arrivalHotel, String arrivalDate, String departureDate, 
			int adultsNumber) {
		
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		homePage.fillBooking(depatureCity, arrivalHotel, arrivalDate, departureDate, adultsNumber);
		try {
			Thread.sleep(30000);
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
