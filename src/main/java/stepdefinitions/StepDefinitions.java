package stepdefinitions;

import common.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import common.BasePage;
import pageobjects.StepDefinitionActions;
import io.cucumber.java.en.Then;
import pageobjects.StepDefinitionActions.FilterType;

public class StepDefinitions extends BasePage {

    StepDefinitionActions stepDefinitionActions = new StepDefinitionActions();


    @Given("I navigate to the public portal website")
    public void iNavigateToThePublicPortalWebsite() {
        driver = new FirefoxDriver();
//        driver.get("https://searchforuksubsidies-dev.beis.gov.uk/");
        driver.get("http://localhost:3001");
    }

    @Given("the user is on the homepage")
    public void theUserIsOnTheHomepage() {
        stepDefinitionActions.isUserOnHomepage();
    }

    @When("the user clicks {string} button")
    public void theUserClicksHomepageButton(String button) {
        stepDefinitionActions.clickHomepageButton(button);
    }

    @And("the user selects yes")
    public void selectYes() {
        stepDefinitionActions.userSelectsYes();
    }

    @And("the user selects Continue")
    public void selectContinue() {
        stepDefinitionActions.userSelectsContinue();
    }

    @And("the user selects Show results")
    public void selectShowResults() {
        stepDefinitionActions.userSelectsShowResults();
    }

    @And("the search results page should be displayed")
    public void searchPageShouldBeDisplayed() {
        stepDefinitionActions.isSearchPageDisplayed();
    }

    @Then("the user clicks new search")
    public void iClickNewSearch() {
        stepDefinitionActions.userSelectsNewSearch();
    }

    @And("the user selects No")
    public void theUserSelectsNo() {
        stepDefinitionActions.userSelectsNo();
    }

    @And("the user selects a subsidy purpose")
    public void theUserSelectsASubsidyFilter() {
        stepDefinitionActions.userSelectsSubsidyPurpose();
    }

    @And("the user selects a spending sector")
    public void theUserSelectsASpendingSector() {
        stepDefinitionActions.userSelectsSubsidySector();
    }

    @And("the user selects a spending sector")
    public void theUserSelectsASubsidyType() {
        stepDefinitionActions.userSelectsSubsidyType();
    }

    @And("the results table should only display results with the same {string}")
    public void theResultsTableShouldOnlyDisplayResultsWithMatchingFilter(String filterType) {
        stepDefinitionActions.doesTableContainMatchingFilters(filterType);
    }

    @And("the user selects {string} Select all")
    public void theUserSelectsSubsidyPurposeSelectAll(String selectAllType) {
        driver.findElement(By.id(selectAllType + "-0")).click();
    }

    @And("the user selects {string} for award period")
    public void theUserSelectsYesForAwardPeriod(String awardPeriod) {
        String awardPeriodSelection = "";
        if (awardPeriod.equals("Yes")) {
            awardPeriodSelection = "_radio";
        } else {
            awardPeriodSelection = "-2";
        }
        driver.findElement(By.id("legalgrantingdate" + awardPeriodSelection)).click();
    }

    @And("the user inputs valid dates")
    public void theUserInputsValidDates() {
        driver.findElement(By.id("legal_granting_date_day")).sendKeys("01");
        driver.findElement(By.id("legal_granting_date_month")).sendKeys("01");
        driver.findElement(By.id("legal_granting_date_year")).sendKeys("2000");
        driver.findElement(By.id("legal_granting_date_day1")).sendKeys("01");
        driver.findElement(By.id("legal_granting_date_month1")).sendKeys("01");
        driver.findElement(By.id("legal_granting_date_year1")).sendKeys("2020");
    }

