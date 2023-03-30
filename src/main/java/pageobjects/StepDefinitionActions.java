package pageobjects;

import common.BasePage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.thucydides.core.annotations.DefaultUrl;
//import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static pageobjects.StepDefinitionActions.FilterType.AWARD;


public class StepDefinitionActions extends BasePage {
//public class StepDefinitionActions extends ScenarioSteps {


//    WebDriver driver;

    @FindBy(xpath = "/html/head/title")
    private WebElement title;

    //Search test data

    private static final String SUBSIDY_PURPOSE = "Infrastructure";
    private static final String SUBSIDY_SECTOR = "Construction";
    private static final String SUBSIDY_TYPE = "Equity";


    //Scheme test data
    private static final String SC_NUMBER = "SC10128";
    private static final String SUBSIDY_SCHEME_NAME = "BB Test Scheme";
    private static final String PUBLIC_AUTHORITY = "TEST GA";
    private static final String START_DATE_FROM = "01-01-2000";
    private static final String START_DATE_TO = "01-02-2000";
    private static final String END_DATE_FROM = "01-01-2020";
    private static final String END_DATE_TO = "01-02-2020";
    private static final String BUDGET_FROM = "0";
    private static final String BUDGET_TO = "100";
    private static final String STATUS = "Active";
    private static final String ADHOC = "Yes";


    public void clickHomepageButton(String button) {
        driver.findElement(By.id("homepage_button_" + button.toLowerCase(Locale.ROOT).replaceAll(" ", "_"))).click();
    }

    public void userSelectsYes() {
        driver.findElement(By.id("beneficiary_name_radio_button")).click();

    }

    public void userSelectsContinue() {
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/form/button")).click();
    }

    public void userSelectsShowResults() {
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/form/fieldset/button")).click();
    }

