
package test.classes;

        import io.cucumber.java.en.Given;
        import io.cucumber.java.en.Then;
        import io.cucumber.java.en.When;
        import model.classes.House;
        import model.classes.Tenant;

        import java.awt.*;
        import java.io.IOException;
        import java.net.URI;
        import java.net.URISyntaxException;
        import java.sql.SQLException;
        import java.util.List;
        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertTrue;

        import code.classes.Login;
public class ViewHousingT {
    Login obj;
    List<House> availableHousing;
    House selectedHouse;

    public ViewHousingT() {
        this.obj = new Login();
    }

    // Scenario: View available housing
    @Given("I am a logged-in tenant")
    public void iAmALoggedInTenant() {
        obj.login();

        // Open the web page after the tenant is logged in
        try {
            openWebPage();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private void openWebPage() throws IOException, URISyntaxException {
        Desktop d = Desktop.getDesktop();
        String uri = "http://localhost/sakancom/table.php";
        d.browse(new URI(uri));
    }

    @When("I access the housing section")
    public void iAccessTheHousingSection() {
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

    // Scenario: View housing details as a logged-in tenant
    @When("I select a specific housing")
    public void iSelectASpecificHousing()  throws SQLException {
        // Replace 1 with the actual house ID for testing
        int houseId = 1;
        selectedHouse = House.getHouseDetails(houseId);
    }

    @Then("I should be able to see the prices of the housing")
    public void iShouldBeAbleToSeeThePricesOfTheHousing() {
        assertNotNull(selectedHouse);
        assertTrue(selectedHouse.getPrice() > 0);

        // Display house details
        House.displayHouseDetails(selectedHouse);
    }

    @Then("I should be able to see the location of the housing")
    public void iShouldBeAbleToSeeTheLocationOfTheHousing() {
        assertNotNull(selectedHouse);
        assertNotNull(selectedHouse.getLocation());

        // Display house details
        House.displayHouseDetails(selectedHouse);
    }

    @Then("I should be able to see the services available in the housing")
    public void iShouldBeAbleToSeeTheServicesAvailableInTheHousing() {
        assertNotNull(selectedHouse);
        assertNotNull(selectedHouse.getServices());

        // Display house details
        House.displayHouseDetails(selectedHouse);
    }
}