package stepdefinitions;

import Util.FilterUtils;
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
    public void theclicksUserClicksHomepageButton(String button) {
        stepDefinitionActions.clickHomepageButton(button);
    }

//    @And("the user selects yes")
//    public void selectYes() {
//        stepDefinitionActions.userSelectsYes();
//    }

//    @And("the user selects Continue")
//    public void selectContinue() throws InterruptedException {
//        stepDefinitionActions.userSelectsContinue();
//    }

//    @And("the user selects Show results")
//    public void selectShowResults() {
//        stepDefinitionActions.userSelectsShowResults();
//    }

    @And("the {string} results page should be displayed")
    public void searchPageShouldBeDisplayed(String page) throws InterruptedException {
        stepDefinitionActions.isSearchPageDisplayed(page);
    }

//    @Then("the user clicks new search")
//    public void iClickNewSearch() {
//        stepDefinitionActions.userSelectsNewSearch();
//    }
//
//    @And("the user selects No")
//    public void theUserSelectsNo() {
//        stepDefinitionActions.userSelectsNo();
//    }

    @And("the user selects a subsidy {string}")
    public void theUserSelectsASubsidyFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
    }

//    @And("the user selects Select all")
//    public void theUserSelectsSubsidyAwardSelectAll() {
//        stepDefinitionActions.userSelectsAll();
//    }

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

//    @Then("the results table should only display awards with the same spending sectors")
//    public void theResultsTableShouldOnlyDisplayAwardsWithTheSameSpendingSectors() {
//
//        WebElement simpleTable = driver.findElement(By.id("searchresult-table-body"));
//        List<WebElement> rows = simpleTable.findElements(By.tagName("tr"));
//
//        int columnIndex = stepDefinitionActions.getColumnIndex("Spending sector");
//
//        boolean isSpendingSector = true;
//        for (WebElement row : rows) {
//            List<WebElement> stringDate = row.findElements(By.tagName("td"));
//            if (!stringDate.get(columnIndex).getText().equals("Construction")) {
//                isSpendingSector = false;
//            }
//        }
//        Assert.assertTrue(isSpendingSector);
//    }

//    @When("the user selects filters button")
//    public void theUserSelectsFiltersButton() {
//        stepDefinitionActions.userSelectsFiltersButton();
//    }

    @Then("all the filters are hidden")
    public void allTheFiltersAreHidden() {
        stepDefinitionActions.hideFilters();
    }


    @Then("all the filters are displayed")
    public void allTheFiltersAreDisplayed() {
        stepDefinitionActions.displayFilters();
    }

    @When("the user selects open or close all filters button")
    public void theUserSelectsOpenAllButton() {
        stepDefinitionActions.toggleAllFilters();
    }

    /**
     * Verifies that all filters are expanded or closed in a web page.
     *
     * @param expandedOrClosed a string indicating whether filters should be expanded or closed
     */
    @Then("all filters are {string}")
    public void verifyFiltersState(String expandedOrClosed) {
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
        stepDefinitionActions.isPurposeFilterExpanded(filter);
    }

    @And("the user selects an option from the {string} filter")
    public void theUserSelectsAnOptionFromTheFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
    }

