Feature: Public Portal schemes

  Background:
    Given the user clicks 'View all schemes' button
    Then the search results page should be displayed

  Scenario: Hide filters (TC_008)
    When the user selects filters button
    Then all the filters are hidden

  Scenario: Show filters (TC_008)
    When the user selects filters button
    And the user selects filters button
    Then all the filters are displayed

  Scenario: Expand all filters (TC_008)
    When the user selects 'Open all' button
    Then all scheme filters are 'expanded'

  Scenario: Close all filters (TC_008)
    When the user selects 'Open all' button
    When the user selects 'Close all' button
    Then all scheme filters are 'closed'

  Scenario: Filter by Subsidy Control (SC) Number
    When the user selects 'SCHEME' 'Subsidy Control (SC) Number' filter
    And the user inputs a valid data for 'SCHEME' 'Subsidy Control (SC) Number' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Subsidy Control (SC) Number'

  Scenario: Filter by Subsidy Scheme Name
    When the user selects 'SCHEME' 'Subsidy Scheme Name' filter
    And the user inputs a valid data for 'SCHEME' 'Subsidy Scheme Name' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Subsidy Scheme Name'

  Scenario: Filter by public authority
    When the user selects 'SCHEME' 'Public Authority' filter
    And the user inputs a valid data for 'SCHEME' 'Public Authority' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Public Authority'

  Scenario: Filter by Start Date
    When the user selects 'SCHEME' 'Start Date' filter
    And the user inputs valid date ranges for 'SCHEME' 'Start Date' filter
    And the user selects Update results
    Then the results table should only display results within the 'Start Date' provided

  Scenario: Filter by End Date
    When the user selects 'SCHEME' 'End Date' filter
    And the user inputs valid date ranges for 'SCHEME' 'End Date' filter
    And the user selects Update results
    Then the results table should only display results within the 'End Date' provided

  Scenario: Filter by public authority
    When the user selects 'SCHEME' 'Public Authority' filter
    And the user inputs a valid data for 'SCHEME' 'Public Authority' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Public Authority'

  Scenario: Filter by status
    When the user selects 'SCHEME' 'Status' filter
    And the user inputs a valid data for 'SCHEME' 'Status' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Status'

  Scenario: Filter by ad hoc
    When the user selects 'SCHEME' 'Ad Hoc' filter
    And the user inputs a valid data for 'SCHEME' 'Ad Hoc' filter
    And the user selects Update results
    Then the results table should only display 'SCHEME's with the same 'Ad Hoc'

  Scenario: 20 Results per page matches results table
    When the user select results per page '20'
    Then the table returns '20' results per page

  Scenario: 50 Results per page matches results table
    When the user select results per page '50'
    Then the table returns '50' results per page

  Scenario: 100 Results per page matches results table
    When the user select results per page '100'