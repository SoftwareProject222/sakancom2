package code.classes;
import model.classes.ConectionClass;
import model.classes.House;
import model.classes.HouseFloor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OwnerControlPanel {
    private static Logger logger = Logger.getLogger(OwnerControlPanel.class.getName());
    static House houseObj;
    static HouseFloor houseFloorObj;

    private OwnerControlPanel(){

    }
    public static List<House> findHouse(int houseId) throws SQLException {
        List<House> houseList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select * from house where idhouse='" + houseId + "'");

        while (result.next()) {
            houseObj=new House(result.getInt("idhouse"),result.getString("images"),result.getString("location"),result.getString("services"),result.getDouble("price"),result.getInt("id_owner"),result.getInt("no_floors"));
            houseObj.setNoOfTenant(result.getInt("no_tenant"));
        }
        houseList.add(houseObj);
        return houseList;
    }

    public static void display(House h) throws SQLException {

            logger.info("number of tenant= " + h.getNoOfTenant() + "\n" + "number of floors= " + h.getNoOfFloors() + "\n");
            logger.info("floors of this house:\n");
        ConectionClass c=new ConectionClass();
            ResultSet result = c.getStmt().executeQuery("select DISTINCT id_floor from house_floor where id_house='" + h.getId() + "'");
            while (result.next()) {
                String output =result.getInt("id_floor")+"\n";
                logger.info(output);
            }

    }

    public static void displayNOTenantAndFloors(List<House> house) throws SQLException {
        for (House h : house) {
            display(h);
        }
    }

    public static List<Integer> findFloor(int floorId) throws SQLException {
        List<Integer> apart = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select id_apart from house_floor where  id_house='" + houseObj.getId()+"' and  id_floor='" + floorId + "' ");
        while (result.next()) {
            apart.add(result.getInt("id_apart"));
        }
        return apart;
    }

    public static void displayAparts(List<Integer> apart) {
        logger.info("apartments of this floor: \n");
        for (int i=0;i<apart.size();i++){
            String output=apart.get(i)+"\n";
            logger.info(output);
        }

    }

    public static List<HouseFloor> findApart(int apartmentId) throws SQLException {
        List<HouseFloor> apartInfoList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select * from house_floor where id_house='" + houseObj.getId() + "' and id_apart='"+apartmentId+"'");
        while (result.next()) {
            houseFloorObj=new HouseFloor(result.getInt("id_house"),result.getInt("id_floor"),result.getInt("id_apart"),result.getInt("no_bathrooms"),result.getInt("no_bedrooms"),result.getString("balcony"));
        }
        apartInfoList.add(houseFloorObj);

        return apartInfoList;
    }


    private static void displayApartt(HouseFloor hf) throws SQLException {

        logger.info("number of bathrooms= " + hf.getNoBathrooms() + "\n" + "number of bedrooms= " + hf.getNoBedrooms() + "\n there's a balcony: "+hf.getBalcony()+"\n");
        logger.info("Tenants of this apartment:\n TenantName \t contactInfo\n");
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select name, phone, email from tenant where id_apart='"+hf.getIdApart()+"'");

        while (result.next()) {
            String output=result.getString("name") + "\t 0"+ result.getString("phone")+" , "+result.getString("email")+"\n";
            logger.info(output);

        }

    }
    public static void displayApartInformation(List<HouseFloor> houseFloor) throws SQLException {
        for (HouseFloor hf : houseFloor) {
            displayApartt(hf);
        }
    }


}
