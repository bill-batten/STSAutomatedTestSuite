package pageobjects;

import au.com.bytecode.opencsv.CSVReader;
import com.google.gson.Gson;
import common.BasePage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static Util.FilterUtils.*;


public class StepDefinitionActions extends BasePage {

    private static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

    private static final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");

    public void clickHomepageButton(String button) {
        driver.findElement(By.id("homepage_button_" + button.toLowerCase(Locale.ROOT).replaceAll("[ /]", "_"))).click();
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

    public void isSearchPageDisplayed(String page) throws InterruptedException {
        Thread.sleep(1000);
        String expectedTitle = "";
        if (page.equals("homepage")) {
            expectedTitle = "GOV.UK - Public user search page";
        } else {
            expectedTitle = "GOV.UK - Public user " + page + " result page";
        }
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        Thread.sleep(1000);
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

    public void userSelectsFilterOption(String filter) {
        selectFilter(testData.get(filter));
    }

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
            if (headerCells.get(i).getText().toLowerCase(Locale.ROOT).contains(headerName.toLowerCase(Locale.ROOT))) {
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
        driver.findElement(By.id("legal_granting_date_day")).sendKeys(testData.get("Start Date Day From"));
        driver.findElement(By.id("legal_granting_date_month")).sendKeys(testData.get("Start Date Month From"));
        driver.findElement(By.id("legal_granting_date_year")).sendKeys(testData.get("Start Date Year From"));
        driver.findElement(By.id("legal_granting_date_day1")).sendKeys(testData.get("Start Date Day To"));
        driver.findElement(By.id("legal_granting_date_month1")).sendKeys(testData.get("Start Date Month To"));
        driver.findElement(By.id("legal_granting_date_year1")).sendKeys(testData.get("Start Date Year To"));
    }

    public void doResultsInTableMatchDates(String dateType) {
        List<WebElement> rows = getTableBodyRows();
        int numberOfResults = rows.size();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

        LocalDate fromDate = LocalDate.parse(testData.get(dateType + " From"));
        LocalDate toDate = LocalDate.parse(testData.get(dateType + " To"));

        boolean isWithinDateRange = false;
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


    public boolean isDateOrder(String order) {
        List<WebElement> resultsTableRows = getTableBodyRows();
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
                    if (previousDate != null) {
                        int comparisonResult = previousDate.compareTo(currentDate);
                        if (order.equals("ascending") && comparisonResult > 0) {
                            // If the order is ascending and the previous date is after the current date, the list is not ordered
                            return false;
                        } else if (order.equals("descending") && comparisonResult < 0) {
                            // If the order is descending and the previous date is before the current date, the list is not ordered
                            return false;
                        }
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

    public void userSelectsAll() {
        driver.findElement(By.xpath("//*[contains(text(),'Select all')]"));
    }

    public void hideFilters() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: block; -> display: none;
        Assert.assertEquals("display: none;", filtersStyle);
    }

    public void displayFilters() {
        String filtersStyle = driver.findElement(By.id("filter-accordion-div")).getAttribute("style");
        // If the filters are hidden the form style will be changed from display: none -> display: block;
        Assert.assertEquals("display: block;", filtersStyle);
    }

    public void toggleAllFilters() {
        driver.findElement(By.xpath("//*[@id=\"accordion-default\"]/div[1]/button")).click();
    }

    public void verifyFilterState(String expandedOrClosed) {
        WebElement accordionDefault = driver.findElement(By.id("accordion-default"));
        List<WebElement> schemeFiltersList = accordionDefault.findElements(By.className("govuk-accordion__section"));
        String ariaExpanded = accordionDefault.findElement(By.className("govuk-accordion__open-all")).getAttribute("aria-expanded");
        if (expandedOrClosed.equals("expanded")) {
            Assert.assertEquals(ariaExpanded, "true");
            List<WebElement> filtersExpanded = accordionDefault.findElements(By.className("govuk-accordion__section--expanded"));
            Assert.assertEquals(schemeFiltersList.size(), filtersExpanded.size());
        } else {
            List<WebElement> filtersClosed = accordionDefault.findElements(By.className("govuk-accordion__open-all"));
            Assert.assertEquals(ariaExpanded, "false");
        }

    }

    public void doResultsInTableMatchFilter(String filterType) {
        List<WebElement> results = getTableBodyRows();
        int columnIndex = getColumnIndex(filterType);

        if (filterType.equals("Status") && driver.getTitle().toLowerCase(Locale.ROOT).contains("mfa")) {
            filterType = "MFA Status";
        } else if (filterType.equals("Status") && driver.getTitle().toLowerCase(Locale.ROOT).contains("scheme")) {
            filterType = "Scheme Status";
        }
        for (WebElement rows : results) {
            List<WebElement> columnData = rows.findElements(By.tagName("td"));
            Assert.assertTrue(columnData.get(columnIndex).getText().contains(testData.get(filterType)));
        }
    }

    public void inputDates(String filterType) {

        LocalDate dateFrom = LocalDate.parse(testData.get(filterType + " Date From"));
        LocalDate dateTo = LocalDate.parse(testData.get(filterType + " Date To"));

        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-day-from")).sendKeys(dateFrom.format(dayFormatter));
        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-month-from")).sendKeys(dateFrom.format(monthFormatter));
        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-year-from")).sendKeys(String.valueOf(dateFrom.getYear()));

        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-day-to")).sendKeys(dateTo.format(dayFormatter));
        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-month-to")).sendKeys(dateTo.format(monthFormatter));
        driver.findElement(By.id("filter-" + filterType.toLowerCase(Locale.ROOT) + "-year-to")).sendKeys(String.valueOf(dateTo.getYear()));


    }

    public void selectResultsPerPage(String resultsPerPage) {
        driver.findElement(By.id("results-per-page-" + resultsPerPage)).click();
    }

    public void doResultsMatchResultsPerPage(String resultsPerPage) {
        int numberOfResults = getTableBodyRows().size();
        WebElement pagnataionDiv = driver.findElement(By.id("pagination1"));
        List<WebElement> pagnationResults = pagnataionDiv.findElements(By.tagName("div"));
        boolean doesNextPageButtonExist = pagnataionDiv.findElements(By.xpath("//*[contains(text(),'Next Page')]")).size() > 0;

        if (pagnationResults.size() < 2) {
            Assert.assertTrue(numberOfResults <= Integer.parseInt(resultsPerPage));
        } else if (!doesNextPageButtonExist) {
            Assert.assertTrue(numberOfResults <= Integer.parseInt(resultsPerPage));
        } else {
            Assert.assertEquals(Integer.parseInt(resultsPerPage), numberOfResults);
        }
    }

    public void userSelectsNextPage() {
        boolean doesNextPageButtonExist = driver.findElement(By.id("pagination1")).findElements(By.xpath("//*[contains(text(),'Next Page')]")).size() > 0;
        if (doesNextPageButtonExist) {
            driver.findElement(By.id("paginationlink_next_page")).click();
        } else {
            System.out.println("No more pages to traverse");
        }
    }

    public void userSelectsFeedbackLink() {
        driver.findElement(By.linkText("feedback")).click();
    }

    public void isFeedbackFormDisplayed() {
        Assert.assertEquals(driver.getTitle(), "Feedback form");
    }

    public void userIsDirectedToHistoricURL() throws InterruptedException {
        Thread.sleep(2000);
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Assert.assertEquals("https://ec.europa.eu/transparencyregister/public/consultation/search.do?locale=en",
                driver.getCurrentUrl());
    }

    public void userSelectsLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public void isUserOnCookiesPage() {
        Assert.assertEquals(driver.getTitle(), "GOV.UK - Cookies help page");
    }

    public void selectAllowCookies() {
        driver.findElement(By.id("radio-e76f0057-0")).click();
    }

    public void selectDisallowCookies() { driver.findElement(By.id("radio-e76f0057-1")).click();}

    public void saveChanges() {
        driver.findElement(By.id("save-changes-button")).click();
    }

    public void areCookiesEnabled() {

        Set<Cookie> cookies = driver.manage().getCookies();
        Gson gson = new Gson();
        Cookie policyCookie = cookies.stream()
                .filter(cookie -> cookie.getName().equals("cookies_policy"))
                .findFirst()
                .orElse(null);
        if (policyCookie != null) {
            Map<String, Object> policyData = gson.fromJson(policyCookie.getValue(), Map.class);
            boolean usage = (boolean) policyData.getOrDefault("usage", false);
            Assert.assertTrue(usage, "cookies_policy cookie has usage value false");
        } else {
            Assert.fail("cookies_policy cookie not found");
        }
    }

    public void selectFilter(String filter) {
        WebElement accordionDefault = driver.findElement(By.id("accordion-default"));
        WebElement expandedFilter = accordionDefault.findElement(By.className("govuk-accordion__section--expanded"));
        WebElement filterElement = expandedFilter.findElement(By.cssSelector("input, select"));

        if (filterElement.getAttribute("class").contains("govuk-select")) {
            Select select = new Select(filterElement);
            select.selectByVisibleText(filter);
        } else if (filterElement.getAttribute("class").contains("govuk-checkboxes__input")) {
            filterElement.findElement(By.xpath("//*[contains(text(),'" + filter + "')]")).click();
        } else {
            filterElement.sendKeys(filter);
        }

    }

    public void selectFilterHeader(String filterName) {
        WebElement filterList = driver.findElement(By.id("accordion-default"));
        filterList.findElement(By.xpath("//button[contains(text(),'" + filterName + "')]")).click();
    }

    public void inputMonetaryRange(String range) {
        WebElement accordionDefault = driver.findElement(By.id("accordion-default"));
        WebElement expandedFilter = accordionDefault.findElement(By.className("govuk-accordion__section--expanded"));
        String formattedRange = range.replaceAll(" ", "-");
        expandedFilter.findElement(By.id("filter-" + formattedRange.toLowerCase(Locale.ROOT) + "-from")).sendKeys(testData.get(range + " From"));
        expandedFilter.findElement(By.id("filter-" + formattedRange.toLowerCase(Locale.ROOT) + "-to")).sendKeys(testData.get(range + " To"));
    }

    public void areTableResultsOrderedByColumnName(String order, String columnName) {
        if (columnName.toLowerCase(Locale.ROOT).contains("date")) {
            Assert.assertTrue(isDateOrder(order));
        } else {
            Assert.assertTrue(isCorrectAlphabeticalOrder(order, columnName));
        }
    }

    private boolean isCorrectAlphabeticalOrder(String order, String columnName) {
        try {
            WebElement table = driver.findElement(By.id("searchresult-table"));
            WebElement columnLink = table.findElement(By.xpath("//a[contains(text(),'" + columnName + "')]"));
            WebElement columnIcon = columnLink.findElement(By.tagName("img"));
            String altText = columnIcon.getAttribute("alt");
            return altText.toLowerCase(Locale.ROOT).contains(order.toLowerCase(Locale.ROOT));
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
    }

    public void areTableResultsWithinMonetaryRange(String budget) {
        int budgetLowerBound = Integer.parseInt(testData.get(budget + " From"));
        int budgetUpperBound = Integer.parseInt(testData.get(budget + " To"));

        List<WebElement> results = getTableBodyRows();
        int columnIndex = getColumnIndex(budget);
        for (WebElement rows : results) {
            List<WebElement> columnData = rows.findElements(By.tagName("td"));
            String reformatTableData = columnData.get(columnIndex).getText().replace("£", "").replace(",", "");
            int budgetValue = Integer.parseInt(reformatTableData);
            Assert.assertTrue((budgetValue <= budgetUpperBound) && (budgetValue >= budgetLowerBound));
        }
    }

    public void orderTableByColumn(String columnName, String order) {
        boolean isCorrectOrder = false;
        while (!isCorrectOrder) {
            WebElement table = driver.findElement(By.id("searchresult-table"));
            WebElement column = table.findElement(By.xpath("//a[contains(text(),' " + columnName + "')]"));
            String sortedStatus = column.findElement(By.tagName("img")).getAttribute("alt");
            if (sortedStatus.toLowerCase(Locale.ROOT).contains(order.toLowerCase(Locale.ROOT))) {
                isCorrectOrder = true;
            } else {
                column.click();
            }
        }
    }

    public void selectDownloadFileButton(String fileType) {
        driver.findElement(By.id("export_" + fileType.toLowerCase(Locale.ROOT) + "_button")).click();
    }

    public void openBrowserDownloadsFolder(String fileType){
        String downloadsFolder = getDownloadFolder();
        File folder = new File(downloadsFolder);
        File[] files = folder.listFiles();
        if (files.length == 1) {
            for (File file : files) {
                if (fileType.toLowerCase(Locale.ROOT).equals("excel")) {
                    Assert.assertTrue(file.getName().endsWith(".xlsx"));
                } else if (fileType.toLowerCase(Locale.ROOT).equals("csv")) {
                    Assert.assertTrue(file.getName().endsWith(".csv"));
                }
            }
        }
    }

    private String getDownloadFolder() {
        //TODO
        // Add logic for linux os
        // //project working directory // selenium
//        String os = System.getProperty("os.name").toLowerCase();
//        if (os.contains("win")) {
//            return "C:\\Users\\{username}\\SeleniumDownloadsFolder/";
//        } else if (os.contains("mac")) {
////            return System.getProperty("user.home") + "/SeleniumDownloadsFolder/";
//            return System.getProperty("user.dir") + "/SeleniumDownloadsFolder/";
//        } else {
//            throw new IllegalArgumentException("Unsupported operating system");
//        }
        return System.getProperty("user.dir") + "/SeleniumDownloadsFolder/";
    }

    public void userClicksRecipientNameLink() {
        List<WebElement> results = getTableBodyRows();
        results.get(0).findElement(By.className("govuk-link")).click();
    }

    public int numberOfResultsOnPage() {
        return Integer.parseInt(driver.findElement(By.id("number-of-results")).getText());
    }

    public int numberOfResultsInFile() throws InterruptedException {
        Thread.sleep(2000);
        File folder = new File(getDownloadFolder());
        File[] files = folder.listFiles();
        String filePath = null;
        if (files != null) {
            filePath = files[0].toString();
        }
        int rowCount = 0;
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1);
        if (extension.equals("csv")) {
            // read a CSV file
            try {
                CSVReader reader = new CSVReader(new FileReader(filePath));
                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    boolean hasData = false;

                    for (String column : nextLine) {
                        if (!column.isEmpty()) {
                            hasData = true;
                            break;
                        }
                    }
                    if (hasData) {
                        rowCount++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            FileInputStream inputStream = null;
            File excelFile = new File(filePath);
            try {
                inputStream = new FileInputStream(excelFile);

                // Create a Workbook object for the Excel file
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

                // Get the first sheet from the workbook
                XSSFSheet sheet = workbook.getSheetAt(0);

                // Iterate over the rows in the sheet and count the number of rows with data
                for (Row row : sheet) {
                    boolean isRowEmpty = true;
                    for (Cell cell : row) {
                        if (cell.getCellType() != CellType.BLANK) {
                            isRowEmpty = false;
                            break;
                        }
                    }
                    if (!isRowEmpty) {
                        rowCount++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Close the input stream
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // rowCount
        return rowCount - 1;
    }

    public void doPageResultsMatchFileResults () throws InterruptedException {
        Assert.assertEquals(numberOfResultsInFile(), numberOfResultsOnPage());
        System.out.println("Expected [" + numberOfResultsOnPage() + "] - Actual [" + numberOfResultsInFile() + "] results");
    }


    public void userSelectsSearchOption(String searchOption) {
        driver.findElement(By.xpath("//*[contains(text(),' " + testData.get(searchOption) + "')]")).click();
    }
}

