Feature: Tenant Control Panel

  Scenario: View tenant control panel after booking
    Given I am a logged-in tenant
    And I have booked accommodation
    When I access the tenant control panel
    Then I should see my personal data
    And I should see the name of the residence owner
    And I should see the residence owner's contact information
    And I should see when the rent is paid (time to pay)

  Scenario: View tenant control panel without booking
    Given I am a logged-in tenant
    And I have not booked any accommodation
    When I try to access the tenant control panel
    Then I should receive an error message indicating no booking



