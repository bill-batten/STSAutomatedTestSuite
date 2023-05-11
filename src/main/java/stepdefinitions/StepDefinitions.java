package stepdefinitions;

import common.BasePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import pageobjects.StepDefinitionActions;
import io.cucumber.java.en.Then;


public class StepDefinitions extends BasePage {

    StepDefinitionActions stepDefinitionActions = new StepDefinitionActions();


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

    @And("the {string} results page should be displayed")
    public void searchPageShouldBeDisplayed(String page) throws InterruptedException {
        stepDefinitionActions.isSearchPageDisplayed(page);
    }

    @Then("the user clicks new search")
    public void iClickNewSearch() {
        stepDefinitionActions.userSelectsNewSearch();
    }

    @And("the user selects No")
    public void theUserSelectsNo() {
        stepDefinitionActions.userSelectsNo();
    }

    @And("the user selects a subsidy {string}")
    public void theUserSelectsASubsidyFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
    }

    @And("the user selects Select all")
    public void theUserSelectsSubsidyAwardSelectAll() {
        stepDefinitionActions.userSelectsAll();
    }

    @And("the user selects {string} for award period")
    public void theUserSelectsAwardPeriod(String awardPeriod) {
        stepDefinitionActions.userSelectsAwardPeriod(awardPeriod);
    }

    @And("the user inputs valid dates")
    public void theUserInputsValidDates() {
        stepDefinitionActions.userInputDates();
    }

    @Then("the results table should only display results within the {string} provided")
    public void theResultsTableShouldOnlyDisplayResultsWithinTheDatesProvided(String dateType) {
        stepDefinitionActions.doResultsInTableMatchDates(dateType);
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
//        driver.findElement(By.id("filters_button")).click();
        stepDefinitionActions.userSelectsFiltersButton();
    }

    @Then("all the filters are hidden")
    public void allTheFiltersAreHidden() {
//        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
//        // If the filters are hidden the form style will be changed from display: block; -> display: none;
//        Assert.assertEquals("display: none;",filtersStyle);
        stepDefinitionActions.hideFilters();
    }


    @Then("all the filters are displayed")
    public void allTheFiltersAreDisplayed() {
//        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
//        // If the filters are hidden the form style will be changed from display: none -> display: block;
//        Assert.assertEquals("display: block;",filtersStyle);
        stepDefinitionActions.displayFilters();
    }

    @When("the user selects open or close all filters button")
    public void theUserSelectsOpenAllButton() {
//        driver.findElement(By.xpath("//*[@id=\"accordion-default\"]/div[1]/button")).click();
        stepDefinitionActions.toggleAllFilters();
    }

