package model.classes;

import code.classes.Login;

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

    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String password="memesa32002@";

    private static Logger logger = Logger.getLogger(House.class.getName());

    public House() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public void setNoOfTenant(int noOfTenant) {
        this.noOfTenant = noOfTenant;
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
    public House(int id, String link, String location, String services, Double price, int owner_id, int noof_floors, int noof_tenant) {
        this.id = id;
        this.link = link;
        this.location = location;
        this.services = services;
        this.price = price;
        this.ownerId = owner_id;
        this.noOfFloors = noof_floors;
        this.noOfTenant = noof_tenant;
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

            Connection con1 = DriverManager.getConnection(url,user,password);
            Statement stmt1 = con1.createStatement();
            ResultSet result1 = stmt1.executeQuery("select idhouse from house");
            while (result1.next()) {
                if(idhouse==result1.getInt("idhouse")){
                    ret= true;
                    break;
                }
            }

        return ret;
    }

    public static void addHouse(House house) throws SQLException {

            Connection con = DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            String insertHouse="INSERT INTO house VALUES('"+house.getId()+"','"+house.getLink()+"','"+house.getLocation()+"','"+house.getServices()+"','"+house.getPrice()+"','"+house.getOwnerId()+"','"+house.getNoOfFloors()+"',0)";
            if(!House.findHouseId(house.getId())){
                stmt.executeUpdate(insertHouse);
                logger.info("House added successfully");
            }else {
                logger.info("this id is already exist, please enter another id: ");
            }

            con.close();

    }
    public static void addHouseInfo(HouseFloor housef) {
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            String insertHouseInfo="INSERT INTO house_floor VALUES('"+housef.getIdHouse()+"','"+housef.getIdFloor()+"','"+housef.getIdApart()+"','"+housef.getNoBathrooms()+"','"+housef.getNoBedrooms()+"','"+housef.getBalcony()+"')";
            stmt.executeUpdate(insertHouseInfo);
            logger.info("House's Information added successfully");

        } catch (Exception e) {
        }
    }

    public void updateInfo(String attribute, Object value, Integer houseId) throws SQLException {
//        try {
        Connection con = DriverManager.getConnection(url,user,password);
        Statement stmt = con.createStatement();

        if(House.findHouseId(houseId)){
            if(attribute.equalsIgnoreCase("services")){
                String updateServices="UPDATE house SET services='"+value+"' WHERE idhouse='"+houseId+"'";
                stmt.executeUpdate(updateServices);
            }
            else if(attribute.equalsIgnoreCase("price")){
                String updatePrice="UPDATE house SET price='"+value+"' WHERE idhouse='"+houseId+"'";
                stmt.executeUpdate(updatePrice);
            }
            else if(attribute.equalsIgnoreCase("ownerId")){
                String updateOwnerId="UPDATE owner SET idowner='"+value+"' WHERE idowner=(select id_owner from house where idhouse='"+houseId+"')";
                stmt.executeUpdate(updateOwnerId);
            }
        }
            con.close();

    }
    public void updateMsg() {
        logger.info(" Information Updated Successfully");
    }

    public void unupdatedMsg() {
        logger.info(" Information will not Updated, since houseId doesn't exist ");
    }


}
