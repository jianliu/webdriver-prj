package sample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: liu
 * Date: 14-6-23
 * Time: 上午9:47
 * To change this template use File | Settings | File Templates.
 */
public class WebDriverWrapper {


    public static WebDriver createDriver(String driveType) {
        driveType = driveType.toLowerCase();
        WebDriver driver = null;
        if (driveType.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (driveType.contains("firefox")) {
            System.setProperty("webdriver.firefox.bin", "C:/Program Files (x86)/Mozilla Firefox/firefox.exe");
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile("webdriver");
            driver = new FirefoxDriver(profile);
        } else if (driveType.contains("htmlunit")) {
            driver = new HtmlUnitDriver();
        }
        return driver;
    }

    public static String get51(WebDriver driver, String url) {
        driver.get("http://www.baidu.com");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement euser = driver.findElement(By.id("passport_51_user"));
        euser.sendKeys("lj3331");
        WebElement epwd = driver.findElement(By.id("passport_51_password"));
        if (epwd.isDisplayed())
            epwd.sendKeys("yjwvfp");
        WebElement esub = driver.findElement(By.id("btn_login"));
        esub.click();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

//        Selenium selenium = new WebDriverBackedSelenium(driver,driver.getCurrentUrl());
//        return selenium.getBodyText();
        return driver.getPageSource();
    }



    public static void main(String[] args) {
        String url = "http://www.51.com/";
        String html = WebDriverWrapper.get51(WebDriverWrapper.createDriver("firefox"), url);
        if (html.contains("lj3331") || html.contains("放羊的星星"))
            System.out.println(html);


    }


}
