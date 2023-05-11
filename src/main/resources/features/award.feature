Feature: Public Portal - testing the different components of the 'view all awards' feature

  Background:
    Given the user clicks 'View all awards' button
    Then the 'awards' results page should be displayed

  Scenario: Hide filters (TC_006)
    When the user selects filters button
    Then all the filters are hidden

  Scenario: Show filters (TC_006)
    When the user selects filters button
    And the user selects filters button
    Then all the filters are displayed

  Scenario: Expand all filters (TC_006)
    When the user selects open or close all filters button
    Then all filters are 'expanded'

  Scenario: Close all filters (TC_006)
    When the user selects open or close all filters button
    When the user selects open or close all filters button
    Then all filters are 'closed'

  Scenario: Expand Purpose filter (TC_006)
    When the user selects 'Purpose' filter
    Then the 'Purpose' filter is expanded

  Scenario: Select one or more 'Purpose' options from form (TC_006)
    When the user selects 'Purpose' filter
    And the user selects an option from the 'Purpose' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Purpose'

  Scenario: Expand Sector filter (TC_006)
    When the user selects 'Sector' filter
    Then the 'Sector' filter is expanded

  Scenario: Select one or more 'Sector' options from form (TC_006)
    When the user selects 'Sector' filter
    And the user selects an option from the 'Sector' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Sector'

  Scenario: Expand Type filter (TC_006)
    When the user selects 'Type' filter
    Then the 'Type' filter is expanded

  Scenario: Select one or more 'Type' options from form (TC_006)
    When the user selects 'Type' filter
    And the user selects an option from the 'Type' filter
    And the user selects Update results
    Then the results table should only display results with the same 'Type'

  @export-file
  Scenario: Export awards as excel file
    When the user selects 'Purpose' filter
    And the user selects an option from the 'Purpose' filter
    And the user selects Update results
    And the user selects export as 'Excel' file button
    And the 'Excel' file should be downloaded to the downloads folder
    Then the number of results inside the file should match the number of results on the page

  @export-file
  Scenario: Export awards as csv file
    When the user selects 'Type' filter
    And the user selects an option from the 'Type' filter
    And the user selects Update results
    And the user selects export as 'csv' file button
    And the 'csv' file should be downloaded to the downloads folder
    Then the number of results inside the file should match the number of results on the page


  Scenario: Results per page matches the results table (TC_006)
    When the user select results per page '10'
    And the user selects next page
    Then the table returns '10' results per page
    And the user selects next page
    Then the table returns '10' results per page

  Scenario: 20 Results per page matches results table (TC_006)
    When the user select results per page '20'
    Then the table returns '20' results per page

  Scenario: 50 Results per page matches results table (TC_006)
    When the user select results per page '50'
    Then the table returns '50' results per page

  Scenario: 100 Results per page matches results table (TC_006)
    When the user select results per page '100'
    Then the table returns '100' results per page

  Scenario: Checks that the number of results is 100 per page but accepts less results (TC_006)
    When the user selects 'Sector' filter
    And the user selects an option from the 'Sector' filter
    And the user selects Update results
    When the user select results per page '100'
    Then the table returns '100' results per page

  Scenario: Order the results table into ascending order by Recipient name (TC_006)
    When the user select results per page '50'
    And the user clicks 'Recipient Name' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Recipient Name' column name

  Scenario: Order the results table into descending order by Recipient name (TC_006)
    When the user select results per page '50'
    And the user clicks 'Recipient Name' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Recipient Name' column name

  Scenario: Order the results table into ascending order by Subsidy date (TC_006)
    When the user select results per page '50'
    And the user clicks 'Subsidy Date' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Subsidy Date' column name

  Scenario: Order the results table into descending order by Subsidy date (TC_006)
    When the user select results per page '50'
    And the user clicks 'Subsidy Date' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Subsidy Date' column name