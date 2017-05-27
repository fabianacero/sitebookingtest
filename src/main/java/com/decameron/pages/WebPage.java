package com.decameron.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.decameron.utility.*;

public class WebPage {

	protected static int TIMEOUT = 3000;
	WebDriver driver;
	
    @FindBy(css="div[id=ui-datepicker-div]")
    private WebElement calendarDiv;
    
    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-title")
    private List<WebElement> datePickersTitles;

    @FindBy(how = How.CLASS_NAME, using = "ui-datepicker-next")
    private WebElement buttonNextCalendar;
	
	public WebPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Sets the indicated input value
	 * @param element the web element to be fill
	 * @param value the vale to fill in the input
	 */
	public void setInputValue(WebElement element, String value){
		element.sendKeys(value);
	}
	
	/**
	 * Finds a value into a web elements list. Then click the element
	 * @param webElementsList
	 * @param valueToFind
	 */
	public void selectListElement(List<WebElement> webElementsList, String valueToFind){
		
		for(WebElement element: webElementsList){
			if(element.getText().contains(valueToFind)){
				element.click();
				break;
			}
		}
		
	}
	
	/**
	 * Selects a normal selector with the indicated value
	 * @param selector
	 * @param adultsNumber
	 */
	public void setSelectValue(WebElement selector, int adultsNumber){
		new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(selector));
		//selector.click();
		(new Select(selector)).deselectByValue("3");
	}
	
    /**
     * Waits until an indicated element exists
     * @param element
     */
    public void waitElement(By element){
        WebElement elementToWaitFor = new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.invisibilityOf(elementToWaitFor));
    }
    
    /**
     * Waits until the loader element is invisible
     * @param loaderDiv
     */
    public void waitUntilLoaderFinish(WebElement loaderDiv){
		//new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(loaderDiv));
		new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.invisibilityOf(loaderDiv));
    }
    
    public void scrollTo(String x, String y){
    	JavascriptExecutor jsx = (JavascriptExecutor)driver;
    	jsx.executeScript("window.scrollBy(" + x + "," + y + ")", "");
    }
    
	/**
	 * 
	 * @param selector
	 * @param value
	 * @return
	 */
	protected Boolean validateMaxSelectorValue(WebElement selector, int selectedValue){
		
		int maxValue = 0;
		int currentValue = 0;
		
		new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(selector));
		
		for(WebElement option: selector.findElements(By.tagName("option"))){
			currentValue = Integer.parseInt(option.getText());
			if(maxValue < currentValue ){
				maxValue = currentValue; 
			}
		}
		
		if(selectedValue <= maxValue){
			return true;
		}
		
		return false;
	}
	   
    /**
     * Selects the indicated dates on the indicated datepickers
     * @param datePickers
     */
    public void selectDatePickers(DatePicker datePicker){
   
        datePicker.getElement().click();
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.visibilityOf(calendarDiv));
        List<WebElement> calendarTable = findRightDatePickerMonth(datePicker);
        
        for(WebElement cell: calendarTable){
        	new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(cell));
        	cell.click();
        }
    }
    
    /**
     * Search and select the right calendar month. Then return the list of table's elements.
     * @param dateValues
     * @return
     */
    private List<WebElement> findRightDatePickerMonth(DatePicker datePicker){

       By dayCell = By.xpath(".//table//td[(@data-handler='selectDay') and (@data-year='" + datePicker.getYear() + "') and (@data-month='" + datePicker.getMonth() + "')]/a[text()='" + datePicker.getDay() + "']");
        List<WebElement> calendarTable = calendarDiv.findElements(dayCell);
        
        if(calendarTable.size() > 0){            	
        	return calendarTable;
        }
        
    	buttonNextCalendar.click();
    	String selectedDateTitle = datePicker.getMonthName();
    	Boolean titleIsEquals = false;
    	
    	for(WebElement dateTitle: datePickersTitles){
    		if(selectedDateTitle.equals(dateTitle.getText())){
    			titleIsEquals = true;
    			break;
    		}
    	}

    	if(!titleIsEquals){
    		findRightDatePickerMonth(datePicker);
    	}
    	
    	calendarTable = calendarDiv.findElements(dayCell);
    	return calendarTable;
    }
}
