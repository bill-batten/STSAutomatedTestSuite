
  # Given steps are used to describe the initial context of the system - the scene of the scenario. It is typically something that happened in the past.
  # When steps are used to describe an event, or an action. This can be a person interacting with the system, or it can be an event triggered by another system.
  # Then steps are used to describe an expected outcome, or result.

  Feature: Public Portal - testing the different components of the search feature

    Background:
      Given the user is on the homepage
      Then the 'homepage' results page should be displayed

    # TODO
      # Need to rename all of the scenarios to the correct convention
    Scenario: Search without any filters
      When the user clicks 'Start search' button
      And the user selects yes
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the 'awards' results page should be displayed
      And the user clicks new search
      Then the user is on the homepage

    Scenario: Search awards with specific subsidy purpose
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects a subsidy search 'Purpose'
      And the user selects Continue
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the 'awards' results page should be displayed
      Then the results table should only display results with the same 'Purpose'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: Search awards with specific subsidy sector
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects a subsidy search 'Sector'
      And the user selects Continue
      And the user selects Continue
      And the user selects Show results
      Then the 'awards' results page should be displayed
      Then the results table should only display results with the same 'Sector'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: Search awards with specific subsidy type
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects a subsidy search 'Type'
      And the user selects Continue
      And the user selects Show results
      Then the 'awards' results page should be displayed
      Then the results table should only display results with the same 'Type'
      And the user clicks new search
      Then the user is on the homepage

    Scenario: Search all awards within specific date range
      When the user clicks 'Start search' button
      And the user selects No
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects Select all
      And the user selects Continue
      And the user selects 'Yes' for award period
      And the user inputs valid dates
      And the user selects Show results
      Then the 'awards' results page should be displayed
      Then the results table should only display awards within the dates provided
      And the user clicks new search
      Then the user is on the homepage






