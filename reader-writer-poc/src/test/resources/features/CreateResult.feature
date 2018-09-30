Feature: Read file and create result

  @Process
  Scenario: Read file with sucess and create appropriate result
    Given a relatory.dat file on data/in folder
    Then must create a result with correct information