//    @Then("all scheme filters are {string}")
//    public void allSchemeFiltersAreExpanded(String filtersState) {
////        stepDefinitionActions.areAllSchemeFiltersExpanded();
//        stepDefinitionActions.verifyFilterState(filtersState);
//    }

    /**
     * Verifies that all filters are expanded or closed in a web page.
     *
     * @param expandedOrClosed a string indicating whether filters should be expanded or closed
     */
    @Then("all filters are {string}")
    public void verifyFiltersState(String expandedOrClosed) {
//        boolean expectedState = expandOrClose.equals("expanded");
//
//        WebElement purposeFilterSection = driver.findElement(By.id("accordion-default-heading-1"));
//        WebElement sectorFilterSection = driver.findElement(By.id("accordion-default-heading-2"));
//        WebElement typeFilterSection = driver.findElement(By.id("accordion-default-heading-3"));
//
//        assertFilterState(purposeFilterSection, expectedState);
//        assertFilterState(sectorFilterSection, expectedState);
//        assertFilterState(typeFilterSection, expectedState);
        stepDefinitionActions.verifyFilterState(expandedOrClosed);
    }

    @When("the user selects {string} filter")
    public void theUserSelectsSubsidyControlSCNumberFilter(String filterName) {
        stepDefinitionActions.selectFilterHeader(filterName);
    }

    @And("the user inputs valid date ranges for {string} date filter")
    public void theUserInputsDateRangeFilterData(String filterType) {
        stepDefinitionActions.inputDates(filterType);
    }

    @And("the user inputs a valid data for {string} filter")
    public void theUserInputsFilterData(String filterType) {
        stepDefinitionActions.selectFilter(filterType);
    }

    @Then("the results table should only display results with the same {string}")
    public void theResultsTableShouldOnlyDisplayResultsWithMatchingFilters(String filterType) {
        stepDefinitionActions.doResultsInTableMatchFilter(filterType);
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

    @And("the user selects an option from the {string} filter")
    public void theUserSelectsAnOptionFromTheFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
    }

    @And("the user selects Update results")
    public void theUserSelectsUpdateResults() {
        driver.findElement(By.id("update-results-button")).click();
    }

    @When("the user select Export as {string} file")
    public void theUserSelectExportAsExcelFile(String exportFileType) {
        driver.findElement(By.id("export_" + exportFileType + "_button")).click();
    }

    @Then("the downloaded file is of type {string}")
    public void theDownloadedFileIsOfTypeExcel() {
    }

    @When("the user select results per page {string}")
    public void theUserSelectResultsPerPage(String resultsPerPage) {
//        driver.findElement(By.id("results-per-page-" + resultsPerPage)).click();
        stepDefinitionActions.selectResultsPerPage(resultsPerPage);
    }

    @Then("the table returns {string} results per page")
    public void theTableReturnsResultsPerPage(String resultsPerPage) {
//        Assert.assertEquals(Integer.parseInt(resultsInTable), stepDefinitionActions.numberOfResultsInTable(driver));
        stepDefinitionActions.doResultsMatchResultsPerPage(resultsPerPage);
    }

    @And("the user selects next page")
    public void theUserSelectsNextPage() {
//        driver.findElement(By.id("paginationlink_next_page")).click();
        stepDefinitionActions.userSelectsNextPage();
    }

    @When("the user clicks {string} ordering arrow by {string} order")
    public void theUserClicksBeneficiaryNameOrderingArrows(String columnName, String order) {
        stepDefinitionActions.orderTableByColumn(columnName, order);
    }

    @When("the user selects the feedback link")
    public void theUserSelectsTheFeedbackLink() {
        stepDefinitionActions.userSelectsFeedbackLink();
    }

    @Then("the feedback form is displayed")
    public void theFeedbackFormIsDisplayed() {
        stepDefinitionActions.isFeedbackFormDisplayed();
    }

    @Then("the results table should only display awards within the dates provided")
    public void theResultsTableShouldOnlyDisplayAwardsWithinTheDatesProvided() {
        stepDefinitionActions.doResultsInTableMatchDates("Subsidy Date");
    }

    @When("the user selects link text {string}")
    public void theUserSelectsLinkTextCookies(String linkText) {
        stepDefinitionActions.userSelectsLinkText(linkText);
    }

    @Then("the user is directed to historic URL")
    public void theUserIsDirectedToHistoricURL() throws InterruptedException {
        stepDefinitionActions.userIsDirectedToHistoricURL();
    }

    @Then("the user is on the cookies page")
    public void theUserIsOnTheCookiesPage() {
        stepDefinitionActions.isUserOnCookiesPage();
    }

    @And("the user selects to use cookies")
    public void theUserSelectsToUseCookies() {
        stepDefinitionActions.selectAllowCookies();
    }

    @And("the user selects to not use cookies")
    public void theUserSelectsToNotUseCookies() {stepDefinitionActions.selectDisallowCookies();
    }

    @And("selects save changes")
    public void selectsSaveChanges() {
        stepDefinitionActions.saveChanges();
    }

    @Then("the cookies are enabled")
    public void theCookiesAreEnabled() {
        stepDefinitionActions.areCookiesEnabled();
    }

    @Then("the cookies are disabled")
    public void theCookiesAreDisabled() {
        stepDefinitionActions.areCookiesEnabled();
    }

    @And("the user inputs a valid data ranges for {string} filter")
    public void theUserInputsAValidDataRangesForBudgetFilter(String range) {
        stepDefinitionActions.inputMonetaryRange(range);
    }


    @Then("the results table should only display results with the {string} range")
    public void theResultsTableShouldOnlyDisplayResultsWithTheBudgetRange(String budget) {
        stepDefinitionActions.areTableResultsWithinMonetaryRange(budget);
    }

    @Then("the tables results are ordered in {string} order by {string} column name")
    public void theTablesResultsAreOrderedInAscendingOrderByRecipientNameColumnName(String order, String columnName) {
        stepDefinitionActions.areTableResultsOrderedByColumnName(order, columnName);
    }

    @When("the user selects export as {string} file button")
    public void theUserSelectsExportAsExcelFileButton(String fileType) {
        stepDefinitionActions.selectDownloadFileButton(fileType);
    }

    @Then("the {string} file should be downloaded to the downloads folder")
    public void theExcelFileShouldBeDownloadedToTheDownloadsFolder(String fileType) throws IOException {
        stepDefinitionActions.openBrowserDownloadsFolder(fileType);
    }

    @When("the user clicks on recipient name link")
    public void theUserClicksOnRecipientNameLink() {
        stepDefinitionActions.userClicksRecipientNameLink();
    }

    @Then("the number of results inside the file should match the number of results on the page")
    public void theNumberOfResultsInsideTheFileShouldMatchTheNumberOfResultsOnThePage() throws InterruptedException {
        stepDefinitionActions.doPageResultsMatchFileResults();
    }

    @And("the user selects a subsidy search {string}")
    public void theUserSelectsASubsidySearchPurpose(String searchOption) {
        stepDefinitionActions.userSelectsSearchOption(searchOption);
    }
}