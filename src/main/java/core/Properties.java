//package core;
//
//import net.thucydides.core.guice.Injectors;
//import net.thucydides.core.util.EnvironmentVariables;
//import org.openqa.selenium.remote.UnreachableBrowserException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.net.MalformedURLException;
//import java.net.URL;
//
////import static uk.gov.beis.rrems.civilian.util.PropertyManager.readPropertyOrEnv;
//
//
//public class Properties {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);
//    private static EnvironmentVariables vars = Injectors.getInjector().getProvider(EnvironmentVariables.class).get();
//
//    public static String getSeleniumPlatformName() {
//        return readPropertyOrEnv("SELENIUM_PLATFORM"); // e.g macOS, OSX, Windows 7
//    }
//
//    public static String getAppiumPlatformName() {
//        return readPropertyOrEnv("APPIUM_PLATFORM"); // E.g ios or android
//    }
//
//    public static String getBrowserVersion() {
//        return readPropertyOrEnv("SELENIUM_BROWSER_VERSION");
//    }
//
//    public static String getSeleniumBrowserName() {
//        // Edge is very picky in what it needs to be called by SauceLabs
//        final String browser = readPropertyOrEnv("SELENIUM_BROWSER");
//        return browser.equalsIgnoreCase("edge") ? "MicrosoftEdge" : browser;
//    }
//
//    public static String getAppiumBrowserName() {
//        return readPropertyOrEnv("APPIUM_BROWSER");
//    }
//
//    public static String getBaseUrl() {
//        return getProperty("webdriver.base.url");
//    }
//
//    public static String getImplicitTimeout() {
//        return getProperty("implicit.timeout");
//    }
//
//    public static String getDeviceName() {
//        return readPropertyOrEnv("APPIUM_DEVICE_NAME");
//    }
//
//    public static String getAppiumVersion() {
//        return readPropertyOrEnv("APPIUM_VERSION");
//    }
//
//    public static String getAppiumPlatformVersion() {
//        return readPropertyOrEnv("APPIUM_PLATFORM_VERSION");
//    }
//
//    public static String getDeviceOrientation() {
//        return readPropertyOrEnv("DEVICE_ORIENTATION");
//    }
//
//    public static String getRunner () {
//        return readPropertyOrEnv("RUNNER");
//    }
//
//    private static String getProperty(String property) {
//        if (property.isEmpty()) {
//            LOGGER.error(property + " must be provided or set.");
//            throw new NullPointerException(property + " must be provided or set.");
//        }
//
//        return vars.getProperty(property);
//    }
//
//    public static URL remoteWebDriverUrl() {
//        //Sauce lab credentials
//        String username = readPropertyOrEnv("SAUCE_USERNAME", "");
//        String accessKey = readPropertyOrEnv("SAUCE_ACCESS_KEY", "");
//        //url to connect sauce  labs
//        String endpoint = readPropertyOrEnv("SAUCE_ENDPOINT", "ondemand.eu-central-1.saucelabs.com:443/wd/hub");
//        String scheme = readPropertyOrEnv("SAUCE_ENDPOINT_SCHEME", "https");
//
//        final String endpointSpec = createEndpointSpec(scheme, username, accessKey, endpoint);
//        try {
//            return new URL(endpointSpec);
//        } catch (MalformedURLException e) {
//            throw new UnreachableBrowserException(e.getMessage());
//        }
//    }
//
//    private static String createEndpointSpec(String scheme, String username, String accessKey, String endpoint) {
//        return scheme + "://" + username + ":" + accessKey + "@" + endpoint;
//    }
//
//}
//
