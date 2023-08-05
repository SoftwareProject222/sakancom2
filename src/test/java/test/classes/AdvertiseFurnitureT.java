package test.classes;
import code.classes.Login;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.Tenant;

import java.sql.SQLException;

public class AdvertiseFurnitureT {
    Tenant tenant;
    private int furnitureId; // Used to store the ID of the advertised furniture

    @Before
    public void setUp() {
        // Create a sample tenant with some data
        tenant = new Tenant(1, 0, 0, "Leen", 1234567890, "leenbatt@example.com", 25, "Computer Science");
    }

    @Given("I'm a logged-in Tenant")
    public void iAmALoggedInTenant() {
        // The tenant object is already initialized in the @Before hook, no need to create it again here
    }

    @When("I access the special window for furniture")
    public void iAccessTheSpecialWindowForFurniture() {
        // No action needed as this is just a placeholder for testing purposes.
        // The assumption is that the tenant has accessed the special window for furniture.
    }

    @Then("I should be able to advertise my own used furniture for sale")
    public void iShouldBeAbleToAdvertiseMyOwnUsedFurnitureForSale() throws SQLException {
        String furnitureName = "Sofa";
        String description = "Comfortable sofa for sale.";
        double price = 250.0;
        // Advertise the furniture using the tenant's information
        furnitureId = tenant.advertiseFurniture(furnitureName, description, price);
        assert (furnitureId > 0); // Furniture ID should be greater than 0 if the advertisement is successful
}
    }

