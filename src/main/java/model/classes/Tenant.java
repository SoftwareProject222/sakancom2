package model.classes;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static model.classes.House.logger;

public class Tenant {
    private int idTenant;

    private static String name;
    private static int phone;
    private static String email;
    private int idApart;

    private int idHouse;


    private int age;


    private String universityMajor;
    private List<String> advertisedFurniture;


    public Tenant(int idTenant, int idApart, int idtenant, String name, int phone, String email, int age, String universityMajor) {
        this.idTenant = idTenant;
        this.idApart = 0;
        this.idHouse = 0;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.age = age;
        this.universityMajor = universityMajor;
        this.advertisedFurniture = new ArrayList<>();
    }



    public int getIdTenant() {
        return idTenant;
    }

    public static String getName() {
        return name;
    }

    public static int getPhone() {
        return phone;
    }

    public static String getEmail() {
        return email;
    }

    public int getIdApart() {
        return idApart;
    }


    public int getIdHouse() {
        return idHouse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUniversityMajor() {
        return universityMajor;
    }

    public void setUniversityMajor(String universityMajor) {
        this.universityMajor = universityMajor;
    }

    public static Tenant getTenantDetails(int houseId, int tenantId) throws SQLException {
        ConectionClass c = new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("SELECT * FROM tenant WHERE id_house = " + houseId + " AND idtenant = " + tenantId);

        Tenant tenant = null;
        if (result.next()) {
            tenant = new Tenant(
                    result.getInt("idtenant"),
                    result.getInt("id_apart"), result.getInt("idtenant"), result.getString("name"),
                    result.getInt("phone"),
                    result.getString("email"),
                    result.getInt("age"), // Assuming 'age' is a column in the 'tenant' table
                    result.getString("university_major") // Assuming 'university_major' is a column in the 'tenant' table
            );
        }

        c.getCon().close();
        return tenant;
    }

   public int advertiseFurniture(String furnitureName, String description, double price) throws SQLException {
        ConectionClass connectionClass = new ConectionClass();
        Connection connection = connectionClass.getCon();
        Statement stmt = null;
        int furnitureId = -1;

        try {
            stmt = connection.createStatement();
            String insertFurnitureQuery = "INSERT INTO furniture (tenant_id, furniture_name, description, price) " +
                    "VALUES (" + idTenant + ", '" + furnitureName + "', '" + description + "', " + price + ")";
            stmt.executeUpdate(insertFurnitureQuery, Statement.RETURN_GENERATED_KEYS);

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                furnitureId = generatedKeys.getInt(1);
            }
        } finally {
            // Close the resources properly
            if (stmt != null) {
                stmt.close();
            }
            connection.close();
        }

        return furnitureId;
    }
}