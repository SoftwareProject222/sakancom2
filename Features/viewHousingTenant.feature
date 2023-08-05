Feature: View Housing

  Scenario: tenant see advertisements of available housing
    Given the tenant logged in
    When he select to see the advertisements of houses
    Then a page will appear to see them


  Scenario: tenant see details of housing
    Given the tenant logged in
    When he select to see the houses in the system
    Then a table of houses information will printed


  Scenario: tenant see available apartments of housing
    Given the tenant logged in
    When he select to see the available apartments in the system
    Then a table of apartments information will printed


  Scenario: tenant see information about student housing
    Given the tenant logged in
    When he select to see a student apartments
    Then a table of apartments, age and major will printed