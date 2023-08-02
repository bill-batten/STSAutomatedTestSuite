@admin-portal
@granting-authority
Feature: Admin Portal - testing the different components of the 'Public authorities' (Granting authorities) feature

  Background:
    Given the user logs in as 'BEIS Admin'
    And the user clicks 'Public Authorities' navigation link
    Then the 'Public Authority page' page should be displayed

  Scenario: Create new public authority (TC_001)
    When the user selects 'Add a new public authority' button
    And the user inputs a valid 'public authority name' into text field 'Public authority name'
    And the user selects 'Continue' button
    And the user selects 'Save' button
    Then the 'Public authority added successfully page' page should be displayed

  Scenario: Create and edit new public authority (TC_002)
    When the user selects to add a new public authority
    And the user selects 'Change' link text button
    And the user selects 'Cancel' button
    And the user selects 'No' button
    And the user selects 'Cancel' button
    And the user selects 'Yes' button
    Then the 'Public Authority page' page should be displayed

  Scenario: Deactivate public authority (create a test user to deactivate) (TC_003)
    When the user selects to add a new public authority
    And the user selects 'Save' button
    And the user selects 'View all public authorities' button
    And the user deactivates the public authority
    Then the status of the public authority should be inactive

  Scenario: Attempt to deactivate a public authority with active users (TC_004)
    When the user selects to add a new public authority
    And the user selects 'Save' button
    And the user creates a test user
    And the user deactivates the public authority
    Then the status of the public authority should be inactive

  Scenario: Search GA by name
    When the user searches for PA by 'public authority name'
    Then the filtered table contains matching 'public authority name'

  Scenario: Search GA by Id
    When the user searches for PA by 'id'
    Then the filtered table contains matching 'id'

  Scenario: Filter Inactive GA
    When the user filters by 'Inactive'
    Then the filtered table should only contain 'Inactive' 'Status'

  Scenario: Filter Active GA
    When the user filters by 'Active'
    Then the filtered table should only contain 'Active' 'Status'

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

  #TODO - Runtime can be reduced by combining ascending and descending into the same scenario

  Scenario: Order granting authorities in ascending order by Public Authority ID
    When the user clicks 'Public Authority ID' ordering arrow by 'ascending' order
    And the tables results are ordered in 'ascending' order by 'Public Authority ID' column name

  Scenario: Order granting authorities in descending order by Public Authority ID
    When the user clicks 'Public Authority ID' ordering arrow by 'descending' order
    And the tables results are ordered in 'descending' order by 'Public Authority ID' column name

  Scenario: Order granting authorities in ascending order  by Public Authority name
    When the user clicks 'Public Authority name' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Public Authority name' column name

  Scenario: Order granting authorities in descending order  by Public Authority name
    When the user clicks 'Public Authority name' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Public Authority name' column name

  Scenario: Order granting authorities in ascending order by Added by
    When the user clicks 'Added by' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Added by' column name

  Scenario: Order granting authorities in descending order by Added by
    When the user clicks 'Added by' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Added by' column name

  Scenario: Order granting authorities in ascending order by Status
    When the user clicks 'Status' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Status' column name

  Scenario: Order granting authorities in descending order by Status
    When the user clicks 'Status' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Status' column name

  Scenario: Order granting authorities in ascending order by Created on
    When the user clicks 'Created on' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Created on' column name

  Scenario: Order granting authorities in descending order by Created on
    When the user clicks 'Created on' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Created on' column name

  Scenario: Order granting authorities in ascending order by Last modified
    When the user clicks 'Last modified' ordering arrow by 'ascending' order
    Then the tables results are ordered in 'ascending' order by 'Last modified' column name

  Scenario: Order granting authorities in descending order by Last modified
    When the user clicks 'Last modified' ordering arrow by 'descending' order
    Then the tables results are ordered in 'descending' order by 'Last modified' column name

  Scenario: Adding granting authority invalid error validation
    When the user adds an 'invalid' granting authority
    Then the user should be presented with a 'Enter a public authority name' error

  Scenario: Adding granting authority inactive error validation
    When the user adds an 'inactive' granting authority
    Then the user should be presented with a 'Public Authority already added' error

  Scenario: Adding granting authority duplicate error validation
    When the user adds an 'duplicate' granting authority
    Then the user should be presented with a 'Public Authority already added' error