    public void isSearchPageDisplayed() {
        String expectedTitle = "GOV.UK - Public user search result page";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    public void userSelectsNewSearch() {
        driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div[1]/form/div/button")).click();
    }

    public void isUserOnHomepage() {
        String expectedPageTitle = "GOV.UK - Public user search page";
        String actualPageTitle = driver.getTitle();
        Assert.assertEquals(actualPageTitle, expectedPageTitle);
    }

    public void userSelectsNo() {
        driver.findElement(By.id("beneficiaryname-2")).click();
    }

    //---------------------------------------//

    public void userSelectsSubsidyPurpose() {
        selectFilter(AWARD, SUBSIDY_PURPOSE);
    }

    public void userSelectsSubsidySector() {
        selectFilter(AWARD, SUBSIDY_SECTOR);
    }
    public void userSelectsSubsidyType() {
        selectFilter(AWARD, SUBSIDY_TYPE);
    }

    public String getSubsidyAwardFilterType(String filterType){
        String filterTestCase = "";
        switch (filterType) {
            case "Purpose":
                filterTestCase = SUBSIDY_PURPOSE;
                break;
            case "Sector":
                filterTestCase = SUBSIDY_SECTOR;
                break;
            case "Type":
                filterTestCase = SUBSIDY_TYPE;
                break;
        }
        return filterTestCase;
    }

    public void doesTableContainMatchingFilters(String filterType) {
        List<WebElement> rows = getTableBodyRows();

        String headerName = "";
        String filterTestCase = "";
        switch (filterType) {
            case "Purpose":
                headerName = "Subsidy purpose";
                filterTestCase = SUBSIDY_PURPOSE;
                break;
            case "Sector":
                headerName = "Subsidy sector";
                filterTestCase = SUBSIDY_SECTOR;
                break;
            case "Type":
                headerName = "Subsidy type";
                filterTestCase = SUBSIDY_TYPE;
                break;
        }

        int columnIndex = getColumnIndex(headerName);
        boolean isMatchingFilter = true;
        for (WebElement row : rows) {
            List<WebElement> stringDate = row.findElements(By.tagName("td"));
            if (!stringDate.get(columnIndex).getText().equals(filterTestCase)) {
                isMatchingFilter = false;
            }
        }
        Assert.assertTrue(isMatchingFilter);
    }


    //---------------------------------------//

    private List<WebElement> getTableBodyRows() {
        WebElement simpleTable = driver.findElement(By.id("searchresult-table-body"));
        return simpleTable.findElements(By.tagName("tr"));
    }

    public int getColumnIndex(String headerName) {

        WebElement resultsTable = driver.findElement(By.id("searchresult-table"));
        List<WebElement> headerCells = resultsTable.findElement(By.tagName("thead"))
                .findElement(By.tagName("tr"))
                .findElements(By.tagName("th"));
        for (int i = 0; i < headerCells.size(); i++) {
            if (headerCells.get(i).getText().equals(headerName)) {
                return i;
            }
        }
        return -1;
    }

    public boolean doesTableContainResults(WebDriver driver) {
        return retrieveResultsTableRows(driver).size() > 0;
    }

    public int numberOfResultsInTable(WebDriver driver){
        return retrieveResultsTableRows(driver).size();
    }

    public List<WebElement> retrieveResultsTableRows(WebDriver driver) {
        WebElement resultsTable = driver.findElement(By.id("searchresult-table-body"));
        return resultsTable.findElements(By.tagName("tr"));
    }

    public boolean isAscendingAlphabetical(WebDriver driver, List<WebElement> resultsTableRows) {
        boolean isInAlphabeticalOrder = true;

        // Loop through the list of Web Elements
        for (int i = 0; i < resultsTableRows.size() - 1; i++) {

            // Get the text of the current element and the next element in the list
            String currentElementText = resultsTableRows.get(i).getText();
            String nextElementText = resultsTableRows.get(i + 1).getText();

            // Convert the text to lowercase for case-insensitive comparison
            currentElementText = currentElementText.toLowerCase();
            nextElementText = nextElementText.toLowerCase();

            // Check if the current element is greater than the next element in alphabetical order,
            // allowing numbers to come before letters
            if (Character.isDigit(currentElementText.charAt(0)) && !Character.isDigit(nextElementText.charAt(0))) {
                // If the current element is a number and the next element is a letter, they are out of order
                isInAlphabeticalOrder = true;
                break;
            } else if (currentElementText.compareTo(nextElementText) > 0) {
                // If the current element is greater than the next element in alphabetical order,
                // and they are both either numbers or letters, they are out of order
                isInAlphabeticalOrder = false;
                break;
            }
        }

        // Finally, check the value of the flag to see if the list is in alphabetical order
        if (isInAlphabeticalOrder) {
            System.out.println("The list is in alphabetical order.");
            return true;
        } else {
            System.out.println("The list is not in alphabetical order.");
            return false;
        }
    }

    public boolean isAscendingDateOrder(WebDriver driver, List<WebElement> resultsTableRows) {
        if (resultsTableRows.size() <= 1) {
            // If the list has 1 or 0 elements, it is considered ordered
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date previousDate = null;
        for (WebElement element : resultsTableRows) {
            List<WebElement> elementChildren = element.findElements(By.xpath("./*"));
            if (elementChildren.size() >= 5) {
                String dateText = elementChildren.get(4).getText();
                try {
                    Date currentDate = dateFormat.parse(dateText);
                    if (previousDate != null && previousDate.compareTo(currentDate) > 0) {
                        // If the previous date is after the current date, the list is not ordered
                        return false;
                    }
                    previousDate = currentDate;
                } catch (ParseException e) {
                    // If a date cannot be parsed, skip it
                }
            }
        }
        // If all dates are in order, the list is considered ordered
        return true;
    }


    public enum FilterType {
        AWARD, SCHEME
    }

    public static final Map<String, String> AWARD_FILTER_IDS = new HashMap<String, String>() {{
        put("Purpose", "accordion-default-heading-1");
        put("Sector", "accordion-default-heading-2");
        put("Type", "accordion-default-heading-3");

    }};

    public static final Map<String, String> AWARD_FILTER_INPUT_FIELD_IDS = new HashMap<String, String>() {{
        //Purpose
        put("Purpose Select all", "subsidyobjective-0");
        put("Culture or Heritage", "subsidyobjective-1");
        put("Employment", "subsidyobjective-2");
        put("Energy efficiency", "subsidyobjective-3");
        put("Environmental protection", "subsidyobjective-4");
        put("Infrastructure", "subsidyobjective-5");
        put("Regional development", "subsidyobjective-5");
        put("Rescue aid", "subsidyobjective-6");
        put("Research and development", "subsidyobjective-7");
        put("Services of Public Economic Interest", "subsidyobjective-8");
        put("SME (Small/Medium-sized enterprise) support", "subsidyobjective-9");
        put("Training", "subsidyobjective-10");
        put("Purpose Other", "subsidyobjective-11");

        //Sector
        put("Sector Select all", "spendingsector-0");
        put("Accommodation and food service activities", "spendingsector-accommodation");
        put("Activities of extraterritorial organisations and bodies", "spendingsector-activities-of-extraterritorial");
        put("Activities of households as employers; undifferentiated goods- and services-producing activities of households for own use", "spendingsector-undifferentiated-goods");
        put("Administrative and support service activities", "spendingsector-administrative");
        put("Agriculture, forestry and fishing", "spendingsector-agriculture-forestry-and-fishing");
        put("Arts, entertainment and recreation", "spendingsector-arts-entertainment");
        put("Construction", "spendingsector-construction");
        put("Education", "spendingsector-education");
        put("Electricity, gas, steam and air conditioning supply", "spendingsector-Electricity-gas-steam-and-air-conditioning-supply");
        put("Financial and insurance activities", "spendingsector-financial-and-insurance-activities");
        put("Human health and social work activities", "spendingsector-human-health");
        put("Information and communication", "spendingsector-information-and-communication");
        put("Manufacturing", "spendingsector-Manufacturing");
        put("Mining and quarrying", "spendingsector-mining-and-quarrying");
        put("Other service activities", "spendingsector-Other-service-activities");
        put("Professional, scientific and technical activities", "spendingsector-professional");
        put("Public administration and defence; compulsory social security", "spendingsector-public-administration");
        put("Real estate activities", "spendingsector-real-estate-activities");
        put("Transportation and storage", "spendingsector-transportation-and-storage");
        put("Water supply; sewerage, waste management and remediation activities", "spendingsector-water-supply");
        put("Wholesale and retail trade; repair of motor vehicles and motorcycles", "spendingsector-wholesale-and-retail-trade");

        //Type
        put("Type Select all", "subsidyinstrument-0");
        put("Direct Grant", "subsidyinstrument");
        put("Equity", "subsidyinstrument-1");
        put("Guarantee", "subsidyinstrument-2");
        put("Loan", "subsidyinstrument-3");
        put("Provision of goods or services below market prices", "subsidyinstrument-4");
        put("Purchase of goods or services above market prices", "subsidyinstrument-5");
        put("Tax measures (tax credit, or tax/duty exemption)", "subsidyinstrument-6");
        put("Type Other", "subsidyinstrument-7");

    }};

    public static final Map<String, String> SCHEME_FILTER_IDS = new HashMap<String, String>() {{
        put("Subsidy Control (SC) Number", "accordion-default-heading-1");
        put("Subsidy Scheme Name", "accordion-default-heading-2");
        put("Public Authority", "accordion-default-heading-3");
        put("Start Date", "accordion-default-heading-4");
        put("End Date", "accordion-default-heading-5");
        put("Budget", "accordion-default-heading-6");
        put("Status", "accordion-default-heading-7");
        put("Ad Hoc", "accordion-default-heading-8");
    }};

    public static final Map<String, String> SCHEME_FILTER_INPUT_FIELD_IDS = new HashMap<String, String>() {{
        put("Subsidy Control (SC) Number", "filter-scnumber");
        put("Subsidy Scheme Name", "filter-name");
        put("Public Authority", "filter-ga");
        put("Start Date Day From", "filter-start-month-from");
        put("End Date", "accordion-default-heading-5");
        put("Budget", "accordion-default-heading-6");
        put("Status", "accordion-default-heading-7");
        put("Ad Hoc", "accordion-default-heading-8");
    }};

    public void selectFilter(FilterType type, String filter) {
        Map<String, String> filterIds;
        Map<String, String> filterInputFieldIds;
        switch (type) {
            case AWARD:
//                filterIds = AWARD_FILTER_IDS;
                filterIds = AWARD_FILTER_INPUT_FIELD_IDS;
                break;
            case SCHEME:
//                filterIds = SCHEME_FILTER_IDS;
                filterIds = SCHEME_FILTER_INPUT_FIELD_IDS;
                break;
            default:
                throw new IllegalArgumentException("Unknown filter type: " + type);
        }

        String id = filterIds.get(filter);
        if (id == null) {
            throw new IllegalArgumentException("Unknown filter: " + filter);
        }

        WebElement filterElement = driver.findElement(By.id(id));
        filterElement.click();
    }

    public void selectFilterHeader(FilterType type, String filter) {
        Map<String, String> filterIds;
        Map<String, String> filterInputFieldIds;
        switch (type) {
            case AWARD:
                filterIds = AWARD_FILTER_IDS;
//                filterInputFieldIds = AWARD_FILTER_INPUT_FIELD_IDS;
                break;
            case SCHEME:
                filterIds = SCHEME_FILTER_IDS;
//                filterInputFieldIds = SCHEME_FILTER_INPUT_FIELD_IDS;
                break;
            default:
                throw new IllegalArgumentException("Unknown filter type: " + type);
        }

        String id = filterIds.get(filter);
        if (id == null) {
            throw new IllegalArgumentException("Unknown filter: " + filter);
        }

        WebElement filterElement = driver.findElement(By.id(id));
        filterElement.click();
    }
}

