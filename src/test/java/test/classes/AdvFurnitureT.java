package test.classes;
import code.classes.AdvFurniture;
import code.classes.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.classes.House;
import model.classes.Tenant;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdvFurnitureT {
    Login obj;
    AdvFurniture furniture;
    List<AdvFurniture> furnitures;
    int tenantID;
    String tenantName;
    String furnitureName;
    String description;
    Double price;

    public AdvFurnitureT() {
        this.obj=new Login();
    }
    @Given("the tenant is logged in")
    public void the_tenant_is_logged_in() {
        obj.login();
    }

    @Given("tenant enter the furniture_name {string}, description {string}, price {double}")
    public void tenant_enter_the_furniture_name_description_price(String furnitureName, String description, Double price) {
        furniture=new AdvFurniture(obj.getTenantID(),obj.getTenantName(),furnitureName,description,price);
        this.tenantID=obj.getTenantID();
        this.tenantName=obj.getTenantName();
        this.furnitureName=furnitureName;
        this.description=description;
        this.price=price;
    }

    @Then("the advertisement will be added to the database in system")
    public void the_advertisement_will_be_added_to_the_database_in_system() throws SQLException {
        assertNotNull(furniture);

        assertEquals(furniture.getTenantID(),tenantID);
        assertEquals(furniture.getTenantName(),tenantName);
        assertEquals(furniture.getFurnitureName(),furnitureName);
        assertEquals(furniture.getDescription(),description);
        assertEquals(furniture.getPrice(),price);

        AdvFurniture.addFurniture(furniture);

    }

    @Then("other tenants can see it")
    public void other_tenants_can_see_it() throws SQLException {
        furnitures=AdvFurniture.seeFurniture();
        AdvFurniture.displayFurnitures(furnitures);

    }

}
