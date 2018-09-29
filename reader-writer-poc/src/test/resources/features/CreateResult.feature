Feature: Read file and create result

  @Process
  Scenario: Read file with sucess and create appropriate result
    Given a relatory.dat file on test/data/in folder
    When start the server
    Then must create a result with correct information