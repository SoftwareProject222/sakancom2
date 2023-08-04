Feature: View Housing

  Scenario: View available housing
    Given I am a logged-in tenant
    When I access the housing section
    Then I should be able to view the available housing


  Scenario: View housing details as a logged-in tenant
    Given I am a logged-in tenant
    When I select a specific housing
    Then I should be able to see the pictures of the housing \\ مروة قالت نمسحها
    And I should be able to see the prices of the housing
    And I should be able to see the location of the housing
    And I should be able to see the services available in the housing




