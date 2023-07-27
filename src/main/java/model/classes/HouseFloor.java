package model.classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HouseFloor {
    private int houseId;
    private int floorId;
    private int apartId;
    private int noBathrooms;
    private int noBedrooms;
    private String balcony;

    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="memesa32002@";

    public int getIdHouse() {
        return houseId;
    }

    public int getIdFloor() {
        return floorId;
    }

    public int getIdApart() {
        return apartId;
    }

    public int getNoBathrooms() {
        return noBathrooms;
    }

    public int getNoBedrooms() {
        return noBedrooms;
    }

    public String getBalcony() {
        return balcony;
    }

    public HouseFloor(int idHouse, int idFloor, int idApart, int noBathrooms, int noBedrooms, String balcony) {
        this.houseId = idHouse;
        this.floorId = idFloor;
        this.apartId = idApart;
        this.noBathrooms = noBathrooms;
        this.noBedrooms = noBedrooms;
        this.balcony = balcony;
    }
    public static boolean findHouseFloorId(int idhouse){
        boolean ret=false;
        try {
            Connection con1 = DriverManager.getConnection(url,user,p);
            Statement stmt1 = con1.createStatement();
            ResultSet result1 = stmt1.executeQuery("select id_house from house_floor");
            while (result1.next()) {
                if(idhouse==result1.getInt("id_house")){
                    ret= true;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}
