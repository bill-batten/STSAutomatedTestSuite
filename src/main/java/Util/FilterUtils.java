package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import pageobjects.StepDefinitionActions;

public class FilterUtils extends StepDefinitionActions {

    public static final Map<String, String> testData = new HashMap<String, String>() {{

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
        put("Public Authority", "TEST GA");
        put("MFA Status", "Published");

    }};


//    public static final Map<String, String> AWARD_FILTER_IDS = new HashMap<String, String>() {{
//        put("Purpose", "accordion-default-heading-1");
//        put("Sector", "accordion-default-heading-2");
//        put("Type", "accordion-default-heading-3");
//
//    }};
//
//    public static final Map<String, String> AWARD_FILTER_INPUT_FIELD_IDS = new HashMap<String, String>() {{
//        //Purpose
//        put("Purpose Select all", "subsidyobjective-0");
//        put("Culture or Heritage", "subsidyobjective-1");
//        put("Employment", "subsidyobjective-2");
//        put("Energy efficiency", "subsidyobjective-3");
//        put("Environmental protection", "subsidyobjective-4");
//        put("Infrastructure", "subsidyobjective-5");
//        put("Regional development", "subsidyobjective-5");
//        put("Rescue aid", "subsidyobjective-6");
//        put("Research and development", "subsidyobjective-7");
//        put("Services of Public Economic Interest", "subsidyobjective-8");
//        put("SME (Small/Medium-sized enterprise) support", "subsidyobjective-9");
//        put("Training", "subsidyobjective-10");
//        put("Purpose Other", "subsidyobjective-11");
//
//        //Sector
//        put("Sector Select all", "spendingsector-0");
//        put("Accommodation and food service activities", "spendingsector-accommodation");
//        put("Activities of extraterritorial organisations and bodies", "spendingsector-activities-of-extraterritorial");
//        put("Activities of households as employers; undifferentiated goods- and services-producing activities of households for own use", "spendingsector-undifferentiated-goods");
//        put("Administrative and support service activities", "spendingsector-administrative");
//        put("Agriculture, forestry and fishing", "spendingsector-agriculture-forestry-and-fishing");
//        put("Arts, entertainment and recreation", "spendingsector-arts-entertainment");
//        put("Construction", "spendingsector-construction");
//        put("Education", "spendingsector-education");
//        put("Electricity, gas, steam and air conditioning supply", "spendingsector-Electricity-gas-steam-and-air-conditioning-supply");
//        put("Financial and insurance activities", "spendingsector-financial-and-insurance-activities");
//        put("Human health and social work activities", "spendingsector-human-health");
//        put("Information and communication", "spendingsector-information-and-communication");
//        put("Manufacturing", "spendingsector-Manufacturing");
//        put("Mining and quarrying", "spendingsector-mining-and-quarrying");
//        put("Other service activities", "spendingsector-Other-service-activities");
//        put("Professional, scientific and technical activities", "spendingsector-professional");
//        put("Public administration and defence; compulsory social security", "spendingsector-public-administration");
//        put("Real estate activities", "spendingsector-real-estate-activities");
//        put("Transportation and storage", "spendingsector-transportation-and-storage");
//        put("Water supply; sewerage, waste management and remediation activities", "spendingsector-water-supply");
//        put("Wholesale and retail trade; repair of motor vehicles and motorcycles", "spendingsector-wholesale-and-retail-trade");
//
//        //Type
//        put("Type Select all", "subsidyinstrument-0");
//        put("Direct Grant", "subsidyinstrument");
//        put("Equity", "subsidyinstrument-1");
//        put("Guarantee", "subsidyinstrument-2");
//        put("Loan", "subsidyinstrument-3");
//        put("Provision of goods or services below market prices", "subsidyinstrument-4");
//        put("Purchase of goods or services above market prices", "subsidyinstrument-5");
//        put("Tax measures (tax credit, or tax/duty exemption)", "subsidyinstrument-6");
//        put("Type Other", "subsidyinstrument-7");
//
//    }};
//
//    public static final Map<String, String> SCHEME_FILTER_IDS = new HashMap<String, String>() {{
//        put("Subsidy Control (SC) Number", "accordion-default-heading-1");
//        put("Subsidy Scheme Name", "accordion-default-heading-2");
//        put("Public Authority", "accordion-default-heading-6");
//        put("Start Date", "accordion-default-heading-4");
//        put("End Date", "accordion-default-heading-5");
//        put("Budget", "accordion-default-heading-6");
//        put("Scheme Status", "accordion-default-heading-7");
//        put("Ad Hoc", "accordion-default-heading-8");
//    }};
//
//    public static final Map<String, String> SCHEME_FILTER_INPUT_FIELD_IDS = new HashMap<String, String>() {{
//        put("Subsidy Control (SC) Number", "filter-scnumber");
//        put("Subsidy Scheme Name", "filter-name");
//        put("Public Authority", "filter-ga");
//
//        put("Start Date Day From", "filter-start-day-from");
//        put("Start Date Month From", "filter-start-month-from");
//        put("Start Date Year From", "filter-start-year-from");
//        put("Start Date Day To", "filter-start-day-to");
//        put("Start Date Month To", "filter-start-month-to");
//        put("Start Date Year To", "filter-start-year-to");
//
//        put("End Date Day From", "filter-end-day-from");
//        put("End Date Month From", "filter-end-month-from");
//        put("End Date Year From", "filter-end-year-from");
//        put("End Date Day To", "filter-end-day-to");
//        put("End Date Month To", "filter-end-month-to");
//        put("End Date Year To", "filter-end-year-to");
//
//        put("Budget", "filter-budget");
//        put("Scheme Status", "filter-status");
//        put("Ad Hoc", "filter-adhoc");
//    }};
//
//    public static final Map<String, String> MFA_SPEI_FILTER_IDS = new HashMap<String, String>() {{
//        put("Recipient Name", "accordion-default-heading-1");
//        put("SPEI Assistance", "accordion-default-heading-2");
//        put("Confirmation Date", "accordion-default-heading-3");
//        put("MFA Grouping Name", "accordion-default-heading-4");
//        put("Award Amount", "accordion-default-heading-5");
//        put("Public Authority", "accordion-default-heading-6");
//        put("MFA Status", "accordion-default-heading-7");
//    }};
//
//    public static final Map<String, String> MFA_SPEI_FILTER_INPUT_IDS= new HashMap<String, String>() {{
//        put("Recipient Name", "filter-recipient");
//        put("SPEI Assistance", "filter-speia");
//
//        put("Confirmation Date Day From", "filter-confirmation-day-from");
//        put("Confirmation Date Month From", "filter-confirmation-month-from");
//        put("Confirmation Date Year From", "filter-confirmation-year-from");
//        put("Confirmation Date Day To", "filter-confirmation-day-to");
//        put("Confirmation Date Month To", "filter-confirmation-month-to");
//        put("Confirmation Date Year To", "filter-confirmation-year-to");
//
//        put("MFA Grouping Name", "filter-mfa-grouping");
//
//        put("Award Amount From", "filter-amount-from");
//        put("Award Amount To", "filter-amount-to");
//
//        put("Public Authority", "filter-ga");
//        put("MFA Status", "filter-status");
//    }};
}
