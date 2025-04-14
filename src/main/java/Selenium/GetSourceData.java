package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetSourceData {

    //youtube
    //Java Testing with Selenium Course
    //System Design was HARD until I Learned these 30 Concepts

    WebDriver driver;

    public void login(){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://research.morningstar.com/ic/authenticate?Name=Clifton%20Park");
        WebElement cardNum = driver.findElement(By.id("password"));
        cardNum.sendKeys("1000601559668");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        closeBrowser();

    }

    public void closeBrowser(){
        driver.quit();
    }

}
