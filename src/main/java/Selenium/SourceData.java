package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.*;

public class SourceData {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    static WebDriver driver;

    public void login(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.setImplicitWaitTimeout(ofSeconds(5));
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(ofSeconds(4));
        driver.get("https://research.morningstar.com/ic/authenticate?Name=Clifton%20Park");
        WebElement cardNum = driver.findElement(By.id("password"));
        cardNum.sendKeys("1000601559668");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
    }



    public void enterTickerSymbol(String ticker){
        WebElement searchTicker = driver.findElement(By.className("mdc-search-field__input"));
        searchTicker.sendKeys(ticker);
        searchTicker.sendKeys(Keys.RETURN);
    }

    public void clickHomeLink(){
        driver.findElement(By.id(""));
    }

    public boolean clickUSSecuritiesLink(String ticker){

        // Wait until the new page is loaded
        /*WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("new-page"));*/

        try {
            //WebElement usSecuritiesLink = driver.findElement(By.xpath("//a[href=/search/us-securiteis?q]") );

            List<WebElement> links = driver.findElements(By.xpath("//a"));
            if(links == null){
                return false;
            }else{
                for(WebElement we : links){
                    String s = we.getDomAttribute("href");
                    System.out.println(s);
                }

                WebElement usSecuritiesLink = driver.findElement(By.cssSelector("a[href='/search/us-securities?query=" + ticker + "']"  ) );
                usSecuritiesLink.click();
                return true;
            }

            //WebElement usSecuritiesLink = driver.findElement(By.partialLinkText("U.S. Securities"));
            /*if (usSecuritiesLink != null) {
                usSecuritiesLink.click();
                WebElement errorClass = driver.findElement(By.className("error"));
                if (errorClass == null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }*/
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean checkForResults(){
        WebElement searchHitDiv = driver.findElement(By.className("search-all__hit"));
        return searchHitDiv != null;
    }

    public void retrieveDataFromResultsPage(){
        //
        System.out.println("Results Found");
    }



    public void copySourceDataToMasterCopy(){


    }

    public void closeBrowser(){
        driver.quit();
    }

}
