Feature: Purchase order from an ecommerce website

  Background:
    Given User landed on ecomm website

  Scenario Outline:
    Given User logged in with "<username>" and "<password>"
    When User adds "<productName>" to cart
    And Checkout "<productName>" and Submit the order
    Then "THANKYOU FOR THE ORDER." is displayed on confirmation page
    Examples:
    | username    | password   | productName  |
    | neha@g.com  | Neha@1234  | ZARA COAT 3  |