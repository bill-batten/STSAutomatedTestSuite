package common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hooks extends BasePage {

    @Before //Cucumber Before Hook
    public static void setupDriver() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3001");
    }

    @After // Cucumber After hook
    public static void quitDriver() throws Exception {
        driver.quit();
    }

}
