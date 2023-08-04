package test.classes;

import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.House;
import model.classes.Tenant;

import java.sql.SQLException;
import java.util.List;

import static model.classes.House.logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookAccommodationT {
    Login obj;
    List<House> availableHousing;
    House selectedHouse;
    Tenant selectedTenant;

    public BookAccommodationT() {
        this.obj = new Login();
    }

    // Scenario: Book accommodation without viewing available houses
    @Given("I am a logged-in tenant")
    // public void iAmALoggedInTenant() {
    //      obj.login();
    //   }

    @When("I want to book an accommodation without viewing available houses")
    public void iWantToBookAccommodationWithoutViewingAvailableHouses() {
        try {
            availableHousing = House.getAvailableHousing();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Then("I should be able to view the available housing")
    public void iShouldBeAbleToViewTheAvailableHousing() {
        assertNotNull(availableHousing);
        assertTrue(availableHousing.size() > 0);
        House.displayAvailableHousing(availableHousing);
    }

    // Scenario: Book accommodation after viewing available houses
    @Given("I am a logged-in tenant and I have viewed available houses")
    public void iAmALoggedInTenantAndViewedAvailableHouses() {
        obj.login();
    }

    @When("I select a specific house to book")
    public void iSelectASpecificHouseToBook() throws SQLException {
        // Check if there are available houses
        availableHousing = House.getAvailableHousing();
        assertNotNull(availableHousing);
        assertTrue(availableHousing.size() > 0);

        // Replace this line with the actual house ID for testing
        int houseId = 1245;
        selectedHouse = House.getHouseDetails(houseId);
    }

    // @Then("I should be able to book the accommodation")
    // public void iShouldBeAbleToBookTheAccommodation() throws SQLException {
    //     assertNotNull(selectedHouse);
    // Implement the booking logic here, such as adding the tenant to the house's tenant list
    //   House.addTenant(selectedHouse.getId(), new Tenant(selectedHouse.getId(), result.getInt("id_apart"), result.getInt("idtenant"), 0, 0, "Tenant Name", 1234567890, "tenant@email.com"));
    //     House.updateNoOfTenant(selectedHouse.getId(), 1); // Increment the number of tenants in the house by 1
    //     House.SuccessMsg(); // Print message that the house information was updated successfully
    //}

    // Scenario: Book accommodation with invalid house ID
    @When("I try to book an accommodation with invalid house ID")
    public void iTryToBookAccommodationWithInvalidHouseID() throws SQLException {
        int invalidHouseId = 1000;
        selectedHouse = House.getHouseDetails(invalidHouseId);
    }

    @Then("I should see an error message that the house does not exist")
    public void iShouldSeeAnErrorMessageThatTheHouseDoesNotExist() throws SQLException {
        // The selectedHouse will be null for an invalid house ID
        if (selectedHouse != null) {
            House.updateNoOfTenant(selectedHouse.getId(), selectedHouse.getNoOfTenant() - 1);
            House.failMsg(); // Print message that the house ID doesn't exist
        } else {
            logger.info("House with the provided ID does not exist.");
        }
    }
}