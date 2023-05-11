Feature: General functionality

  Scenario: Feedback form
    When the user selects the feedback link
    Then the feedback form is displayed

  Scenario: Search for awards before 01/01/2021
    When the user selects link text 'Search for subsidies awarded before 1 January 2021.'
    Then the user is directed to historic URL

  Scenario: Enable cookies
    When the user selects link text 'Cookies'
    And the user is on the cookies page
    And the user selects to use cookies
    And selects save changes
    Then the cookies are enabled

  Scenario: Change cookie settings
    When the user selects link text 'Cookies'
    And the user is on the cookies page
    And the user selects to not use cookies
    And selects save changes
    Then the cookies are disabled

