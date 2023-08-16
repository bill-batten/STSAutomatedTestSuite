@admin-portal
Feature: Admin Portal - testing the different components of the 'Subsidy schemes' feature

  Background:
    Given the user clicks 'Subsidy schemes' navigation link
    Then the 'Subsidy Schemes' page should be displayed

  #TODO - Create scheme
  @publicAuthorityData
  Scenario: Add subsidy scheme successfully
    When the user selects 'Add a new subsidy scheme' button

    #TODO - Figure out how to get the date into all of the fields
    And the user fills in the form with the following data:
      | Field         | Value           |
      | Public authority name | regression-test-scheme-public-authority |
      | Subsidy scheme name | regression test |
      | Subsidy scheme description | regression test text |
      | Legal basis                | regression test text |
      | Public authority policy URL | regression test text |
      | Public authority policy page description | regression test text |
      | Budget                      | 100                  |
      | Maximum amount given under scheme | regression test text |
      | Confirmation Date                 | 01-01-2000           |
      | Start Date                        | 01-01-2000           |
      | End Date                          | 01-01-2020           |
    And the user selects the following checkboxes:
      | Select one or more sector(s) | Real estate activities |
    And the user selects 'Publish subsidy scheme' button
    Then the 'Subsidy scheme published' success page should be displayed

    #TODO - Deactivate scheme

    #TODO - Delete scheme

    #TODO - Search scheme

    #TODO - Check error validation for scheme creation




