package code.classes;

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

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerContactInfo(String ownerContactInfo) {
        this.ownerContactInfo = ownerContactInfo;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setRentNote(String rentNote) {
        this.rentNote = rentNote;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static void setValidH(boolean validH) {
        AddAdvertisement.validH = validH;
    }

    public AddAdvertisement() {
    }

    static boolean validH=false;
    private static Logger logger = Logger.getLogger(AddAdvertisement.class.getName());


    public AddAdvertisement(Integer id_house, String photos, String ownerName, String ownerContactInfo, String location, String services, Double rent,String rentNote,  Double price) {
        this.houseId = id_house;
        this.photos = photos;
        this.ownerName = ownerName;
        this.ownerContactInfo = ownerContactInfo;
        this.location = location;
        this.services = services;
        this.rentNote = rentNote;
        this.rent = rent;
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


    public static boolean isDuplicateHouse=false;


    public static boolean isValidHouse() {
        return validH;
    }



    public static void addAdv(AddAdvertisement adv) {
        boolean flag=true;
        boolean flag2=false;
        Connection con1=null;
        try {
            con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakancom", "root", "memesa32002@");
            Statement stmt1 = con1.createStatement();
            ResultSet result1 = stmt1.executeQuery("select idhouse_adv from owner_advertisements");
            while (result1.next()) {
                if(adv.getHouseId()==result1.getInt("idhouse_adv")){
                    flag=false;
                    validH=false;
                    isDuplicateHouse=true;
                    break;
                }
            }
            if(flag){
                ResultSet result2 = stmt1.executeQuery("select idhouse from house");
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
                stmt1.executeUpdate(insertStmt);
                validH=true;
            }

            con1.close();


        }catch (SQLException e){
        }
        finally {
            if (con1 != null) {
                try {
                    con1.close();
                } catch (SQLException e) {
                }
            }
        }

    }

    public void displayReasonSameHouse() {
        logger.info("The advertisement of this house is already exist in the system");
    }

    public void displayReasonHouseNotExist() {
        logger.info("The house you want to advertise it is not exist");
    }
}
