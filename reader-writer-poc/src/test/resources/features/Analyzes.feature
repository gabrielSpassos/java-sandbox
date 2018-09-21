Feature: Check the analyzes

  @Analyses
  Scenario: The analyzes about the relatory are made correcly
    Given a relatory
    When the analyses are made
    Then should create a correct result