package code.classes;

import model.classes.ConectionClass;
import model.classes.House;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdvFurniture {
    int tenantID;
    int furnitureID;
    String tenantName;
    String furnitureName;
    String description;
    Double price;
    static AdvFurniture advFurniture;
    public static Logger logger = Logger.getLogger(AdvFurniture.class.getName());

    public AdvFurniture(int tenantID, String tenantName, String furnitureName, String description, Double price) {
        this.tenantID = tenantID;
        this.tenantName = tenantName;
        this.furnitureName = furnitureName;
        this.description = description;
        this.price = price;
    }

    public int getFurnitureID() {
        return furnitureID;
    }

    public int getTenantID() {
        return tenantID;
    }

    public String getTenantName() {
        return tenantName;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public static void addFurniture(AdvFurniture furniture) throws SQLException {
        ConectionClass c=new ConectionClass();
        String insertFurniture="INSERT INTO furniture (`tenant_id`, `tenant_name`, `furniture_name`,`description`,price) VALUES('"+furniture.getTenantID()+"','"+furniture.getTenantName()+"','"+furniture.getFurnitureName()+"','"+furniture.getDescription()+"','"+furniture.getPrice()+"')";
        c.getStmt().executeUpdate(insertFurniture);
        logger.info("Furniture added successfully");
        c.getCon().close();
    }

    public static List<AdvFurniture> seeFurniture() throws SQLException {
        List<AdvFurniture> furnitureList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select idFurniture,tenant_name,furniture_name,description,price from furniture");
        while (result.next()) {
            advFurniture=new AdvFurniture(result.getInt("idFurniture"),result.getString("tenant_name"),result.getString("furniture_name"),result.getString("description"), result.getDouble("price"));
            furnitureList.add(advFurniture);
        }
        return furnitureList;
    }

    public static void displayFurnitures(List<AdvFurniture> furnitures) {
        logger.info("Furniture id\t Tenant name\t Furniture name\t Description\t Price\n ");
        for(AdvFurniture f:furnitures)
        {
            printFernitures(f);
        }
    }

    private static void printFernitures(AdvFurniture f) {
        logger.info(f.getFurnitureID()+"\t\t\t"+f.getTenantName()+"\t\t"+f.getFurnitureName()+"\t"+f.getDescription()+"\t\t"+f.getPrice()+"\n");
    }


}
