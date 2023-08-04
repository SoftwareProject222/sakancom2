package test.classes;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.House;
import model.classes.Tenant;
import java.sql.SQLException;

import static model.classes.House.logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
public class TenantControlPanelT {
    private House house;
    private int ownerId;
    private String residenceOwnerName;
    private String residenceOwnerContactInfo;
    private String rentDueDate;

  /*  @Given("I have booked accommodation")
    public void iHaveBookedAccommodation() throws SQLException {
        // For testing purposes, let's assume the tenant has booked an accommodation
        // and we have the house and tenant objects available
        house = new House(1, "images/1.jpg", "Location A", "Services: Wifi, AC", 1500.0, 101, 2);
        Tenant tenant = new Tenant(house.getId(), 0, 1, "Tenant Name", 1234567890, "tenant@email.com");
        house.addTenant(house.getId(), tenant);
        house.updateNoOfTenant(house.getId(), 1);
    }
*/
    @When("I access the tenant control panel")
    public void iAccessTheTenantControlPanel() {
        ownerId = house.getOwnerId();
        try {
            residenceOwnerName = House.getResidenceOwnerName(ownerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retrieve residence owner's contact information based on the owner ID
        try {
            residenceOwnerContactInfo = House.getResidenceOwnerContactInfo(ownerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retrieve the rent due date based on the house ID
        try {
            rentDueDate = House.getRentDueDate(house.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Then("I should see my personal data")
    public void iShouldSeeMyPersonalData() {
        assertNotNull(Tenant.getName());
        assertEquals("Tenant Name", Tenant.getName());
        // Add more assertions for other tenant data, such as phone and email
        assertEquals(1234567890,Tenant.getPhone());
        assertEquals("tenant@email.com", Tenant.getEmail());


     assertNotNull(Tenant.getName());
        assertEquals("Tenant Name", Tenant.getName());

    }

    @Then("I should see the name of the residence owner")
    public void iShouldSeeTheNameOfTheResidenceOwner() {
        assertNotNull(residenceOwnerName);
        assertEquals("Residence Owner Name", residenceOwnerName);
    }
    @Then("I should see the residence owner's contact information")
    public void iShouldSeeTheResidenceOwnerSContactInformation() {
        assertNotNull(residenceOwnerContactInfo);
        assertEquals("Contact Info", residenceOwnerContactInfo);
    }
    @Then("I should see when the rent is paid (time to pay)")
    public void iShouldSeeWhenTheRentIsPaidTimeToPay() {
        assertNotNull(rentDueDate);
        assertEquals("2023-08-01", rentDueDate);

    }

    @Given("I have not booked any accommodation")
    public void iHaveNotBookedAnyAccommodation() {
        house = null;
    }

    @When("I try to access the tenant control panel")
    public void iTryToAccessTheTenantControlPanel() {
        if (house == null) {
            logger.info("Error: You have not booked any accommodation.");
        }
    }

    @Then("I should receive an error message indicating no booking")
    public void iShouldReceiveAnErrorMessageIndicatingNoBooking() {
        logger.info("Error: You have not booked any accommodation so you can't access the control panel.");
    }
}
