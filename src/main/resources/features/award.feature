Feature: Public Portal - testing the different components of the 'view all awards' feature

  Background:
    Given the user clicks 'View all awards' button
    Then the search results page should be displayed

  Scenario: Hide filters (TC_006)
    When the user selects filters button
    Then all the filters are hidden

  Scenario: Show filters (TC_006)
    When the user selects filters button
    And the user selects filters button
    Then all the filters are displayed

  Scenario: Expand all filters (TC_006)
    When the user selects 'Open all' button
    Then all filters are 'expanded'

  Scenario: Close all filters (TC_006)
    When the user selects 'Open all' button
    When the user selects 'Close all' button
    Then all filters are 'closed'

  Scenario: Expand Purpose filter (TC_006)
    When the user selects 'AWARD' 'Purpose' filter
    Then the 'Purpose' filter is expanded

  Scenario: Select one or more 'Purpose' options from form (TC_006)
    When the user selects 'AWARD' 'Purpose' filter
    And the user selects an option from the Purpose filter
    And the user selects Update results
    Then the results table should only display awards with the same subsidy purpose

  Scenario: Expand Sector filter (TC_006)
    When the user selects 'AWARD' 'Sector' filter
    Then the 'Sector' filter is expanded

  Scenario: Select one or more 'Sector' options from form (TC_006)
    When the user selects 'AWARD' 'Sector' filter
    And the user selects an option from the Sector filter
    And the user selects Update results
    Then the results table should only display awards with the same spending sectors

  Scenario: Expand Type filter (TC_006)
    When the user selects 'AWARD' 'Type' filter
    Then the 'Type' filter is expanded

  Scenario: Select one or more 'Type' options from form (TC_006)
    When the user selects 'AWARD' 'Type' filter
    And the user selects an option from the Type filter
    And the user selects Update results
    Then the results table should only display awards with the same subsidy instrument

  #To-do come back to this and complete
#  Scenario: Filters results are exported as an Excel file and CSV (TC_006)
#    When the user selects 'AWARD' 'Purpose' filter
#    And the user selects an option from the Purpose filter
#    And the user selects Update results
#    When the user select Export as 'excel' file
#    Then the downloaded file is of type 'excel'
#    And the file contains only the filters selected

  Scenario: Select filters and then remove to to check the full list is present (TC_006)
    When the user selects 'AWARD' 'Purpose' filter
    And the user selects an option from the Purpose filter
    And the user selects Update results
    Then the results table should only display awards with the same subsidy purpose
    And the user selects an option from the Purpose filter
    And the user selects Update results
    Then the results table should display all awards

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

  Scenario: Change ordering of results table by Recipient name (TC_006)
    When the user select results per page '50'
    And the user clicks 'beneficiary' name ordering arrows
    Then the tables results are re-ordered by 'beneficiary' column name

  Scenario: Change ordering of results table by Subsidy date (TC_006)
    When the user select results per page '50'
    And the user clicks 'beneficiary' name ordering arrows
    And the user clicks 'legalgrantingdate' name ordering arrows
    Then the tables results are re-ordered by legal granting date column name

  #To-do come back to this and complete - Steps created by the logic needs implementing
#  Scenario: Print award details
#    When the user clicks on recipient name link
#    Then the user is taken to the awards details page
#    And the user selects Print page
#    And the user clicks save
#    Then the awards page is saved as a pdf





