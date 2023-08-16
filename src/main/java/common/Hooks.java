package common;

import Util.DataContextManager;
import Util.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.File;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import pageobjects.StepDefinitionActions;

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

    @Before("@publicAuthorityData") //Before export file functionality
    public static void setupPublicAuthorityData() throws InterruptedException {
        if (!DataContextManager.isPublicAuthorityDataCreated()) {

//          NAVIGATE TO PUBLIC AUTHORITY PAGE
            StepDefinitionActions.userSelectsOptionFromNavigationBar("Public Authorities");
            StepDefinitionActions.isCorrectPageDisplayed("Public Authorities search page");

//          CREATE PUBLIC AUTHORITY
            StepDefinitionActions.userSelectsButtonByText("Add a new public authority");
            StepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
            String inputData = StepDefinitionActions.getValueForTextField("public authority name");
            StepDefinitionActions.userInputsDataIntoTextField(inputData, "public authority name");
            StepDefinitionActions.userSelectsButtonByText("Continue");
            StepDefinitionActions.userSelectsButtonByText("Save");
            DataContextManager.setPublicAuthorityDataCreated(true);
        }
    }

    @Before("@subsidySchemeData")
    public void setupSubsidySchemeData() {
        if (!DataContextManager.isSchemeDataCreated()) {
            // Code to create role data

            DataContextManager.setSchemeDataCreated(true);
        }
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

    @After("@granting-authority")
    public static void tearDownGrantingAuthorityData() {
        ScenarioContext.clearContext();
    }

    @After // Cucumber After hook
    public static void quitDriver(){
        driver.quit();
    }

}
