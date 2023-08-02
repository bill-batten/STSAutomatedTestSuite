@admin-portal
Feature: Admin Portal - testing the different components of the 'Subsidy schemes' feature

#  Background:
#    Given the user logs in as 'BEIS Admin' successfully
#    And the user clicks 'Subsidy schemes' navigation link
#    Then the 'Subsidy Schemes search page' page should be displayed


    Scenario:
      Given the user logs in as 'BEIS Admin' successfully
      And the user clicks 'Subsidy schemes' navigation link
      When the user selects 'Add a new subsidy scheme' button