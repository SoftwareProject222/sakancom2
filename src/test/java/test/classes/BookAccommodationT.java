package test.classes;

import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.classes.HouseFloor;
import model.classes.Tenant;

import java.sql.SQLException;
import java.util.List;

public class BookAccommodationT {
    Login obj;
    List<HouseFloor> apartments;
    List<Tenant> student;
    public BookAccommodationT() {
        this.obj=new Login();
    }
    @Given("the tenant is logged in the system")
    public void the_tenant_is_logged_in_the_system() {
        obj.login();
    }

    @When("he want to book house, the available apartments will printed")
    public void he_want_to_book_house_the_available_apartments_will_printed() throws SQLException {
        apartments= Tenant.seeAvailableAparts();
        Tenant.displayAparts(apartments);
        student=Tenant.seeStudentAparts();
        Tenant.displayStudentAparts(student);
    }

    @When("he enter his email {string}, name {string}, contactInfo {string}, id_house {int}, id_apart {int}")
    public void he_enter_his_email_name_contact_info_id_house_id_apart(String string, String string2, String string3, Integer int1, Integer int2) {

    }

    @Then("the reservation process has been completed successfully")
    public void the_reservation_process_has_been_completed_successfully() {

    }

    @Then("a control panel show information about the reservation process will appear")
    public void a_control_panel_show_information_about_the_reservation_process_will_appear() {

    }
}
