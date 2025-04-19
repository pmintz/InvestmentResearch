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

public class SourceData extends Thread {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    static WebDriver driver;
    private String ticker;

    public void login() {
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


    public void enterTickerSymbol() {
        WebElement searchTicker = driver.findElement(By.className("mdc-search-field__input"));
        searchTicker.sendKeys(ticker);
        searchTicker.sendKeys(Keys.RETURN);
    }

    public void clickUSSecuritiesLink(String ticker) {
        try {
            Thread.sleep(2000);
            WebElement usSecuritiesLink = driver.findElement(By.cssSelector("a[href='/search/us-securities?query=" + ticker + "']"));
            usSecuritiesLink.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkForResults() {
        clickUSSecuritiesLink(ticker);
        try {
            Thread.sleep(2000);
            driver.findElement(By.className("search-us-securities__hit"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void pageRefresh() {
        try {
            Thread.sleep(2000);
            driver.get("https://research.morningstar.com/home");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void retrieveDataFromResultsPage() {
        System.out.println("Results Found");

        try {
            Thread.sleep(2000);
            List<WebElement> webElementList =
                    driver.findElements(By.xpath(
        "//a[contains(@class,'mdc-security-module__name')]"
                    ));
            WebElement webElement = webElementList.get(0);
            webElement.click();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void copySourceDataToMasterCopy() {


    }

    public void closeBrowser() {
        driver.quit();
    }

    @Override
    public void run() {
        enterTickerSymbol();

        if (checkForResults()) {
            retrieveDataFromResultsPage();
            //pageRefresh();
        } else {
            System.out.println("No results");
            pageRefresh();
        }

        System.out.print("Enter ticker symbol and then press \"Enter\".  Type \"close\" to exit: ");

    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }


}
