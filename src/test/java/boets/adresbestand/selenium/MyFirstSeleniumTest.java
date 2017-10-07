package boets.adresbestand.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Asus on 2/09/2017.
 */
public class MyFirstSeleniumTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\javadev\\tools\\chromedriver.exe");
        MyFirstSeleniumTest.testGoogleButton(createAndInitialseChromeDriver());
        MyFirstSeleniumTest.testGoogleSearchBox(createAndInitialseChromeDriver());

    }

    private static WebDriver createAndInitialseChromeDriver() {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.google.be");
        return webDriver;
    }
    private static void closeWebDriver(WebDriver driver) {
        driver.quit();
    }

    private static void testGoogleButton(WebDriver webDriver) {
        WebElement button = webDriver.findElement(By.name("btnK"));
        System.out.println(button.getLocation());
        button.submit();
        closeWebDriver(webDriver);
    }

    private static void testGoogleSearchBox(WebDriver webDriver) {
        WebElement searchBox = webDriver.findElement(By.name("q"));
        System.out.println(searchBox.getText());
        searchBox.sendKeys(Keys.chord(Keys.SHIFT,"packt publishing"));
        searchBox.submit();
        closeWebDriver(webDriver);
    }
}
