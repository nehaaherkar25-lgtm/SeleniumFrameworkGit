Feature: Error Validations

  @ErrorValidation
  Scenario Outline: Validate login error message
    Given User landed on ecomm website
    When User logged in with "<username>" and "<password>"
    Then "Incorrect email or password." error message is displayed
    Examples:
      | username | password   |
      | n@g.com  | Neha@1234  |