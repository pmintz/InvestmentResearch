package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetSourceData {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    static WebDriver driver;

    public void login(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://research.morningstar.com/ic/authenticate?Name=Clifton%20Park");
        WebElement cardNum = driver.findElement(By.id("password"));
        cardNum.sendKeys("1000601559668");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        //closeBrowser();

    }



    public void enterTickerSymbol(String ticker){
        //class="mdc-search-field__input mds-search-field__input__rxp"
        //driver.findElement(By.id(""));
        //class="mds-navigation__item-text__rxp"
        WebElement searchTicker = driver.findElement(By.className("mdc-search-field__input"));
        searchTicker.sendKeys(ticker);
        searchTicker.sendKeys(Keys.RETURN);
    }

    public void clickHomeLink(){
        driver.findElement(By.id(""));
    }

    public void checkForResults(){
        //look for search-all_hit
    }

    public void retrieveDataFromResultsPage(){
        //copy all data java data structures
    }



    public void copySourceData(){


    }

    public void closeBrowser(){
        driver.quit();
    }

}