//    @And("the user selects Update results")
//    public void theUserSelectsUpdateResults() {
//        stepDefinitionActions.userSelectsUpdateResults();
//    }

    @When("the user select Export as {string} file")
    public void theUserSelectExportAsExcelFile(String exportFileType) {
        stepDefinitionActions.exportFile(exportFileType);
    }

    @When("the user select results per page {string}")
    public void theUserSelectResultsPerPage(String resultsPerPage) {
        stepDefinitionActions.selectResultsPerPage(resultsPerPage);
    }

    @Then("the table returns {string} results per page")
    public void theTableReturnsResultsPerPage(String resultsPerPage) {
        stepDefinitionActions.doResultsMatchResultsPerPage(resultsPerPage);
    }

    @And("the user selects next page")
    public void theUserSelectsNextPage() {
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


    //-----------------------------------------------------------------------------------------------------------------
    // BEGINNING OF ADMIN STEP DEFINITIONS
    //-----------------------------------------------------------------------------------------------------------------

    @Given("the user clicks {string} navigation link")
    public void theUserClicksPublicAuthoritiesNavigationLink(String navOption) {
        stepDefinitionActions.userSelectsOptionFromNavigationBar(navOption);
        stepDefinitionActions.isCorrectPageDisplayed(navOption + " search page");
    }

    @When("the user selects to add a new public authority")
    public void theUserSelectsAddANewPublicAuthority() throws InterruptedException, IOException {
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        stepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        stepDefinitionActions.userInputsDataIntoTextField("public authority name", "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
    }

    @When("the user selects {string} button")
    public void theUserSelectsAddANewPublicAuthorityButton(String buttonText) throws InterruptedException {
        stepDefinitionActions.userSelectsButtonByText(buttonText);
    }

    @Then("the {string} page should be displayed")
    public void thePublicAuthoritiesPageShouldBeDisplayed(String pageTitle) {
        stepDefinitionActions.isCorrectPageDisplayed(pageTitle);
    }

    @Given("the user logs in as {string}")
    public void theUserLogsInAsBEISAdmin(String user) {
        stepDefinitionActions.loginAsUser(user);
    }

    @And("the user inputs a valid {string} into text field {string}")
    public void theUserInputsAValidPublicAuthorityNameIntoTextField(String testData, String textFieldName) throws IOException {
        stepDefinitionActions.userInputsDataIntoTextField(testData, textFieldName);
    }

    @And("the user selects Save")
    public void theUserSelectsSave() throws InterruptedException {
        stepDefinitionActions.userSelectsSaveButton();
    }

    @And("the user selects {string} link text button")
    public void theUserSelectsChangeLinkTextButton(String linkText) {
        stepDefinitionActions.userSelectsLinkText(linkText);
    }

    @And("the user selects ID of newly created public authority")
    public void theUserSelectsIDOfNewlyCreatedPublicAuthority() {
        stepDefinitionActions.selectNewlyCreatedPublicAuthority();
    }

    @Then("the status of the public authority should be inactive")
    public void theStatusOfThePublicAuthorityShouldBeInactive() {
        stepDefinitionActions.checkPublicAuthorityStatus();
    }

    @And("the user creates a test user")
    public void theUserCreatesATestUser() throws IOException, InterruptedException {
        stepDefinitionActions.userSelectsOptionFromNavigationBar("Users");
        stepDefinitionActions.userSelectsButtonByText("Add a new user");
        stepDefinitionActions.userSelectsButtonByText("Public Authority Approver");
        stepDefinitionActions.userInputsDataIntoTextField("public authority name", "public authority name");
        stepDefinitionActions.userInputsDataIntoTextField("user email address", "Email address");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        stepDefinitionActions.userInputsDataIntoTextField("user first name", "First name");
        stepDefinitionActions.userInputsDataIntoTextField("user last name", "Last name");
        stepDefinitionActions.userInputsDataIntoTextField("user mobile number", "Mobile phone number");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        stepDefinitionActions.userSelectsButtonByText("Update profile");

    }

    @And("the user deactivates the public authority")
    public void theUserDeactivatesThePublicAuthority() throws InterruptedException {
        stepDefinitionActions.userSelectsOptionFromNavigationBar("Public Authorities");
        stepDefinitionActions.selectNewlyCreatedPublicAuthority();
        stepDefinitionActions.userSelectsButtonByText("Deactivate");
        if (stepDefinitionActions.doesTextExistOnPage("Remove all users")){
            stepDefinitionActions.userSelectsButtonByText("Remove all users");
        }
        stepDefinitionActions.userSelectsButtonByText("Yes, deactivate public authority");
        stepDefinitionActions.userSelectsButtonByText("View all public authorities");
    }

    @When("the user searches for PA by {string}")
    public void theUserSearchesBySearchText(String searchType) throws IOException {
        stepDefinitionActions.userInputsDataIntoSearchField(searchType);
    }

    @Then("the filtered table contains matching {string}")
    public void theFilteredTableContainsMatchingPublicAuthorityName(String searchText) throws IOException {
        Assert.assertTrue(stepDefinitionActions.doesFilteredTableContainSearchFilter(searchText));
    }

    @When("the user filters by {string}")
    public void theUserFiltersByInactive(String filterType) {
        stepDefinitionActions.setFilterSelect(filterType);
    }

    @Then("the filtered table should only contain {string} {string}")
    public void theFilteredTableShouldOnlyContainInactiveStatus(String tableValue, String columnHeader) {
        stepDefinitionActions.doesFilteredTableResultsMatchFilter(tableValue, columnHeader);
    }

    @When("the user adds an {string} granting authority")
    public void theUserAddsAnInvalidGrantingAuthority(String errorType) throws InterruptedException, IOException {
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        stepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        stepDefinitionActions.userInputsDataIntoTextField(errorType + " granting authority name", "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        if (!errorType.equals("invalid")){
            stepDefinitionActions.userSelectsButtonByText("Save");
        }
    }

    @Then("the user should be presented with a {string} error")
    public void theUserShouldBePresentedWithAPublicAuthorityAlreadyAddedError(String errorMessage) {
        Assert.assertTrue(stepDefinitionActions.doesTextExistOnPage(errorMessage));
    }

    @Given("the user logs in as {string} successfully")
    public void theUserLogsInAsBEISAdminSuccessfully(String user) {
        stepDefinitionActions.loginAsUser(user);
        stepDefinitionActions.isCorrectPageDisplayed(user + " Dashboard page");
    }
}