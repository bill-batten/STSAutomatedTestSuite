package pageobjects;

import common.BasePage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static pageobjects.StepDefinitionActions.FilterType.AWARD;


public class StepDefinitionActions extends BasePage {

    //Search test data
    private static final String SUBSIDY_PURPOSE = "Infrastructure";
    private static final String SUBSIDY_SECTOR = "Construction";
    private static final String SUBSIDY_TYPE = "Equity";

    private static final String START_DATE_DAY_FROM = "01";
    private static final String START_DATE_MONTH_FROM = "01";
    private static final String START_DATE_YEAR_FROM = "2000";
    private static final String START_DATE_DAY_TO = "01";
    private static final String START_DATE_MONTH_TO = "01";
    private static final String START_DATE_YEAR_TO = "2020";


    //Scheme test data
    private static final String SC_NUMBER = "SC10128";
    private static final String SUBSIDY_SCHEME_NAME = "BB Test Scheme";
    private static final String PUBLIC_AUTHORITY = "TEST GA";

    private static final String START_DATE_FROM = "2000-01-01";
    private static final String START_DATE_TO = "2020-01-01";

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
                headerName = "Spending sector";
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

    private List<WebElement> getTableBodyRows() {
        WebElement simpleTable = driver.findElement(By.id("searchresult-table-body"));
        return simpleTable.findElements(By.tagName("tr"));
    }

    public List<WebElement> getFilterRows() {
        WebElement filterRows = driver.findElement(By.id("filter-accordion-div"));
        return filterRows.findElements(By.className("govuk-accordion__section"));
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

    public void userSelectsAwardPeriod(String awardPeriod) {
        String awardPeriodSelection = "";
        if (awardPeriod.equals("Yes")) {
            awardPeriodSelection = "_radio";
        } else {
            awardPeriodSelection = "-2";
        }
        driver.findElement(By.id("legalgrantingdate" + awardPeriodSelection)).click();
    }

    public void userInputDates() {
        driver.findElement(By.id("legal_granting_date_day")).sendKeys(START_DATE_DAY_FROM);
        driver.findElement(By.id("legal_granting_date_month")).sendKeys(START_DATE_MONTH_FROM);
        driver.findElement(By.id("legal_granting_date_year")).sendKeys(START_DATE_YEAR_FROM);
        driver.findElement(By.id("legal_granting_date_day1")).sendKeys(START_DATE_DAY_TO);
        driver.findElement(By.id("legal_granting_date_month1")).sendKeys(START_DATE_MONTH_TO);
        driver.findElement(By.id("legal_granting_date_year1")).sendKeys(START_DATE_YEAR_TO);
    }

    public void doResultsInTableMatchDates(String dateType) {
        List<WebElement> rows = getTableBodyRows();
        int numberOfResults = rows.size();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

        LocalDate fromDate = LocalDate.parse(schemeTestData.get(dateType + " From"));
        LocalDate toDate = LocalDate.parse(schemeTestData.get(dateType + " To"));



        boolean isWithinDateRange = false;
//        int columnIndex = getColumnIndex(schemeTestData.get(dateType));
        int columnIndex = getColumnIndex(dateType);
        for (WebElement row : rows) {
            if (numberOfResults > 0) {
                List<WebElement> stringDate = row.findElements(By.tagName("td"));
                LocalDate awardLegalGrantingDate = LocalDate.parse(stringDate.get(columnIndex).getText(), formatter);
                isWithinDateRange = awardLegalGrantingDate.isEqual(fromDate) || awardLegalGrantingDate.isEqual(toDate)
                        || awardLegalGrantingDate.isAfter(fromDate) && awardLegalGrantingDate.isBefore(toDate);
            }
            Assert.assertTrue(isWithinDateRange);
            System.out.println("Results in table do not match the dates provided.");
        }
    }

    public void userSelectsFiltersButton() {
        driver.findElement(By.id("filters_button")).click();
    }

    public boolean isAscendingAlphabetical(List<WebElement> resultsTableRows) {
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

    public void userSelectsAll(String selectAllType) {
        selectFilter(AWARD, selectAllType + " Select all");
    }

    public void hideFilters() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: block; -> display: none;
        Assert.assertEquals("display: none;",filtersStyle);
    }

    public void displayFilters() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: none -> display: block;
        Assert.assertEquals("display: block;",filtersStyle);
    }

