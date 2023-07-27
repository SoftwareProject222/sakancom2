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

    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="memesa32002@";

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
    public House(int id, String link, String location, String services, Double price, int ownerId, int noOfFloors, int noOfTenant) {
        this.id = id;
        this.link = link;
        this.location = location;
        this.services = services;
        this.price = price;
        this.ownerId = ownerId;
        this.noOfFloors = noOfFloors;
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

            Connection con1 = DriverManager.getConnection(url,user,p);
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

            Connection con = DriverManager.getConnection(url,user,p);
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
            Connection con = DriverManager.getConnection(url,user,p);
            Statement stmt = con.createStatement();
            String insertHouseInfo="INSERT INTO house_floor VALUES('"+housef.getIdHouse()+"','"+housef.getIdFloor()+"','"+housef.getIdApart()+"','"+housef.getNoBathrooms()+"','"+housef.getNoBedrooms()+"','"+housef.getBalcony()+"')";
            stmt.executeUpdate(insertHouseInfo);
            logger.info("House's Information added successfully");

        } catch (Exception e) {
        }
    }

    public void updateInfo(String attribute, Object value, Integer houseId) throws SQLException {
        Connection con = DriverManager.getConnection(url,user,p);
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
