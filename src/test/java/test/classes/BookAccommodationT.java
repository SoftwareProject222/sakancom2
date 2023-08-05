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
    @Given("I am a logged-in tenant in booking section")
    public void iAmALoggedInTenantInBooking() {
        obj.login();
    }

    @When("I access the housing section directly without viewing available housing")
    public void iAccessTheHousingSectionDirectlyWithoutViewingAvailableHousing() {
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

    @Given("I have selected a non-student apartment with available space \\({int} or {int} tenants)")
    public void iHaveSelectedANonStudentApartmentWithAvailableSpaceOrTenants(Integer int1, Integer int2) throws SQLException {
        int houseId = 1124;
        selectedHouse = House.getHouseDetails(houseId);
    }
    @When("I proceed to book accommodation")
    public void iProceedToBookAccommodationNonStudent() throws SQLException {
        assertNotNull(selectedHouse);
        // Implement the booking logic here, such as adding the tenant to the house's tenant list
        Tenant tenant = new Tenant(selectedHouse.getId(), 1403, 1456, "Leen", 1234567890, "leenbatt@example.com",  25, "Computer Science");
        House.addTenant(selectedHouse.getId(), tenant);
        House.updateNoOfTenant(selectedHouse.getId(), selectedHouse.getNoOfTenant() + 1); // Increment the number of tenants in the house by 1
        // Update apartment information
        selectedHouse.setNoOfTenant(selectedHouse.getNoOfTenant() + 1);
        selectedHouse.getTenants().add(tenant);
    }

    @Then("I should receive a booking confirmation")
    public void iShouldReceiveABookingConfirmationNonStudent() {
        assertNotNull(selectedHouse.getTenants());
        assertTrue(selectedHouse.getTenants().size() > 0);
        assertTrue(selectedHouse.getTenants().stream().anyMatch(tenant -> tenant.getName().equals("Leen")));
        // Add additional assertions or verifications based on your application's logic
    }

    @Then("the apartment should be updated with my booking information")
    public void theApartmentShouldBeUpdatedWithMyBookingInformationNonStudent() {
        assertNotNull(selectedHouse.getTenants());
        assertTrue(selectedHouse.getTenants().size() > 0);
        assertTrue(selectedHouse.getTenants().stream().anyMatch(tenant -> tenant.getName().equals("Leen")));
        // Add additional assertions or verifications based on your application's logic
    }
}
