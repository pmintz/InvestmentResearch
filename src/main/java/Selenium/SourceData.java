package Selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static java.time.Duration.*;

public class SourceData extends Thread {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    static WebDriver driver;
    private String ticker;

    public void login() {
        ChromeOptions options = new ChromeOptions();
        FirefoxOptions ffOptions = new FirefoxOptions();
        EdgeOptions edgeOptions = new EdgeOptions();
        //options.addArguments("headless");
        //options.setImplicitWaitTimeout(ofSeconds(5));
        //ffOptions.setImplicitWaitTimeout(ofSeconds(5));
        edgeOptions.setImplicitWaitTimeout(ofSeconds(5));
        //ffOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        edgeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        /*DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);*/
        //driver = new ChromeDriver(options);
        //driver = new FirefoxDriver(ffOptions);
        driver = new EdgeDriver(edgeOptions);
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
            clickExport();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void clickExport(){
        try {
            System.out.println("Sleeping for 10 seconds...");
            Thread.sleep(10000);
            JavascriptExecutor js = (JavascriptExecutor) driver;


            //Launch the application
            //driver.get("https://www.browserstack.com/guide/selenium-scroll-tutorial");

            //Locating element by link text and store in variable "Element"
            //WebElement Element = driver.findElement(By.linkText("Try Selenium Testing For Free"));

            //WebElement webElement = driver.findElement(By.xpath("//button[@id='salKeyStatsPopoverExport']"));

            // Scrolling down the page till the element is found
            //js.executeScript("arguments[0].scrollIntoView();", webElement);

            //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            System.out.println("Sleeping for 10 seconds...");
            Thread.sleep(10000);
            //js.executeScript("window.scroll(0,1250)");
        //js.executeAsyncScript("window.scrollBy(0,document.body.scrollHeight)");
            //js.executeAsyncScript("alert('Appears to be scrolling that is the issue')");
             //((JavascriptExecutor) driver).executeScript ("window.alert('Appears to be scrolling that is the issue')");
            //Thread.sleep(5000);
            //js.executeScript("window.scroll(0,1250)","");
            //Thread.sleep(5000);
            /*new Actions(driver)
                    .scrollByAmount(0, 3000)
                    .perform();*/
            /*new Actions(driver)
                    .scrollByAmount(0, 3000)
                    .perform();*/

            //Long height=(Long) js.executeScript("return document.body.scrollHeight");
            //System.out.println(height);
            //Thread.sleep(1000);
            //driver.findElement(By.tagName("body")).sendKeys(Keys.END);
            WebElement body = driver.findElement(By.cssSelector("body"));
            WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(body);
            new Actions(driver)
                    .scrollFromOrigin(scrollOrigin, 0, 2000)
                    .perform();

            Thread.sleep(5000);

            WebElement exportButton = driver.findElement(By.xpath("//*[@id='salEqsvFinancialsPopoverExport']"));
            exportButton.click();
/*            new Actions(driver)
                    .scrollToElement(exportButton)
                    .perform();*/

            //driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);

            /*Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
            robot.keyRelease(KeyEvent.VK_PAGE_DOWN);*/


            //List<WebElement> webElements = driver.findElements(By.xpath("//*[@class='title-section']"));
            List<WebElement> webElements  = driver.findElements(By.xpath("//*[@class='title-section']"));
            //System.out.println(webElement.getText());
            System.out.println("size: " + webElements.size());

            //button aria-label="Export"
            //webElement.click();
        }catch (Exception e){
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