    public void openAllFilters() {
        driver.findElement(By.xpath("//*[@id=\"accordion-default\"]/div[1]/button")).click();
    }

    public void verifyFilterState(String expandedOrClosed) {

        boolean expectedState = expandedOrClosed.equals("expanded");
        List<WebElement> rows = getFilterRows();
        for (int i = 1; i <= rows.size(); i++) {
            WebElement purposeFilterSection = driver.findElement(By.id("accordion-default-heading-" + i));
            assertFilterState(purposeFilterSection, expectedState);
        }
    }

    /**
     * Asserts that a filter section is in the expected state.
     *
     * @param filterSection the filter section WebElement
     * @param expectedState the expected state of the filter section
     */
    private void assertFilterState(WebElement filterSection, boolean expectedState) {
        String actualState = filterSection.getAttribute("aria-expanded");
        String message = String.format("Filter section '%s' expected to be %s, but was %s", filterSection.getAttribute("id"), expectedState ? "expanded" : "closed", actualState);
        Assert.assertEquals(expectedState, Boolean.parseBoolean(actualState), message);
    }

    public void doResultsInTableMatchFilter(String type, String filterType) {
        List<WebElement> results = getTableBodyRows();
        int columnIndex = getColumnIndex(filterType);
        for (WebElement rows : results) {
            List<WebElement> columnData = rows.findElements(By.tagName("td"));
             Assert.assertEquals(schemeTestData.get(filterType), columnData.get(columnIndex).getText());
        }
    }

    public void inputDates(FilterType valueOf, String filterType) {
        selectFilter(valueOf, filterType + " Day From");
        selectFilter(valueOf, filterType + " Month From");
        selectFilter(valueOf, filterType + " Year From");

        selectFilter(valueOf, filterType + " Day To");
        selectFilter(valueOf, filterType + " Month To");
        selectFilter(valueOf, filterType + " Year To");

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

        put("Start Date Day From", "filter-start-day-from");
        put("Start Date Month From", "filter-start-month-from");
        put("Start Date Year From", "filter-start-year-from");
        put("Start Date Day To", "filter-start-day-to");
        put("Start Date Month To", "filter-start-month-to");
        put("Start Date Year To", "filter-start-year-to");

        put("End Date Day From", "filter-end-day-from");
        put("End Date Month From", "filter-end-month-from");
        put("End Date Year From", "filter-end-year-from");
        put("End Date Day To", "filter-end-day-to");
        put("End Date Month To", "filter-end-month-to");
        put("End Date Year To", "filter-end-year-to");

        put("Budget", "filter-budget");
        put("Status", "filter-status");
        put("Ad Hoc", "filter-adhoc");
    }};

    public void selectFilter(FilterType type, String filter) {
        Map<String, String> filterIds;
        switch (type) {
            case AWARD:
                filterIds = AWARD_FILTER_INPUT_FIELD_IDS;
                break;
            case SCHEME:
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

        if(filterElement.getAttribute("class").equals("govuk-select")) {
            Select select = new Select(filterElement);
            select.selectByVisibleText(schemeTestData.get(filter));
        } else {
            filterElement.sendKeys(schemeTestData.get(filter));
        }

    }

    public void selectFilterHeader(FilterType type, String filter) {
        Map<String, String> filterIds;
        switch (type) {
            case AWARD:
                filterIds = AWARD_FILTER_IDS;
                break;
            case SCHEME:
                filterIds = SCHEME_FILTER_IDS;
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

    public static final Map<String, String> schemeTestData = new HashMap<String, String>() {{
        put("Subsidy Control (SC) Number","SC10128");
        put("Subsidy Scheme Name","BB Test Scheme");
        put("Public Authority","TEST GA");
        put("Start Date From","2000-01-01");
        put("Start Date Day From","01");
        put("Start Date Month From","01");
        put("Start Date Year From","2000");
        put("Start Date To","2020-01-01");
        put("Start Date Day To","01");
        put("Start Date Month To","01");
        put("Start Date Year To","2020");

        put("End Date From","2000-01-01");
        put("End Date Day From","01");
        put("End Date Month From","01");
        put("End Date Year From","2000");
        put("End Date To","2022-01-01");
        put("End Date Day To","01");
        put("End Date Month To","01");
        put("End Date Year To","2022");

        put("Budget From","100");
        put("Budget To","200");
        put("Status","Active");
        put("Ad Hoc","Yes");
    }};
}

