package test.classes;

import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.House;
import model.classes.HouseFloor;
import model.classes.Tenant;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class ViewHousingTenantT {

    Login obj;
    Desktop d;
    List<House> houses;
    List<HouseFloor> apartments;
    List<Tenant> student;

    public ViewHousingTenantT() {
        this.obj=new Login();
    }

    @Given("the tenant logged in")
    public void the_tenant_logged_in() {
        obj.login();
    }

    @When("he select to see the advertisements of houses")
    public void he_select_to_see_the_advertisements_of_houses() {
       d= Tenant.seeAdvertisements();
    }

    @Then("a page will appear to see them")
    public void a_page_will_appear_to_see_them() throws URISyntaxException, IOException {
        Tenant.openAdvertisements(d);
    }

    @When("he select to see the houses in the system")
    public void he_select_to_see_the_houses_in_the_system() throws SQLException {
        houses=Tenant.seeHousing();
    }

    @Then("a table of houses information will printed")
    public void a_table_of_houses_information_will_printed() {
        Tenant.displayHousing(houses);
    }

    @When("he select to see the available apartments in the system")
    public void he_select_to_see_the_available_apartments_in_the_system() throws SQLException {
        apartments=Tenant.seeAvailableAparts();
    }

    @Then("a table of apartments information will printed")
    public void a_table_of_apartments_information_will_printed() {
        Tenant.displayAparts(apartments);
    }

    @When("he select to see a student apartments")
    public void he_select_to_see_a_student_apartments() throws SQLException {
        student=Tenant.seeStudentAparts();
    }

    @Then("a table of apartments, age and major will printed")
    public void a_table_of_apartments_age_and_major_will_printed() {
        Tenant.displayStudentAparts(student);
    }

}
