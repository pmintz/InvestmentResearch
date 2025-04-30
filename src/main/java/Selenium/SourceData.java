package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

import java.util.List;

import static ExcelActions.ExcelRowCopy.copyData;
import static java.time.Duration.*;

public class SourceData extends Thread {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    static WebDriver driver;
    private String ticker;
    public void login() {
        EdgeOptions edgeOptions = new EdgeOptions();
        //options.addArguments("headless");
        edgeOptions.setImplicitWaitTimeout(ofSeconds(5));
        driver = new EdgeDriver(edgeOptions);
        driver.manage().window().maximize();
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

    public void downloadAllFinancialStatements() {
        System.out.println("Results Found");

        try {
            Thread.sleep(2000);
            List<WebElement> webElementList =
                    driver.findElements(By.xpath(
                            "//a[contains(@class,'mdc-security-module__name')]"
                    ));
            WebElement webElement = webElementList.get(0);
            webElement.click();
            scrollToFinancials();
            //the first click on export gets income statement
            clickExport();
            clickBalanceSheetButton();
            clickExport();
            clickCashFlowButton();
            clickExport();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void scrollToFinancials() {
        try {
            Thread.sleep(5000);
            WebElement body = driver.findElement(By.cssSelector("body"));
            WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(body);
            new Actions(driver)
                    .scrollFromOrigin(scrollOrigin, 0, 2000)
                    .perform();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void clickExport() {
        try {
            Thread.sleep(5000);
            WebElement exportButton = driver.findElement(By.xpath("//*[@id='salEqsvFinancialsPopoverExport']"));
            exportButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clickBalanceSheetButton() {
        try {
            Thread.sleep(5000);

            WebElement balanceSheetButton = driver.findElement(By.xpath("//*[@id='balanceSheet']"));
            balanceSheetButton.click();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void clickCashFlowButton() {
        try {
            Thread.sleep(5000);

            WebElement cashFlowButton = driver.findElement(By.xpath("//*[@id='cashFlow']"));
            cashFlowButton.click();
        } catch (Exception e) {
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

        try {
            enterTickerSymbol();

            if (checkForResults()) {
                downloadAllFinancialStatements();
                copyData();
                //pageRefresh();
            } else {
                System.out.println("No results");
                pageRefresh();
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("There has been an error");
            System.out.println("Closing Program");
            System.exit(0);
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
