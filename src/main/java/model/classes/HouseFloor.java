package model.classes;
import java.sql.*;

public class HouseFloor {
    private int houseId;
    private int floorId;
    private int apartId;
    private int noBathrooms;
    private int noBedrooms;
    private String balcony;


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


    public static boolean findHouseFloorId(int idhouse) throws SQLException {
        boolean ret = false;

        ConectionClass c=new ConectionClass();
        ResultSet result1 = c.getStmt().executeQuery("select id_house from house_floor");
        while (result1.next()) {
            if (idhouse == result1.getInt("id_house")) {
                ret = true;
                break;
            }
        }

        return ret;

    }
}