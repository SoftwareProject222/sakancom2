Feature: Book Accommodation

  Scenario: View available housing before booking
    Given I am a logged-in tenant
    When I access the housing section
    Then I should be able to view the available housing
    And each apartment should have an apartment and an ID house

  Scenario: Book accommodation in a non-student apartment with available space
    Given I am a logged-in tenant
    And I have selected a non-student apartment with available space (4 or 5 tenants)
    When I proceed to book accommodation
    Then I should receive a booking confirmation
    And the apartment should be updated with my booking information

  Scenario: Book accommodation in a non-student apartment with no available space
    Given I am a logged-in tenant
    And I have selected a non-student apartment with no available space (already occupied by 4 or 5 tenants)
    When I try to book accommodation
    Then I should receive a message indicating that the apartment is full

  Scenario: Book accommodation in a student apartment with available space
    Given I am a logged-in tenant
    And I have selected a student apartment with available space (4 or 5 tenants)
    When I proceed to book accommodation
    Then I should receive a booking confirmation
    And the apartment should be updated with my booking information

  Scenario: Book accommodation in a student apartment with no available space
    Given I am a logged-in tenant
    And I have selected a student apartment with no available space (already occupied by 4 or 5 tenants)
    When I try to book accommodation
    Then I should receive a message indicating that the apartment is full



  Scenario: View student apartment information
    Given I am a logged-in tenant
    And I have selected a student apartment
    When I access the information section
    Then I should see the ages of the people living in it
    And I should see the university majors of the people living in it