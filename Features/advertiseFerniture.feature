Feature: Advertise Used Furniture

  Scenario: View the special window for furniture as a logged-in tenant
    Given I am a logged-in tenant
    When I access the special window for furniture
    Then I should be able to advertise my own used furniture for sale

