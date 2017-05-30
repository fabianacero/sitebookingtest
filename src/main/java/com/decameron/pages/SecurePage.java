package com.decameron.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SecurePage extends WebPage{

	@FindBy(how = How.ID, using = "primerNombrePax1")
	WebElement firstPassengerNameInput;
	
	@FindBy(how = How.ID, using = "segundoNombrePax1")
	WebElement firstPassengerMiddleNameInput;
	
	@FindBy(how = How.ID, using = "APELLIDOC")
	WebElement firstPassengerLastNameInput;
	
	@FindBy(how = How.ID, using = "pais_titular")
	WebElement firstPassengerCountrySelect;
	
	@FindBy(how = How.ID, using = "ciudad")
	WebElement firstPassengerCityInput;
	
	@FindBy(how = How.ID, using = "TELEFONOC")
	WebElement firstPassengerPhoneInput;
	
	@FindBy(how = How.ID, using = "TIPO_IDENC")
	WebElement firstPassengerIDTypeSelect;
	
	@FindBy(how = How.ID, using = "NUM_IDENC")
	WebElement firstPassengerIDNumberInput;
	
	@FindBy(how = How.ID, using = "FECHA_PASAJERO_A11")
	WebElement firstPassengerBirthDateDatePicker;
	
	@FindBy(how = How.ID, using = "GENERO_PASAJERO_A11")
	WebElement firstPassengerGenre;
	
	@FindBy(how = How.ID, using = "CORREO2")
	WebElement firstPassengerEmail;
	
	@FindBy(how = How.ID, using = "tcnombrea")
	WebElement firstPassengerCardName;
	
	@FindBy(how = How.ID, using = "numeroTarjetaCredito")
	WebElement firstPassengerCardNumber;
	
	@FindBy(how = How.ID, using = "mesTarjetaCredito")
	WebElement firstPassengerCardExpirationMonth;
	
	@FindBy(how = How.ID, using = "anyoTarjetaCredito")
	WebElement firstPassengerCardExpirationYear;
	
	@FindBy(how = How.ID, using = "codSeguridadTarjetaCredito")
	WebElement firstPassengerCardVerificationCode;
	
	@FindBy(how = How.ID, using = "tccuotasa")
	WebElement firstPassengerCuotesNumber;
	
	@FindBy(how = How.ID, using = "tcdira")
	WebElement firstPassengerBillingAdress;
	
	@FindBy(how = How.ID, using = "tcterminos")
	WebElement acceptTermsCheck;
			
	@FindBy(how = How.ID, using = "ENVIAR")
	WebElement submitButton;
	
	
	public SecurePage(WebDriver driver) {
		super(driver);
	}

	public void fillSecureForm(String firstPassengersName, String firstPassengersMiddleName, String firstPassengersLastName){
		setInputValue(firstPassengerNameInput, firstPassengersName);
		setInputValue(firstPassengerMiddleNameInput, firstPassengersMiddleName);
		setInputValue(firstPassengerLastNameInput, firstPassengersLastName);
	}
}
