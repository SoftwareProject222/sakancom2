package model.classes;
import java.sql.*;
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

    private static Logger logger = Logger.getLogger(House.class.getName());
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
    public void updateMsg() {
        logger.info(" Information Updated Successfully");
    }

    public void unupdatedMsg() {
        logger.info(" Information will not Updated, since houseId doesn't exist ");
    }


}
