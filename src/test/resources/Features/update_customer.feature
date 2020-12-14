Feature: Testing customer update REST API
 #updatecustomer fails when organization is not provided
  Scenario: update customer fails when customerId not provided
    Given I am user and entering update details
    When customerId is not provided
    Then Call update api with givens details
    And Return client error as  update is not provided in payload put request

#createcustomer
  Scenario: update customer Success
    Given Enter customer details with valid customerId in payload
    When update details is provided with valid id
    Then update the customer with given details