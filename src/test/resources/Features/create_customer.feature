Feature: Testing customer REST API
 #createcustomer fails when organization is not provided
  Scenario: create customer fails when organization is not provided
    Given I am user and entering customer details
    When organization is not provided
    Then Call create api with givens details
    And Return client error as  organization is not provided in payload post request

#createcustomer
  Scenario: create customer Success
    Given Enter customer details with organization in payload
    When organization is provided with valid id
    Then create the customer with given details