Feature: Book Accommodation

  Scenario: tenant want to book accommodation via the app
    Given the tenant is logged in the system
    When he want to book house, the available apartments will printed
    And  he enter his email "karamabuSa3@gmail.com", name "Karam Nasir", contactInfo "karamabuSa3@gmail.com, 0599440012", id_house 1127, id_apart 1701
    Then the reservation process has been completed successfully
    And  a control panel show information about the reservation process will appear


