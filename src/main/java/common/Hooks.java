package common;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import pageobjects.StepDefinitionActions;
import stepdefinitions.StepDefinitions;

public class Hooks extends BasePage {

    private static final String FOLDER_PATH = System.getProperty("user.dir") + "/SeleniumDownloadsFolder/";

    @Before("@public-portal") //Cucumber Before Hook
    public static void setupPublicDriver() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "geckodriver");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", FOLDER_PATH);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost:3001");
        // Test against staging
    }

    @Before("@admin-portal") //Cucumber Before Hook
    public static void setupAdminDriver() throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "geckodriver");
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", FOLDER_PATH);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.get("http://localhost:3000");
        Thread.sleep(2000);
//        driver.get("http://manageuksubsidies-stg.beis.gov.uk/");
//        driver.get("http://<username>:<password>manageuksubsidies.beis.gov.uk/");
    }

    @Before("@export-file") //Before export file functionality
    public static void setupExportFunctionality(){
        File tempDownloadsFolder = new File(FOLDER_PATH);
        if (tempDownloadsFolder.exists()) {
            tempDownloadsFolder.delete();
        }
        tempDownloadsFolder.mkdir();
    }

    @After("@export-file") //After export file functionality
    public static void tearDownExportFunctionality() {
        File tempDownloadsFolder = new File(FOLDER_PATH);
        if (tempDownloadsFolder.exists()) {
            File[] files = tempDownloadsFolder.listFiles();
            for (File file : files) {
                file.delete();
            }
            tempDownloadsFolder.delete();
        }
    }

    @After // Cucumber After hook
    public static void quitDriver() throws Exception {
        driver.quit();
    }

}
