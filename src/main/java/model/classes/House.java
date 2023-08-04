package model.classes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
public class House {
    private int id;
    private String link;
    private String location;
    private String services;
    private Double price;
    private int ownerId;
    private int noOfFloors;
    private int noOfTenant;

    public static Logger logger = Logger.getLogger(House.class.getName());
    public House() {
    }
    public House(int id, String link, String location, String services, Double price, int ownerId, int noOfFloors) {
        this.id = id;
        this.link = link;
        this.location = location;
        this.services = services;
        this.price = price;
        this.ownerId = ownerId;
        this.noOfFloors = noOfFloors;
    }

    public static List<House> getAvailableHousing() throws SQLException {
        List<House> availableHousing = new ArrayList<>();
        ConectionClass c = new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("SELECT * FROM house WHERE no_tenant < 4 AND no_tenant < 5");

        while (result.next()) {
            House house = new House(
                    result.getInt("idhouse"),
                    result.getString("images"),
                    result.getString("location"),
                    result.getString("services"),
                    result.getDouble("price"),
                    result.getInt("id_owner"),
                    result.getInt("no_floors")
            );

            availableHousing.add(house);
        }

        c.getCon().close();
        return availableHousing;
    }


    // Method to get house details by house ID
    public static House getHouseDetails(int houseId) throws SQLException {
        ConectionClass c = new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("SELECT price, location, services FROM house WHERE idhouse = " + houseId);

        House house = null;
        if (result.next()) {
            house = new House(
                    houseId,
                    null, // Since we are not retrieving images, set it to null
                    result.getString("location"),
                    result.getString("services"),
                    result.getDouble("price"),
                    0, // Since we are not retrieving owner ID, set it to 0
                    0 // Since we are not retrieving number of floors, set it to 0
            );
        }

        c.getCon().close();
        return house;
    }


    // Method to display house details
    // Method to display house details
    public static void displayHouseDetails(House house) {
        if (house != null) {
            String tab = "\t\tـــ\t\t";
            String output = house.getId() + tab + house.getLocation() + tab + house.getServices() + tab + "\tـــ" + house.getPrice();
            logger.info(output);
        } else {
            logger.info("House with the provided ID not found.");
        }
    }


    // Method to display available houses
    public static void displayAvailableHousing(List<House> availableHousing) {
        logger.info("House id\t Location\t Services\t Price\n ");
        for (House house : availableHousing) {
            displayHouseDetails(house);
        }
    }



    public void setNoOfTenant(int noOfTenant) {
        this.noOfTenant = noOfTenant;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getServices() {
        return services;
    }

    public Double getPrice() {
        return price;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public int getNoOfTenant() {
        return noOfTenant;
    }





    public static boolean findHouseId(int idhouse) throws SQLException {
        boolean ret=false;
        ConectionClass c=new ConectionClass();
        ResultSet result1 = c.getStmt().executeQuery("select idhouse from house");
        while (result1.next()) {
            if(idhouse==result1.getInt("idhouse")){
                ret= true;
                break;
            }
        }
        return ret;
    }

    public static void addHouse(House house) throws SQLException {

            ConectionClass c=new ConectionClass();
            String insertHouse="INSERT INTO house VALUES('"+house.getId()+"','"+house.getLink()+"','"+house.getLocation()+"','"+house.getServices()+"','"+house.getPrice()+"','"+house.getOwnerId()+"','"+house.getNoOfFloors()+"',0)";
            if(!House.findHouseId(house.getId())){
                c.getStmt().executeUpdate(insertHouse);
                logger.info("House added successfully");
            }else {
                logger.info("this id is already exist, please enter another id ");
            }

        c.getCon().close();
    }
    public static void addHouseInfo(HouseFloor housef) throws SQLException {

        ConectionClass c=new ConectionClass();
        String insertHouseInfo="INSERT INTO house_floor VALUES('"+housef.getIdHouse()+"','"+housef.getIdFloor()+"','"+housef.getIdApart()+"','"+housef.getNoBathrooms()+"','"+housef.getNoBedrooms()+"','"+housef.getBalcony()+"')";
        c.getStmt().executeUpdate(insertHouseInfo);
        logger.info("House's Information added successfully");
    }

    public void updateInfo(String attribute, Object value, Integer houseId) throws SQLException {
        ConectionClass c=new ConectionClass();

        if(House.findHouseId(houseId)){
            if(attribute.equalsIgnoreCase("services")){
                String updateServices="UPDATE house SET services='"+value+"' WHERE idhouse='"+houseId+"'";
                c.getStmt().executeUpdate(updateServices);
            }
            else if(attribute.equalsIgnoreCase("price")){
                String updatePrice="UPDATE house SET price='"+value+"' WHERE idhouse='"+houseId+"'";
                c.getStmt().executeUpdate(updatePrice);
            }
            else if(attribute.equalsIgnoreCase("ownerId")){
                String updateOwnerId="UPDATE owner SET idowner='"+value+"' WHERE idowner=(select id_owner from house where idhouse='"+houseId+"')";
                c.getStmt().executeUpdate(updateOwnerId);
            }
        }
        c.getCon().close();
    }
    public static void updateMsg() {
        logger.info(" Information Updated Successfully");
    }

    public static void unupdatedMsg() {
        logger.info(" Information will not Updated, since houseId doesn't exist ");
    }
    public static void SuccessMsg() {
        logger.info("  ");
    }
    public static void failMsg() {
        logger.info("  ");
    }


    // Method to add a tenant to the house's tenant list
    public static void addTenant(int houseId, Tenant tenant) throws SQLException {
        ConectionClass c = new ConectionClass();
        String insertTenant = "INSERT INTO tenant (id_house, id_apart, idtenant, name, phone, email) VALUES " +
                "(" + houseId + ", 0, " + tenant.getIdTenant() + ", '" + tenant.getName() + "', " +
                tenant.getPhone() + ", '" + tenant.getEmail() + "')";
        c.getStmt().executeUpdate(insertTenant);
        c.getCon().close();
    }

    // Method to update the number of tenants in the house
    public static void updateNoOfTenant(int houseId, int noOfTenant) throws SQLException {
        ConectionClass c = new ConectionClass();
        String updateNoOfTenant = "UPDATE house SET no_tenant = " + noOfTenant + " WHERE idhouse = " + houseId;
        c.getStmt().executeUpdate(updateNoOfTenant);
        c.getCon().close();
    }
   //// for control panel for tenant
    public static String getResidenceOwnerName(int ownerId) throws SQLException {
        ConectionClass c = new ConectionClass();
        String ownerName = null;
        try {
            String query = "SELECT name FROM owner WHERE idowner = " + ownerId;
            ResultSet result = c.getStmt().executeQuery(query);
            if (result.next()) {
                ownerName = result.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.getCon().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ownerName;
    }
    public static String getResidenceOwnerContactInfo(int ownerId) throws SQLException {
        ConectionClass c = new ConectionClass();
        String contactInfo = null;
        try {
            String query = "SELECT contact_info FROM owner WHERE idowner = " + ownerId;
            ResultSet result = c.getStmt().executeQuery(query);
            if (result.next()) {
                contactInfo = result.getString("contact_info");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.getCon().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contactInfo;
    }

    public static String getRentDueDate(int houseId) throws SQLException {
        ConectionClass c = new ConectionClass();
        String rentDueDate = null;
        try {
            String query = "SELECT rent_due_date FROM house WHERE idhouse = " + houseId;
            ResultSet result = c.getStmt().executeQuery(query);
            if (result.next()) {
                rentDueDate = result.getString("rent_due_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                c.getCon().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rentDueDate;
    }


}
