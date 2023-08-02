@public-portal
Feature: Public Portal schemes

  Background:
    Given the user clicks 'View all schemes' button
    Then the 'schemes' results page should be displayed

  Scenario: Hide filters
    When the user selects 'Hide filters' button
    Then all the filters are hidden

  Scenario: Show filters
    When the user selects 'Hide filters' button
    And the user selects 'Show filters' button
    Then all the filters are displayed

  Scenario: Expand all filters
    When the user selects open or close all filters button
    Then all filters are 'expanded'

  Scenario: Close all filters
    When the user selects open or close all filters button
    And the user selects open or close all filters button
    Then all filters are 'closed'

  Scenario: Filter by Subsidy Control (SC) Number
    When the user selects 'Subsidy Control (SC) Number' filter
    And the user selects an option from the 'Subsidy Control (SC) Number' filter
    And the user selects 'Update results' button
    Then the results table should only display results with the same 'Subsidy Control (SC) Number'

  Scenario: Filter by Subsidy Scheme Name
    When the user selects 'Subsidy Scheme Name' filter
    And the user selects an option from the 'Subsidy Scheme Name' filter
    And the user selects 'Update results' button
    Then the results table should only display results with the same 'Subsidy Scheme Name'

  Scenario: Filter by public authority
    When the user selects 'Public Authority' filter
    And the user selects an option from the 'Public Authority' filter
    And the user selects 'Update results' button
    Then the results table should only display results with the same 'Public Authority'

  Scenario: Filter by Start Date
    When the user selects 'Start Date' filter
    And the user inputs valid date ranges for 'Start' date filter
    And the user selects 'Update results' button
    Then the results table should only display results within the 'Start Date' provided

  Scenario: Filter by End Date
    When the user selects 'End Date' filter
    And the user inputs valid date ranges for 'End' date filter
    And the user selects 'Update results' button
    Then the results table should only display results within the 'End Date' provided

  Scenario: Filter by budget
    When the user selects 'Budget' filter
    And the user inputs a valid data ranges for 'Budget' filter
    And the user selects 'Update results' button.
    Then the results table should only display results with the 'Budget' range

  Scenario: Filter by status
    When the user selects 'Status' filter
    And the user selects an option from the 'Scheme Status' filter
    And the user selects 'Update results' button
    Then the results table should only display results with the same 'Status'

  @export-file
  Scenario: Export schemes as excel file
    When the user selects 'Subsidy Scheme Name' filter
    And the user selects an option from the 'Subsidy Scheme Name' filter
    And the user selects 'Update results' button
    And the user selects export as 'Excel' file button
    Then the 'Excel' file should be downloaded to the downloads folder
    And the number of results inside the file should match the number of results on the page

  @export-file
  Scenario: Export schemes as csv file
    When the user selects 'Start Date' filter
    And the user inputs valid date ranges for 'Start' date filter
    And the user selects 'Update results' button
    And the user selects export as 'csv' file button
    Then the 'csv' file should be downloaded to the downloads folder
    And the number of results inside the file should match the number of results on the page

  Scenario: Filter by ad hoc
    When the user selects 'Ad Hoc' filter
    And the user selects an option from the 'Ad Hoc' filter
    And the user selects 'Update results' button
    Then the results table should only display results with the same 'Ad Hoc'

  Scenario: 20 Results per page matches results table
    When the user select results per page '20'
    Then the table returns '20' results per page

  Scenario: 50 Results per page matches results table
    When the user select results per page '50'
    Then the table returns '50' results per page

  Scenario: 100 Results per page matches results table
    When the user select results per page '100'
    Then the table returns '100' results per page

  Scenario: Results per page matches the results table
    When the user select results per page '10'
    And the user selects next page
    Then the table returns '10' results per page
    And the user selects next page
    Then the table returns '10' results per page