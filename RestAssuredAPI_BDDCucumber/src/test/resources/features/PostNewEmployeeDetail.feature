Feature: Post the new employee
  Background:
    Given the API is available

  Scenario: Create a new employee details and validate
    When I post the new employee details to "/employeedetails"
    And I send the request body from Json file "NewEmployeeDetailRequest.json"
    Then the response status code for new employee should be 200
    And the response should match the new employee schema "NewEmployeeSchema.json"
    And the response should match the new employee expected json "NewEmployeeExpected.json"

  Scenario: update the employee detail by employee id
    When I update the employee detail by employee id  to "/employeedetails/105"
    And I send the updated request from Json file "UpdateEmployeeDetail.json"
    Then the response status code for the updated employee should be 200
    And the response should match the updated employee schema "UpdatedEmployeeSchema.json"
    And the response should match the updated employee expected json "UpdatedEmployeeExpected.json"

  Scenario: Get all employee list of details
    When I send the get request "/employeedetails/"
    Then the response status code should be 200
    And the response should match the schema "GetAllEmployeeSchema.json"
    And the response should match the expected JSON "GetAllEmployeesExpected.json"

  Scenario: Delete the employee detail by employee Id
    When I delete the employee detail by employee id to "/employeedetails/107"
    Then the response status code for deleted employee should be 200
    And the response should match the deleted employee schema "DeletedEmployeeSchema.json"
    And the response should match the deleted employee expected json "DeletedEmployeeExpected.json"

