package stepdefinitions;

import Util.FilterUtils;
import Util.ScenarioContext;
import common.BasePage;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.testng.Assert;
import pageobjects.StepDefinitionActions;
import io.cucumber.java.en.Then;

//import static Util.FilterUtils.*;

import static Util.FilterUtils.testData;
import static pageobjects.StepDefinitionActions.getValueFromPropertiesFile;


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


    @And("the {string} results page should be displayed")
    public void searchPageShouldBeDisplayed(String page) throws InterruptedException {
        stepDefinitionActions.isSearchPageDisplayed(page);
    }

    @And("the user selects a subsidy {string}")
    public void theUserSelectsASubsidyFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
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
        String searchData = testData.get(filterType);
        Assert.assertTrue(stepDefinitionActions.doResultsInTableMatchFilter(filterType, searchData));
    }

    @Then("the {string} filter is expanded")
    public void thePurposeFilterIsExpanded(String filter) {
        stepDefinitionActions.isPurposeFilterExpanded(filter);
    }

    @And("the user selects an option from the {string} filter")
    public void theUserSelectsAnOptionFromTheFilter(String filter) {
        stepDefinitionActions.userSelectsFilterOption(filter);
    }

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
    public void theUserSelectsAddANewPublicAuthority() throws InterruptedException{
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        StepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        String inputData = StepDefinitionActions.getValueForTextField("public authority name");
        stepDefinitionActions.userInputsDataIntoTextField(inputData, "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
    }

    @When("the user selects {string} button")
    public void theUserSelectsAddANewPublicAuthorityButton(String buttonText) throws InterruptedException {
        stepDefinitionActions.userSelectsButtonByText(buttonText);
    }

    @Then("the {string} page should be displayed")
    public void thePublicAuthoritiesPageShouldBeDisplayed(String pageTitle) {
        stepDefinitionActions.isCorrectPageDisplayed(pageTitle + " search page");
    }

    @Given("the user logs in as {string}")
    public void theUserLogsInAsBEISAdmin(String user) {
        stepDefinitionActions.loginAsUser(user);
    }

//    @And("the user inputs a valid {string} into text field {string}")
//    public void theUserInputsAValidPublicAuthorityNameIntoTextField(String testData, String textFieldName) throws IOException {
//        stepDefinitionActions.userInputsDataIntoTextField(testData, textFieldName);
//    }

    @And("the user selects Save")
    public void theUserSelectsSave() throws InterruptedException {
        stepDefinitionActions.userSelectsSaveButton();
    }

    @And("the user selects {string} link text button")
    public void theUserSelectsChangeLinkTextButton(String linkText) {
        stepDefinitionActions.userSelectsLinkText(linkText);
    }


    @Then("the status of the public authority should be inactive")
    public void theStatusOfThePublicAuthorityShouldBeInactive() {
        stepDefinitionActions.checkPublicAuthorityStatus();
    }


    //TODO - Could refactor this here to pass the testData.get() method instead of passing the string which then uses the same method elsewhere
    @And("the user creates a test user")
    public void theUserCreatesATestUser() throws IOException, InterruptedException {
        stepDefinitionActions.userSelectsOptionFromNavigationBar("Users");
        stepDefinitionActions.userSelectsButtonByText("Add a new user");
        stepDefinitionActions.userSelectsButtonByText("Public Authority Approver");

        //TODO - the PA is inactive - get the stored one from scenario context
        stepDefinitionActions.userInputsDataIntoTextField(ScenarioContext.getContext("public authority name"), "public authority name");
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
    public void theUserDeactivatesThePublicAuthority() throws InterruptedException, IOException {
        stepDefinitionActions.userSelectsOptionFromNavigationBar("Public Authorities");
        String scenarioPublicAuthority = ScenarioContext.getContext("public authority name");
        ScenarioContext.removeContext("public authority name");
        ScenarioContext.setContext("inactive public authority", scenarioPublicAuthority);
        stepDefinitionActions.userSelectsLinkText(scenarioPublicAuthority);
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
    public void theFilteredTableContainsMatchingPublicAuthorityName(String searchText){
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


    @Then("the user should be presented with a {string} error")
    public void theUserShouldBePresentedWithAPublicAuthorityAlreadyAddedError(String errorMessage) {
        Assert.assertTrue(stepDefinitionActions.doesTextExistOnPage(errorMessage));
    }

    @Given("the user logs in as {string} successfully")
    public void theUserLogsInAsBEISAdminSuccessfully(String user) {
        stepDefinitionActions.loginAsUser(user);
        stepDefinitionActions.isCorrectPageDisplayed(user + " Dashboard page");
    }

//    @And("the user inputs valid subsidy scheme data")
//    public void theUserInputsValidSubsidySchemeData() throws IOException {
//        stepDefinitionActions.userInputsDataIntoTextField("Public authority name",  "active public authority name");
//        stepDefinitionActions.userInputsDataIntoTextField("Subsidy scheme name", "Subsidy scheme name");
//    }

    @Then("the {string} success page should be displayed")
    public void thePublicAuthoritiesPageSuccessPageShouldBeDisplayed(String pageTitle) {
        stepDefinitionActions.isSuccessPageDisplayed(pageTitle);
    }

    @When("the user fills in the form with the following data:")
    public void theUserFillsInTheSubsidySchemeFormWithTheFollowingData(DataTable subsidySchemeDataTable){
        List<Map<String, String>> rows = subsidySchemeDataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String fieldName = row.get("Field");
            String value = row.get("Value");

            if (value.equals("regression-test-scheme-public-authority")){
                value = ScenarioContext.getContext("public authority name");
            }
//            String inputData = StepDefinitionActions.getValueForTextField(value);
            StepDefinitionActions.userInputsDataIntoTextField(value, fieldName);
        }


    }

    @And("the user selects the following checkboxes:")
    public void theUserSelectsTheFollowingCheckboxes(DataTable subsidySchemeCheckboxes) throws InterruptedException {
        List<Map<String, String>> rows = subsidySchemeCheckboxes.asMaps(String.class, String.class);

        for (Map<String, String> row : rows) {
            String value = row.get("Value");
            stepDefinitionActions.userSelectsButtonByText(value);
        }
    }

    @When("the user adds an invalid granting authority")
    public void theUserAddsAnInvalidGrantingAuthority() throws InterruptedException {
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        stepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        stepDefinitionActions.userInputsDataIntoTextField("", "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
    }

    @And("the user adds an inactive granting authority")
    public void theUserAddsAnInactiveGrantingAuthority() throws InterruptedException {
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        stepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        stepDefinitionActions.userInputsDataIntoTextField(ScenarioContext.getContext("inactive public authority"), "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        stepDefinitionActions.userSelectsButtonByText("Save");
    }

    @And("the user adds a duplicate granting authority")
    public void theUserAddsADuplicateGrantingAuthority() throws InterruptedException {
        stepDefinitionActions.userSelectsButtonByText("Add a new public authority");
        stepDefinitionActions.isCorrectPageDisplayed("Add Public Authority page");
        stepDefinitionActions.userInputsDataIntoTextField(ScenarioContext.getContext("public authority name"), "public authority name");
        stepDefinitionActions.userSelectsButtonByText("Continue");
        stepDefinitionActions.userSelectsButtonByText("Save");
    }

}