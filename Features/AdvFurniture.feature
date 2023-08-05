Feature: Advertise Used Furniture

  Scenario: tenant want to advertise his own used furniture for sale.
    Given the tenant is logged in
    And tenant enter the furniture_name "Sofa", description "Comfortable sofa for sale", price 250.0
    Then the advertisement will be added to the database in system
    And other tenants can see it