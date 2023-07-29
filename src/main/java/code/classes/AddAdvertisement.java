package code.classes;

import model.classes.ConectionClass;

import java.sql.*;
import java.util.logging.Logger;

public class AddAdvertisement {
    Integer houseId;
    String photos;
    String ownerName;
    String ownerContactInfo;
    String location;
    String services;
    String rentNote;
    Double rent;
    Double price;

    static boolean validH=false;
    private static Logger logger = Logger.getLogger(AddAdvertisement.class.getName());


    public AddAdvertisement(Integer idHouse, String photos, String ownerName, String ownerContactInfo, String location, String services, Double rent) {
        this.houseId = idHouse;
        this.photos = photos;
        this.ownerName = ownerName;
        this.ownerContactInfo = ownerContactInfo;
        this.location = location;
        this.services = services;
        this.rent = rent;
    }

    public void setRentNote(String rentNote) {
        this.rentNote = rentNote;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public String getPhotos() {
        return photos;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerContactInfo() {
        return ownerContactInfo;
    }

    public String getLocation() {
        return location;
    }

    public String getServices() {
        return services;
    }

    public String getRentNote() {
        return rentNote;
    }

    public Double getRent() {
        return rent;
    }

    public Double getPrice() {
        return price;
    }
    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="memesa32002@";

    private static boolean isDuplicateHouse=false;

    public static boolean getIsDuplicateHouse() {
        return isDuplicateHouse;
    }

    public static boolean isValidHouse() {
        return validH;
    }



    public static void addAdv(AddAdvertisement adv) throws SQLException {
        boolean flag=true;
        boolean flag2=false;
        ConectionClass c=new ConectionClass();
        ResultSet result1 = c.getStmt1().executeQuery("select idhouse_adv from owner_advertisements");
        while (result1.next()) {
            if(adv.getHouseId()==result1.getInt("idhouse_adv")){
                flag=false;
                validH=false;
                isDuplicateHouse=true;
                break;
            }
        }
        if(flag){
            ResultSet result2 = c.getStmt1().executeQuery("select idhouse from house");
            while (result2.next()) {
                if(adv.getHouseId()==result2.getInt("idhouse")){
                    flag2=true;
                    isDuplicateHouse=false;
                    break;
                }
            }
        }

        if(flag && flag2){
            String insertStmt = "insert into owner_advertisements values('" + adv.getHouseId() + "','" + adv.getPhotos() + "','" + adv.getOwnerName() + "','" + adv.getOwnerContactInfo() +
                    "',  '" + adv.getLocation() +"','" + adv.getServices()+"'," + adv.getRent() + ",'" + adv.getRentNote() + "' ,'" + adv.getPrice() + "','no','')";
            c.getStmt1().executeUpdate(insertStmt);
            validH=true;
        }

        c.getCon1().close();


    }

    public void displayReasonSameHouse() {
        logger.info("The advertisement of this house is already exist in the system");
    }

    public void displayReasonHouseNotExist() {
        logger.info("The house you want to advertise it is not exist");
    }
}