    @Then("the results table should only display awards within the dates provided")
    public void theResultsTableShouldOnlyDisplayAwardsWithinTheDatesProvided() {

        WebElement simpleTable = driver.findElement(By.id("searchresult-table-body"));
        List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));

        int numberOfResults = rows.size();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);
        LocalDate fromDate = LocalDate.of(2000, Month.JANUARY, 1);
        LocalDate toDate = LocalDate.of(2020, Month.JANUARY, 1);

        boolean isWithinDateRange = false;
        int columnIndex = stepDefinitionActions.getColumnIndex("Subsidy date");
        for (WebElement row : rows) {
            if (numberOfResults > 0) {
                List<WebElement> stringDate = row.findElements(By.tagName("td"));
                LocalDate awardLegalGrantingDate = LocalDate.parse(stringDate.get(columnIndex).getText(), formatter);
                if (awardLegalGrantingDate.isAfter(fromDate) && awardLegalGrantingDate.isBefore(toDate)) {
                    isWithinDateRange = true;
                }
            }
            Assert.assertTrue(isWithinDateRange);
        }
    }

    @Then("the results table should only display awards with the same spending sectors")
    public void theResultsTableShouldOnlyDisplayAwardsWithTheSameSpendingSectors() {

        WebElement simpleTable = driver.findElement(By.id("searchresult-table-body"));
        List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));

        int columnIndex = stepDefinitionActions.getColumnIndex("Spending sector");

        boolean isSpendingSector = true;
        for (WebElement row : rows) {
            List<WebElement> stringDate = row.findElements(By.tagName("td"));
            if (!stringDate.get(columnIndex).getText().equals("Construction")) {
                isSpendingSector = false;
            }
        }
        Assert.assertTrue(isSpendingSector);
    }

    @When("the user selects filters button")
    public void theUserSelectsFiltersButton() {
        driver.findElement(By.id("filters_button")).click();
    }

    @Then("all the filters are hidden")
    public void allTheFiltersAreHidden() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: block; -> display: none;
        Assert.assertEquals("display: none;",filtersStyle);
    }


    @Then("all the filters are displayed")
    public void allTheFiltersAreDisplayed() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: none -> display: block;
        Assert.assertEquals("display: block;",filtersStyle);
    }

    @When("the user selects {string} button")
    public void theUserSelectsOpenAllButton(String expandCollapseButton) {
        driver.findElement(By.xpath("//*[@id=\"accordion-default\"]/div[1]/button")).click();
    }

    /**
     * Verifies that all filters are expanded or closed in a web page.
     *
     * @param expandOrClose a string indicating whether filters should be expanded or closed
     */
    @Then("all filters are {string}")
    public void verifyFiltersState(String expandOrClose) {
        boolean expectedState = expandOrClose.equals("expanded");

        WebElement purposeFilterSection = driver.findElement(By.id("accordion-default-heading-1"));
        WebElement sectorFilterSection = driver.findElement(By.id("accordion-default-heading-2"));
        WebElement typeFilterSection = driver.findElement(By.id("accordion-default-heading-3"));

        assertFilterState(purposeFilterSection, expectedState);
        assertFilterState(sectorFilterSection, expectedState);
        assertFilterState(typeFilterSection, expectedState);
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

    @When("the user selects {string} {string} filter")
    public void theUserSelectsAwardPurposeFilter(String type, String filter) {
//        stepDefinitionActions.selectFilter(driver, FilterType.valueOf(type), filter);
    }

    @Then("the {string} filter is expanded")
    public void thePurposeFilterIsExpanded(String filter) {
        String id = "";
        switch(filter){
            case "Purpose":
                id = "accordion-default-heading-1";
                break;
            case "Sector":
                id = "accordion-default-heading-2";
                break;
            case "Type":
                id = "accordion-default-heading-3";
                break;
        }
        Assert.assertEquals("true", driver.findElement(By.id(id)).getAttribute("aria-expanded"));
    }

    @And("the user selects an option from the Purpose filter")
    public void theUserSelectsAnOptionFromThePurposeFilter() {
        driver.findElement(By.id("subsidyobjective-2")).click();
    }

    @And("the user selects Update results")
    public void theUserSelectsUpdateResults() {
        driver.findElement(By.id("update-results-button")).click();
    }

    @And("the user selects an option from the Sector filter")
    public void theUserSelectsAnOptionFromTheSectorFilter() {
        driver.findElement(By.id("spendingsector-construction")).click();
    }

    @And("the user selects an option from the Type filter")
    public void theUserSelectsAnOptionFromTheTypeFilter() {
        driver.findElement(By.id("subsidyinstrument-3")).click();
    }

    @When("the user select Export as {string} file")
    public void theUserSelectExportAsExcelFile(String exportFileType) {
        driver.findElement(By.id("export_" + exportFileType + "_button")).click();
    }

    @Then("the downloaded file is of type {string}")
    public void theDownloadedFileIsOfTypeExcel() {
    }

    @Then("the results table should display all awards")
    public void theResultsTableShouldDisplayAllAwards() {
        Assert.assertTrue(stepDefinitionActions.doesTableContainResults(driver));
    }


    @When("the user select results per page {string}")
    public void theUserSelectResultsPerPage(String resultsPerPage) {
        driver.findElement(By.id("results-per-page-" + resultsPerPage)).click();
        System.out.println("Got to here fine");
    }

    @Then("the table returns {string} results per page")
    public void theTableReturnsResultsPerPage(String resultsInTable) {
        Assert.assertEquals(Integer.parseInt(resultsInTable), stepDefinitionActions.numberOfResultsInTable(driver));
    }

    @And("the user selects next page")
    public void theUserSelectsNextPage() {
        driver.findElement(By.id("paginationlink_next_page")).click();
    }

    @When("the user clicks {string} name ordering arrows")
    public void theUserClicksBeneficiaryNameOrderingArrows(String columnName) {
        driver.findElement(By.id(columnName + "_updownarrow")).click();
    }

    @Then("the tables results are re-ordered by {string} column name")
    public void theTablesResultsAreReOrderedByColumnName(String columnName) {
        List<WebElement> resultsTableRows = stepDefinitionActions.retrieveResultsTableRows(driver);
        Assert.assertTrue(stepDefinitionActions.isAscendingAlphabetical(driver, resultsTableRows));
    }

    @Then("the tables results are re-ordered by legal granting date column name")
    public void theTablesResultsAreReOrderedByLegalGrantingDateColumnName() {
        List<WebElement> resultsTableRows = stepDefinitionActions.retrieveResultsTableRows(driver);
        Assert.assertTrue(stepDefinitionActions.isAscendingDateOrder(driver, resultsTableRows));

    }

    @And("the user inputs a valid {string} for {string} filter")
    public void theUserInputsAValidSchemeFilter(String filterString, String filterType) {
//        stepDefinitionActions.selectFilter(driver, FilterType.valueOf(filterString), filterType);
    }

    @And("the user selects {string} for subsidy {string}")
    public void theUserSelectsSelectAllForSubsidyPurpose(String ) {
    }

}