//package Util;
//
//
//import net.serenitybdd.core.Serenity;
//import net.serenitybdd.core.exceptions.SerenityManagedException;
//import net.serenitybdd.core.pages.WebElementFacade;
//import net.thucydides.core.annotations.Step;
//import net.thucydides.core.pages.PageObject;
//import net.thucydides.core.webdriver.exceptions.ElementShouldBeEnabledException;
//import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;
//import org.testng.Assert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.ElementClickInterceptedException;
//import org.openqa.selenium.ElementNotInteractableException;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.UnsupportedCommandException;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import uk.gov.beis.rrems.civilian.core.Properties;
//import uk.gov.beis.rrems.civilian.model.data.TestDateHelper;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.assertEquals;
//
///**
// * Custom RREMS methods for page automation
// */
//
//public abstract class PageUtils extends PageObject {
//
//    public static final String BRING_INTO_VIEW = "arguments[0].scrollIntoView(true);";
//
//    public static final String BRING_INTO_VIEW_BOTTOM = "arguments[0].scrollIntoView(false);";
//
//    public static final String BRING_INTO_VIEW_MIDDLE = "arguments[0].scrollIntoView({block: \"center\",inline: \"center\",behavior: \"smooth\"});";
//
//    @FindBy(id = "previewSubmission")
//    private WebElementFacade previewSubmission;
//
//    @FindBy(id = "submitForm")
//    private WebElementFacade confirmSubmission;
//
//    @FindBy(id = "showHideSections")
//    private WebElementFacade showHideSections;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(PageUtils.class);
//
//    private JavascriptExecutorFacade js;
//
//    private JavascriptExecutorFacade getJavascriptExecutor() {
//        if (js == null) {
//            js = new JavascriptExecutorFacade(getDriver());
//        }
//        return js;
//    }
//
//    /**
//     * To clear value from input field
//     *
//     * @param webElementFacade
//     */
//    public void clearField(final WebElementFacade webElementFacade) {
//        webElementFacade.sendKeys(Keys.END);
//        int length = webElementFacade.getValue().length() + 2;
//        for (int i = 0; i < length; i++) {
//            webElementFacade.sendKeys(Keys.BACK_SPACE);
//        }
//    }
//
////    public void deleteExistingAndTypeKeys(final WebElementFacade locator, final String textValue) {
////        deleteExisting(locator);
////        waitForElement(locator, 3).sendKeys(textValue);
////    }
//
////    public void deleteExisting(final WebElementFacade locator) {
////        if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
////            waitForElement(locator, 3).clear();
////        } else {
////            waitForElement(locator, 3).sendKeys(Keys.END);
////            int length = waitForElement(locator, 3).getValue().length() + 2;
////            for (int i = 0; i < length; i++) {
////                waitForElement(locator, 3).sendKeys(Keys.BACK_SPACE);
////            }
////        }
////    }
//
////    public void bringElementIntoView(final WebElementFacade webElementFacade) {
////        scrollToElement(BRING_INTO_VIEW, webElementFacade);
////    }
////
////    public void scrollToElement(final String script, WebElementFacade webElementFacade) {
////        if (!Properties.getAppiumPlatformName().equals("")) {
////            waitABit(50000);
////        } else {
////            waitUntilElementExists(webElementFacade, 15);
////        }
////        try {
////            getJavascriptExecutor().executeScript(script, webElementFacade);
////        } catch (NoSuchElementException e) {
////            assert (webElementFacade.isPresent());
////        }
////    }
//
//    public void scrollPageDown() {
//        getJavascriptExecutor().executeScript("window.scrollBy(0, 450)");
//    }
//
//    public void clickOkOnDialogBox() {
//        find(By.id("modalActionButton")).click();
//    }
//
//    public void typeAndEnterTextIntoFormDropDown(WebElementFacade dropDownField, String text) {
//        dropDownField.sendKeys(text);
//        dropDownField.sendKeys(Keys.ENTER);
//    }
//
//    public void clickOnElementUsingJavaScript(WebElement element) {
//        try {
//            getJavascriptExecutor().executeScript("arguments[0].click();", element);
//        } catch (UnsupportedCommandException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // NEW FRAMEWORK UTILS STARTS HERE
//
//    protected void openFormSection(final String sectionNumber) {
//        try {
//            findAll(By.cssSelector(".dynamic-form-section"))
//                    .stream()
//                    .filter(webElementFacade -> webElementFacade.getAttribute("id").endsWith(sectionNumber))
//                    .findAny()
//                    .ifPresent(WebElementFacade::click);
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        } catch (ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            throw new ElementClickInterceptedException(e.getMessage());
//        }
//    }
//
//    public void clickDropDownAndSelectElementFromDropDown(final String elementId, final String visibleText) {
//            selectElementFromDropDown(elementId, visibleText);
//    }
//
//    public void clickDropDownAndSelectByVisibleTextViaOption(final WebElementFacade dropDownField, final String visibleText) {
//        clickDropDownAndSelectByVisibleText("option", dropDownField, visibleText);
//    }
//
//    private void clickDropDownAndSelectByVisibleText(final String selectDropDownType, final WebElementFacade dropDownField, final String visibleText) {
//
//        if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            dropDownField.select().byVisibleText(visibleText);
//        } else {
//            try {
//                dropDownField.click();
//                // some drop-downs are now populated using an API call
//                // so add a wait to give these time to be rendered
//                waitABit(2500);
//                WebElementFacade selection = findBy("//" + selectDropDownType + "[contains(text(),'" + visibleText + "')]");
//                ExpectedConditions.visibilityOf(selection);
//                selection.click();
//            } catch (TimeoutException timeoutException) {
//                dropDownField.click();
//                // some drop-downs are now populated using an API call
//                // so add a wait to give these time to be rendered
//                waitABit(2500);
//                WebElementFacade selection = findBy("//" + selectDropDownType + "[contains(text(),'" + visibleText + "')]");
//                ExpectedConditions.visibilityOf(selection);
//                selection.click();
//            }
//        }
//    }
//
//    public void clickDropDownAndSelectByText(final WebElementFacade dropDownField, final String visibleText){
//        try {
//            dropDownField.click();
//            // some drop-downs are now populated using an API call
//            // so add a wait to give these time to be rendered
//            waitABit(2500);
//            WebElementFacade selection = findBy("//" + "span" + "[contains(text(),'" + visibleText + "')]");
//            ExpectedConditions.visibilityOf(selection);
//            selection.click();
//        } catch (TimeoutException e) {
//            dropDownField.click();
//            // some drop-downs are now populated using an API call
//            // so add a wait to give these time to be rendered
//            waitABit(2500);
//            WebElementFacade selection = findBy("//" + "span" + "[contains(text(),'" + visibleText + "')]");
//            ExpectedConditions.visibilityOf(selection);
//            selection.click();
//        }
//    }
//    public void selectElementFromDropDown(String id, String text) {
//        clickWithoutScroll(findBy(id));
//        WebElementFacade selection = findBy("//span[contains(text(),'" + text + "')]");
//        try {
//            waitFor(selection);
//            selection.click();
//        } catch (TimeoutException e) {
//            try {
//                selection.click();
//            } catch (ElementShouldBeEnabledException e2) {
//                waitABit(60000);
//                Actions action = new Actions(getDriver());
//                action.click(selection).build().perform();
//            }
//        }
//    }
//
//    public void selectElementsFromDropDown(String id, String text) {
//        // clickWithoutScroll(findBy(id));
//        List<WebElementFacade> selections = findAll("//span[contains(text(),'" + text + "')]");
//    }
//
//    public void click(final WebElementFacade element) {
//        click(true, element);
//    }
//
//    public void clickWithoutScroll(final WebElementFacade element) {
//        click(false, element);
//    }
//
//    private void click(final boolean scroll, final WebElementFacade element) {
//        try {
//            if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//                element.waitUntilEnabled();
//                clickOnElementUsingJavaScript(element);
//            } else if (Properties.getAppiumBrowserName().equalsIgnoreCase("safari")) {
//                try {
//                    element.waitUntilEnabled();
//                    clickOnElementUsingJavaScript(element);
//                } catch (Exception e) {
//                    waitForElement(element, 25);
//                    clickOnElementUsingJavaScript(element);
//
//                }
//            } else {
//                if (scroll) {
//                    scrollToElement(BRING_INTO_VIEW, element);
//                }
//                try {
//                    if (isFirefox() || isIE()) {
//                        waitFor(element);
//                        element.click();
//                    } else {
//                        element.waitUntilEnabled().click();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (TimeoutException | NullPointerException | NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException | UnsupportedCommandException e) {
//            LOGGER.error(e.getMessage());
//            try {
//                element.click();
//            } catch (ElementClickInterceptedException | ElementShouldBeEnabledException | UnsupportedCommandException e2) {
//                try {
//                    element.click();
//                } catch (ElementShouldBeEnabledException | ElementClickInterceptedException | UnsupportedCommandException e3) {
//                    e3.printStackTrace();
//                }
//
//            }
//        }
//    }
//
//    public void scrollToMiddleAndClick(final WebElementFacade element) {
//        try {
//            if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//                element.waitUntilEnabled();
//                clickOnElementUsingJavaScript(element);
//            } else {
//                scrollToElement(BRING_INTO_VIEW_MIDDLE, element);
//                element.waitUntilEnabled().click();
//            }
//        } catch (NullPointerException | NoSuchElementException | StaleElementReferenceException | ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//        }
//    }
//
//    public void typeKeys(final WebElementFacade field, final String textValue) {
//        try {
//            scrollToElement(BRING_INTO_VIEW, field);
//
//            // click before attempting to send keys due to an issue with Selenium driver on Safari
//            click(field);
//            field.sendKeys(String.valueOf(textValue));
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        } catch (ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            throw new ElementClickInterceptedException(e.getMessage());
//        }
//    }
//
//    public void typeKeysWithoutScroll(final WebElementFacade field, final String textValue) {
//        waitForElement(field, 30);
//        click(field);
//        field.sendKeys(String.valueOf(textValue));
//
//    }
//
//    public void typeKeysWithClear(final WebElementFacade field, final String textValue) {
//        try {
//            scrollToElement(BRING_INTO_VIEW, field);
//            field.waitUntilVisible().clear();
//            if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//                field.waitUntilVisible().sendKeys("");
//            }
//            field.waitUntilVisible().sendKeys(String.valueOf(textValue));
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        } catch (ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            throw new ElementClickInterceptedException(e.getMessage());
//        }
//    }
//
//    public void typeTextDeleteExisting(final WebElementFacade field, final String value) {
//        scrollToElement(BRING_INTO_VIEW, field);
//        field.waitUntilVisible().sendKeys(Keys.END);
//        int length = field.getValue().length() + 2;
//        for (int i = 0; i < length; i++) {
//            field.waitUntilVisible().sendKeys(Keys.BACK_SPACE);
//        }
//        field.waitUntilVisible().sendKeys(value);
//    }
//
//    public void typeKeysViaJavaScript(final WebElementFacade element, final String valueToSet) {
//        getJavascriptExecutor().executeScript("arguments[0].value = '" + valueToSet + "';", element);
//    }
//
//    public void sendKeys(WebElementFacade element, String value) {
//        element.sendKeys(value);
//    }
//
//    /**
//     * Wait until the element is visible for given element xpath or css value with timeout
//     *
//     * @param xpathOrCssValue
//     * @param seconds
//     * @return
//     */
//    public WebElementFacade waitUntilVisibility(final String xpathOrCssValue, int seconds) {
//        try {
//            return element(xpathOrCssValue).withTimeoutOf(Duration.ofSeconds(seconds));
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        }
//    }
//
//    public WebElementFacade waitForElement(final WebElementFacade elementId, final int timeoutInSeconds) {
//        try {
//            // Fluent with with configurable timeout in seconds
//            return element(elementId).withTimeoutOf(Duration.ofSeconds(timeoutInSeconds));
//        } catch (Exception exception) {
//            LOGGER.error(exception.getMessage());
//            throw exception;
//        }
//    }
//
//    public void waitForPageUrl(String string, int duration) {
//        getWebDriverWait(duration).until(ExpectedConditions.urlContains(string));
//    }
//
//    public void filterUICssAndClickByMatchingText(final String elementCssTextValue, final String matchingText) {
//        scrollToElement(BRING_INTO_VIEW, findBy(elementCssTextValue));
//        try {
//            findAll(By.cssSelector(elementCssTextValue))
//                    .stream().filter(webElementFacade -> webElementFacade.containsText(matchingText))
//                    .findAny()
//                    .ifPresent(WebElementFacade::click);
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        } catch (ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            throw new ElementClickInterceptedException(e.getMessage());
//        }
//    }
//
//    public void filterUICssAndClickByStartingText(final String elementCssTextValue, final String startingCharacter) {
//        try {
//            findAll(By.cssSelector(elementCssTextValue))
//                    .stream().filter(webElementFacade -> webElementFacade.getText().startsWith(startingCharacter))
//                    .findAny()
//                    .ifPresent(WebElementFacade::click);
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        } catch (ElementClickInterceptedException e) {
//            LOGGER.error(e.getMessage());
//            throw new ElementClickInterceptedException(e.getMessage());
//        }
//    }
//
//    public void filterCssAndDisplayByMatchingText(final String elementCssTextValue, final String matchingText) {
//        try {
//            findAll(By.cssSelector(elementCssTextValue))
//                    .stream().filter(webElementFacade -> webElementFacade.containsText(matchingText))
//                    .findAny()
//                    .ifPresent(WebElementFacade::isDisplayed);
//        } catch (NullPointerException e) {
//            LOGGER.error(e.getMessage());
//            throw new NullPointerException(e.getMessage());
//        } catch (NoSuchElementException e) {
//            LOGGER.error(e.getMessage());
//            throw new NoSuchElementException(e.getMessage());
//        } catch (StaleElementReferenceException e) {
//            LOGGER.error(e.getMessage());
//            throw new StaleElementReferenceException(e.getMessage());
//        }
//    }
//
//    public boolean filterCssAndCheckPresenceByMatchingText(final String elementCssTextValue, final String matchingText) {
//        return findAll(By.cssSelector(elementCssTextValue))
//                .stream()
//                .anyMatch(webElementFacade -> webElementFacade.containsText(matchingText));
//    }
//
//    public void iAmOn(String expectedPageUrlEnding) { // Any url ending pattern works
//        assertThat(getDriver().getCurrentUrl()).endsWith(expectedPageUrlEnding);
//    }
//
//    public void pageUrlContains(String expectedUrlTextCharacter) { // Any part of url character works
//        assertThat(getDriver().getCurrentUrl()).contains(expectedUrlTextCharacter);
//    }
//
//    public void pageDoesNotContainUrlPattern(String expectedUrlTextCharacter) {
//        assertThat(getDriver().getCurrentUrl()).doesNotContain(expectedUrlTextCharacter);
//    }
//
//    public void pageHeaderIs(String pageHeader) { // Use only if .page-header css value is on page
//        assertThat(findBy(".page-header").getText()).isEqualTo(pageHeader);
//    }
//
//    public List<String> getListOfDropdownOptions(String selector) {
//        WebElement selectElement = find(selector);
//        Select select = new Select(selectElement);
//
//        // Convert Options into a Java List
//        return select.getOptions().stream().map(WebElement::getText)
//                .filter(s -> !s.isEmpty())
//                .collect(Collectors.toList());
//    }
//
//    public void clickComboBoxAndSelectByVisibleText(WebElementFacade field, String visibleText) {
//        typeKeys(field, visibleText);
//        field.sendKeys(Keys.ARROW_DOWN);
//        field.sendKeys(Keys.ENTER);
//    }
//
//    public void clickPreviewSubmission() {
//        click(previewSubmission);
//    }
//
//    public void clickSubmit() {
//        click(confirmSubmission);
//        waitABit(2000);
//    }
//
//    /**
//     * Method to verify the content for given element id
//     *
//     * @param Id
//     * @param value
//     */
//    public void verifyContent(final String Id, final String value) {
//        waitForCondition().until(ExpectedConditions.textToBePresentInElement(findBy(Id), value));
//    }
//
//    /**
//     * Method to verify the content for given web element
//     *
//     * @param webElementFacade
//     * @param value
//     */
//    public void verifyContent(final WebElementFacade webElementFacade, final String value) {
//        if (isIPAD()) {
//            waitForElement(webElementFacade, 25);
//        } else {
//            waitUntilElementExists(webElementFacade, 25);
//        }
//        Assert.assertTrue(webElementFacade.getText().contains(value));
//    }
//
//    protected void elementDelay(String xpath) {
//        waitForRenderedElements(By.xpath(xpath));
//    }
//
//    protected void waiting() {
//        if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            waitABit(1500);
//        } else {
//            getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//        }
//    }
//
//    public void waitForSeconds(int seconds) {
//        if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            waitABit(seconds * 1000);
//        } else {
//            getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
//        }
//    }
//
//    public void verifyElementIsPresentWithWait(final WebElementFacade webElementFacade, final long timeInMilliseconds) {
//        scrollToElement(BRING_INTO_VIEW, webElementFacade);
//        Assert.assertTrue(webElementFacade.isPresent());
//        waitABit(timeInMilliseconds);
//    }
//
//    /**
//     * Return incident Id for given identifier (for example - '20190525-0730-VICT')
//     *
//     * @param identifier
//     * @param type
//     * @return
//     */
//    public String getIncidentId(String identifier, String type) {
//        return TestDateHelper.getTimeCode(identifier) + "-" + type;
//    }
//
//    public void waitUntilElementExists(WebElementFacade elementLocator, int timeoutInSeconds) {
//        try {
//            getWebDriverWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(elementLocator));
//        } catch (NoSuchElementException noSuchElementException) {
//            LOGGER.info("Element with locator: '" + elementLocator + "' was not found in current context page.");
//        } catch (ElementNotInteractableException elementNotInteractableException) {
//            waitUntilElementExists(elementLocator, 55);
//        } catch (TimeoutException timeoutException) {
//            // Maybe page not loaded yet give it another try especially iPad
//            if (!Properties.getAppiumPlatformName().equals("")) {
//                waitABit(10000);
//            } else {
//                waitABit(1000);
//            }
//            try {
//                assertTrue(elementLocator.isPresent());
//            } catch (NoSuchElementException noSuchElementException) {
//                if (Properties.getAppiumPlatformName().equalsIgnoreCase("ios") || Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//                    waitABit(35000);
//                    if (noSuchElementException.getMessage().contains("Element not found") && !elementLocator.isVisible()) {
//                        getDriver().findElement(By.xpath("//*[@id=\"incidentType.Reactor.label\"]")).click();
//                    }
//                    waitABit(3000);
//                    try {
//                        if (!elementLocator.isVisible()) {
//                            Assert.fail(elementLocator.getText() + "is not displayed");
//
//                        }
//                    } catch (Exception exception) {
//                        waitABit(35000);
//                        try {
//                            assertTrue(elementLocator.isPresent());
//                        } catch (SerenityManagedException serenityManagedException) {
//                            assertTrue(elementLocator.isPresent());
//                        }
//
//                    }
//                }
//            } catch (UnsupportedCommandException unsupportedCommandException) {
//                waitABit(3000);
//                try {
//                    if (!elementLocator.isVisible()) {
//                        Assert.fail(elementLocator.getText() + "is not displayed");
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            } catch (StaleElementReferenceException staleElementReferenceException) {
//                staleElementReferenceException.printStackTrace();
//            } catch (SerenityManagedException serenityManagedException) {
//                serenityManagedException.getMessage();
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    public void waitUntilElementIsClickable(final WebElementFacade elementLocator, int timeoutInSeconds) {
//        try {
//            getWebDriverWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(elementLocator));
//        } catch (NoSuchElementException e) {
//            LOGGER.info("Element with locator: '" + elementLocator + "' was not found in current context page.");
//        } catch (ElementNotInteractableException e) {
//            getWebDriverWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(elementLocator));
//        } catch (TimeoutException e) {
//            waitABit(10000);
//            assertTrue(elementLocator.isDisplayed());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Common function to create WebDriverWait to make it explicit we use seconds here which goes
//     * against what most serenity/seleium element functiosn use. Has caught us out previously
//     * using milliseconds which leads to tests timing out at 20 mins instead of 20 seconds
//     *
//     * @param timeoutInSeconds
//     * @return WebDriverWait
//     */
//    private WebDriverWait getWebDriverWait(int timeoutInSeconds) {
//        // Hundreds of areas where this is used, had issue where it was using milliseconds
//        // just a catch all to make sure we never wait any longer than 30 seconds
//        timeoutInSeconds = (timeoutInSeconds < 30) ? timeoutInSeconds : 30;
//        return new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutInSeconds));
//    }
//
//    /**
//     * To verify page title
//     *
//     * @param titleXpath
//     * @param title
//     */
//    public void verifyPageTitle(String titleXpath, String title) {
//        waitUntilElementExists(findBy(titleXpath), 30);
//        assertThat(findBy(titleXpath).getTextContent()).containsIgnoringCase(title);
//    }
//
//    public void verifySectionProgress(String type, List<String> result) {
//        List<WebElementFacade> progressElements = findAll(type);
//
//        List<String> enabledProgressElements = progressElements.stream()
//                .filter(s -> !(s.hasClass("locked") && s.hasClass("hide-when-locked")))
//                .map(WebElementFacade::getText)
//                .map(String::trim)
//                .collect(Collectors.toList());
//        try {
//            assertEquals(result, enabledProgressElements);
//        } catch (SerenityManagedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void verifySectionNames(String type, List<String> result) {
//        // Get all elements
//        List<WebElementFacade> progressElements = findAll(type);
//
//        // Now filter out those that are locked and hidden when locked then get the child element trimmed text value
//        List<String> sectionNames = progressElements.stream()
//                .filter(s -> !(s.hasClass("locked") && s.hasClass("hide-when-locked")))
//                .map(n -> (WebElementFacade) n.find(By.className("section-name")))
//                .map(WebElementFacade::getText)
//                .map(String::trim)
//                .collect(Collectors.toList());
//
//        assertEquals(result, sectionNames);
//    }
//
//    public void deleteThenTypeKeys(final WebElementFacade locator, final String textValue, final int qtyToDelete) {
//        waitUntilElementExists(locator, 5);
//        if (Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            locator.sendKeys("");
//            for (int i = 0; i < qtyToDelete; i++) {
//                locator.sendKeys(Keys.BACK_SPACE);
//            }
//        } else {
//            locator.sendKeys(Keys.END);
//            int length = locator.getValue().length() + 2;
//            for (int i = 0; i < length; i++) {
//                locator.sendKeys(Keys.BACK_SPACE);
//            }
//        }
//        locator.sendKeys(textValue);
//    }
//
//    public void clickShowAllSectionsLink() {
//        scrollToElement(BRING_INTO_VIEW, showHideSections);
//        if (!showHideSections.getText().contains("Hide")) {
//            click(showHideSections);
//        }
//        // Already open
//    }
//
//    public void verifyElementContainsText(final WebElementFacade element, final String text) {
//        // Safari likes to return double whitespace where other browsers do not
//        if (Properties.getAppiumPlatformName().equalsIgnoreCase("ios") || Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            waitABit(4000);
//        }
//        assertTrue(element.getText().trim().replace("  ", " ").contains(text));
//    }
//
//    public boolean hasClass(WebElementFacade element, String className) {
//        String classes = element.getAttribute("class");
//        for (String c : classes.split(" ")) {
//            if (c.equals(className)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * the method to check the message displayed in the dialog box
//     * the solution for safari is being worked on and in the meantime
//     * its will skip the testing
//     *
//     * @param dialogBox
//     * @param message
//     */
//    public void verifyDialogBoxMessage(final WebElementFacade dialogBox, final String message) {
//        if (!Properties.getSeleniumBrowserName().equalsIgnoreCase("safari")) {
//            waitABit(2000);
//            waitUntilElementExists(dialogBox, 10);
//            assert (dialogBox.containsText(message));
//        }
//    }
//
//    /**
//     * method to check if the result is true
//     *
//     * @param valid
//     */
//    public void assertTrue(boolean valid) {
//        try {
//            assert (valid);
//        } catch (SerenityManagedException e) {
//            e.printStackTrace();
//            Assert.fail("Result not true");
//        }
//    }
//
//    /**
//     * method to check if the result is false
//     *
//     * @param valid
//     */
//    public void assertFalse(boolean valid) {
//        assert (!valid);
//    }
//
//    /**
//     * method to check if the shared/withdrawn data query notification is displayed
//     * true for shared and false for withdrawn
//     *
//     * @param shared
//     */
//    public boolean verifySharedDQNotificationIsDisplayed(boolean shared) {
//        String queryName = Serenity.sessionVariableCalled("DATA_QUERY_NAME");
//        waitABit(15000);
//        findBy(".item-text").waitUntilVisible();
//        if (shared) {
//            return findBy(".item-text").containsText("Data Query - " + queryName + " has been shared with you");
//        } else {
//            return findBy(".item-text").containsText("Data Query - " + queryName + " has been withdrawn");
//        }
//    }
//
//    /**
//     * method to refresh the page
//     */
//    public void pageRefresh() {
//        getDriver().navigate().refresh();
//    }
//
//    /**
//     * method to select given filter values from the dropdown
//     *
//     * @param filterValues - filter values to select
//     * @param dropDownBox  - dropdown in which values need to be selected
//     */
//    public void selectValuesFromDropDown(List<String> filterValues, WebElementFacade dropDownBox) {
//        for (int i = 0; i < filterValues.size(); i++) {
//            clickDropDownAndSelectByVisibleTextViaSpan(dropDownBox, filterValues.get(i));
//        }
//    }
//
//    /**
//     * method to select given filter values from the dropdown for ipad
//     *
//     * @param dropDownValue - filter value to select
//     * @param dropdown  - dropdown in which values need to be selected
//     */
//    public void selectValueFromDropDownForIpad(String dropDownValue, WebElementFacade dropdown){
//        scrollToElement(BRING_INTO_VIEW_MIDDLE, dropdown);
//        dropdown.sendKeys(dropDownValue);
//        dropdown.click();
//        String dropDownValueXpath = "//span[contains(text(),'" + dropDownValue + "')]";
//        elementDelay(dropDownValueXpath);
//        findBy(dropDownValueXpath).click();
//    }
//
//    /**
//     * Clear an elements value
//     * Handles angular issue detailed in:
//     * https://github.com/angular/angular.js/issues/6523
//     *
//     * @param element
//     */
//    public void clearElementValue(WebElement element) {
//        getJavascriptExecutor().executeScript("arguments[0].value = '';", element);
//    }
//
//    public void openFilterModal() {
//        WebElementFacade filterButton = findBy("//*[@id=\"filter\"]");
//        waitForElement(filterButton, 5);
//        click(filterButton);
//    }
//
//    public void verifyThatFilterModalHasOpened() {
//        // wait for the Filter modal to open
//        waitUntilElementExists(findBy("//*[@id=\"form-modal-container\"]/div/div[1]"), 30);
//
//        // check the modal opened properly by checking the modal title
//        final String filterModalTitle = "Filter list";
//        assertEquals(filterModalTitle, findBy("//*[@id=\"form-modal-container\"]/div/div[1]/span").getText());
//    }
//
//    @Step("Wait until the table is displayed")
//    public void waitUntilTheTableIsDisplayed() {
//        /*
//         * Because several pages use the same Xpath to read the page title, waiting for the page title to be present
//         * before verifying it can lead to issues where the old title value is read and expected to match the new title
//         * by waiting for the table to load before checking the page title, this can help prevent the issue from
//         * occurring when navigating between forms and the list of the form entries
//         */
//        final String tableXpath
//                = "//*[@id=\"app-content\"]/app-military-component/div/app-military-form-component/div[1]/div/div[2]/table";
//        waitForElement(findBy(tableXpath), 30);
//        elementDelay("//*[@id=\"dateTime-0\"]");
//    }
//
//    public boolean isIE() {
//        return Properties.getSeleniumBrowserName().equalsIgnoreCase("internet explorer") || Properties.getSeleniumBrowserName().equalsIgnoreCase("ie");
//    }
//
//    public boolean isChrome() {
//        return Properties.getSeleniumBrowserName().equalsIgnoreCase("chrome");
//    }
//
//    public boolean isFirefox() {
//        return Properties.getSeleniumBrowserName().equalsIgnoreCase("firefox");
//    }
//
//    public boolean isSafari() {
//        return Properties.getSeleniumBrowserName().equalsIgnoreCase("safari");
//    }
//
//    public boolean isIPAD() {
//        return Properties.getAppiumBrowserName().equalsIgnoreCase("safari");
//    }
//
//    public void defencePageLoadVerification() {
//        try {
//            if (isIPAD()) {
//                waitForElement(findBy("//*[@id=\"incident-summary\"]"), 30);
//            } else {
//                waitUntilElementExists(findBy("//*[@id=\"incident-summary\"]"), 30);
//            }
//        } catch (Exception ex) {
//            pageRefresh();
//            waitABit(3000L);
//        }
//    }
//
//    public void clickBrowserBackButton() {
//        getDriver().navigate().back();
//    }
//
//    public void selectLocationFromDropDown(String elementID, String locationName){
//        click(findBy("//*[@id=\""+elementID+"\"]"));
//        if(isIE() || isIPAD()){
//            clickDropDownAndSelectByVisibleTextViaSpan(findBy("//*[@id=\""+elementID+"\"]"), locationName);
//        }else if(isSafari()){
//            click(findBy("//*[@id=\""+elementID+"\"]"));
//            clickDropDownAndSelectByText(findBy("//*[@id=\""+elementID+"-component\"]"), locationName);
//        }  else clickDropDownAndSelectByVisibleTextViaSpan(findBy("//*[@id=\""+elementID+"-component\"]"), locationName);
//
//    }
//}