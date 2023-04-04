
  # Given steps are used to describe the initial context of the system - the scene of the scenario. It is typically something that happened in the past.
  # When steps are used to describe an event, or an action. This can be a person interacting with the system, or it can be an event triggered by another system.
  # Then steps are used to describe an expected outcome, or result.
  #
  Feature: Public Portal - testing the different components of the search feature

    //Refactor complete
    Scenario: TC_001
      When the user clicks 'Start search' button
      And the user selects yes
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the search results page should be displayed
      And the user clicks new search
      Then the user is on the homepage

    Scenario: TC_002
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects a subsidy purpose
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the search results page should be displayed
      Then the results table should only display results with the same 'Purpose'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: TC_003
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all for 'Purpose'
      And the user selects Continue
      And the user selects a spending sector
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the search results page should be displayed
      Then the results table should only display results with the same 'Sector'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: TC_004
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all for 'Purpose'
      And the user selects Continue
      And the user selects Select all for 'Sector'
      And the user selects Continue
      And the user selects a subsidy type
      And the user selects Continue
      And the user selects Show results
      Then the search results page should be displayed
      Then the results table should only display results with the same 'Type'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: TC_005
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all for 'Purpose'
      And the user selects Continue
      And the user selects Select all for 'Sector'
      And the user selects Continue
      And the user selects Select all for 'Type'
      And the user selects Continue
      And the user selects 'Yes' for award period
      And the user inputs valid dates
      And the user selects Show results
      Then the search results page should be displayed
      Then the results table should only display awards within the dates provided
      And the user clicks new search
      Then the user is on the homepage






