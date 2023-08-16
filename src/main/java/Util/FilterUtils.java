package Util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import pageobjects.StepDefinitionActions;

public class FilterUtils extends StepDefinitionActions {

    //TODO - Create function here for anything that needs generating

    public static String generateRandomPublicAuthorityName(){
        return "regression-test-" + generateUUID();
    }

    public static String generateUserEmailAddress(){
        return generateUUID() + "@cgi.com";
    }

    public static Map<String, String> testData = new HashMap<String, String>() {{

        // PUBLIC TEST DATA
        put("Subsidy Control (SC) Number", "SC10128");
        put("Subsidy Scheme Name", "BB Test Scheme");
        put("Public Authority", "TEST GA");
        LocalDate dateFrom = LocalDate.parse("2000-01-01");
        LocalDate dateTo = LocalDate.parse("2022-01-01");
        put("Start Date From", String.valueOf(dateFrom));
        put("Start Date To", String.valueOf(dateTo));
        put("End Date From", String.valueOf(dateFrom));
        put("End Date To", String.valueOf(dateTo));
        put("Subsidy Date From", String.valueOf(dateFrom));
        put("Subsidy Date To", String.valueOf(dateTo));
        put("Budget From", "101");
        put("Budget To", "2000");
        put("Scheme Status", "Active");
        put("Ad Hoc", "Yes");
        put("Purpose", "Employment");
        put("Sector", "Construction");
        put("Type", "Equity");
        put("Recipient Name", "Org");
        put("SPEI Assistance", "Yes");
        put("Confirmation Date From", String.valueOf(dateFrom));
        put("Confirmation Date To", String.valueOf(dateTo));
        put("MFA Grouping Name", "Tax reduction scheme");
        put("Award Amount From", "100");
        put("Award Amount To", "1000");
        put("public authority", "TEST GA");
        put("MFA Status", "Published");

        put("Date Day From", String.valueOf(dateFrom.getDayOfMonth()));
        put("Date Month From", String.valueOf(dateFrom.getMonthValue()));
        put("Date Year From", String.valueOf(dateFrom.getYear()));
        put("Date Day To", String.valueOf(dateTo.getDayOfMonth()));
        put("Date Month To", String.valueOf(dateTo.getMonthValue()));
        put("Date Year To", String.valueOf(dateTo.getYear()));


        // ADMIN TEST DATA
        put("user first name", "regression");
        put("user last name", "test");
        put("user mobile number", "01234567890");
        put("legal basis", "-");
        put("Subsidy scheme name", "regression-test");
    }};

    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}