Feature: Public Portal - testing the different components of the 'View all MFA / SPEI Assistance awards' feature

  Background:
    Given the user clicks 'View all MFA/SPEI' button
    Then the 'MFA / SPEIA award' results page should be displayed

  Scenario: Hide filters
    When the user selects filters button
    Then all the filters are hidden

  Scenario: Show filters
    When the user selects filters button
    And the user selects filters button
    Then all the filters are displayed

  Scenario: Expand all filters
    When the user selects open or close all filters button
    Then all filters are 'expanded'

  Scenario: Close all filters
    When the user selects open or close all filters button
    When the user selects open or close all filters button
    Then all filters are 'closed'

  Scenario: Filter by Recipient Name
    When the user selects 'Recipient Name' filter
    And the user selects an option from the 'Recipient Name' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Recipient Name'

  Scenario: Filter by SPEI Assistance
    When the user selects 'SPEI Assistance' filter
    And the user selects an option from the 'SPEI Assistance' filter
    And the user selects Update results
    Then the results table should only display results with the same 'SPEI Assistance'

  Scenario: Filter by Confirmation Date
    When the user selects 'Confirmation Date' filter
    And the user inputs valid date ranges for 'Confirmation' date filter
    And the user selects Update results
    Then the results table should only display results within the 'Confirmation Date' provided

  Scenario: Filter by MFA Grouping Name
    When the user selects 'MFA Grouping Name' filter
    And the user selects an option from the 'MFA Grouping Name' filter
    And the user selects Update results
    Then the results table should only display results with the same 'MFA Grouping Name'

  Scenario: Filter by Award Amount
    When the user selects 'Award Amount' filter
    And the user inputs a valid data ranges for 'Award Amount' filter
    And the user selects Update results
    Then the results table should only display results with the 'Award Amount' range

  Scenario: Filter by Public Authority
    When the user selects 'Public Authority' filter
    And the user selects an option from the 'Public Authority' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Public Authority'

  Scenario: Filter by Status
    When the user selects 'Status' filter
    And the user selects an option from the 'MFA Status' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Status'

  @export-file
  Scenario: Export MFA award's as excel file
    When the user selects 'Public Authority' filter
    And the user selects an option from the 'Public Authority' filter
    And the user selects Update results
    And the user selects export as 'Excel' file button
    Then the 'Excel' file should be downloaded to the downloads folder
    And the number of results inside the file should match the number of results on the page

  @export-file
  Scenario: Export MFA award's as csv file
    When the user selects 'SPEI Assistance' filter
    And the user selects an option from the 'SPEI Assistance' filter
    And the user selects Update results
    And the user selects export as 'csv' file button
    Then the 'csv' file should be downloaded to the downloads folder
    And the number of results inside the file should match the number of results on the page

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