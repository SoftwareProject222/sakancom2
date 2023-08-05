package model.classes;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Tenant {
    private int idTenant;

    private String name;
    private Integer phone;
    private String email;
    private Integer idApart;

    private Integer idHouse;
    static House house;
    static HouseFloor apart;
    static Tenant student;
    private static Logger logger = Logger.getLogger(Tenant.class.getName());

    private int age;

    private String major;

    private String contact;



    public String getContact() {
        return contact;
    }

    public Tenant(int idHouse, int idApart, int idTenant, String name, int phone, String email ) {
        this.idTenant = idTenant;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idApart = idApart;
        this.idHouse = idHouse;
    }

    public Tenant(int idApart, int age, String major) {
        this.idApart = idApart;
        this.age = age;
        this.major = major;
    }

    public Tenant(String email,String name, String contact, Integer houseId, Integer apartId) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.idHouse = houseId;
        this.idApart = apartId;
    }


    public int getIdTenant() {
        return idTenant;
    }

    public String getName() {
        return name;
    }

    public Integer getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getIdApart() {
        return idApart;
    }

    public int getAge() {
        return age;
    }

    public String getMajor() {
        return major;
    }

    public Integer getIdHouse() {
        return idHouse;
    }

    public static Desktop seeAdvertisements() {
        return Desktop.getDesktop();
    }

    public static void openAdvertisements(Desktop d) throws URISyntaxException, IOException {
        String uri = "http://localhost/sakancom/table_tenent.php";
        d.browse(new URI(uri));
    }

    public static List<House> seeHousing() throws SQLException {
        List<House> houseList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select idhouse,location,services,price,no_floors from house");
        while (result.next()) {
            house=new House(result.getInt("idhouse"),result.getString("location"),result.getString("services"), result.getDouble("price"),result.getInt("no_floors"));
            houseList.add(house);
        }
        return houseList;
    }

    public static void displayHousing(List<House> houses) {
        logger.info("House id\t Location\t Services\t Price\t Number of floors\n ");
        for(House h:houses)
        {
            printHousing(h);
        }
    }

    public static void printHousing(House h) {
        logger.info(h.getId()+"\t\t\t"+h.getLocation()+"\t\t"+h.getServices()+"\t"+h.getPrice()+"JD\t\t"+h.getNoOfFloors()+"\n");
    }

    public static List<HouseFloor> seeAvailableAparts() throws SQLException {
        List<HouseFloor> apartList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("SELECT a.* FROM house_floor a LEFT JOIN tenant t ON a.id_apart = t.id_apart GROUP BY a.id_apart, a.id_house, a.id_floor,a.no_bathrooms,a.no_bedrooms,a.balcony HAVING COUNT(t.idtenant) < 4");
        while (result.next()) {
            apart=new HouseFloor(result.getInt("id_house"),result.getInt("id_floor"),result.getInt("id_apart"), result.getInt("no_bathrooms"),result.getInt("no_bedrooms"),result.getString("balcony"));
            apartList.add(apart);
        }
        return apartList;
    }

    public static void displayAparts(List<HouseFloor> apartments) {
        logger.info("Apartment id\t House id\t Floor id\t Number of bathrooms\t Number of bedrooms\t Has a balcony?\n");
        for(HouseFloor hf:apartments)
        {
            printAparts(hf);
        }
    }

    private static void printAparts(HouseFloor hf) {
        logger.info(hf.getIdApart()+"\t "+hf.getIdHouse()+"\t "+hf.getIdFloor()+"\t"+hf.getNoBathrooms()+"\t"+hf.getNoBedrooms()+"\t"+hf.getBalcony()+"\n");
    }
    public static List<Tenant> seeStudentAparts() throws SQLException {
        List<Tenant> studentList = new ArrayList<>();
        ConectionClass c=new ConectionClass();
        ResultSet result = c.getStmt().executeQuery("select id_apart,age,major from tenant WHERE isStudent='yes'");
        while (result.next()) {
            student=new Tenant(result.getInt("id_apart"),result.getInt("age"),result.getString("major"));
            studentList.add(student);
        }
        return studentList;
    }

    public static void displayStudentAparts(List<Tenant> student) {
        logger.info("In these apartments there is students which their ages and majors as shown:");
        logger.info("Apartment id\t Age\t Major\n");
        for(Tenant s:student)
        {
            printStudent(s);
        }
    }

    private static void printStudent(Tenant s) {
        logger.info(s.getIdApart()+"\t "+s.getAge()+"\t "+s.getMajor()+"\n");
    }

    public static void bookAccommodation(Tenant tenant) throws SQLException {
        ConectionClass c=new ConectionClass();
        String bookHouse="UPDATE tenant SET id_apart='"+tenant.getIdApart()+"', time_to_pay = 'After 3 days from now', id_house = '"+tenant.getIdHouse()+"' WHERE email ='"+tenant.getEmail()+"'";
        c.getStmt().executeUpdate(bookHouse);
        logger.info("Book accommodation has been completed successfully");
        c.getCon().close();
    }


    public static void printControlPanel( Tenant tenant) throws SQLException {

        String tenantData="*** PERSONAL DATA ***";
        String name="Name: "+tenant.getName();
        String contact="Contact info.: "+tenant.getContact();
        String time="Time to pay: After 3 days from now";

        String output=tenantData+"\n"+name+"\n"+contact+"\n"+time;
        logger.info(output);

        String ownerData="*** OWNER INFORMATION ***";
        String ownerName="Name: ";
        String ownerContact="Contact info.: ";

        ConectionClass c2=new ConectionClass();
        ResultSet result2 = c2.getStmt().executeQuery("SELECT name,email,phone FROM owner WHERE idowner = (SELECT id_owner FROM house WHERE idhouse = '"+tenant.getIdHouse()+"')");
        StringBuilder ownerNameBuilder = new StringBuilder();
        StringBuilder ownerContactBuilder = new StringBuilder();
        while (result2.next()) {
            ownerNameBuilder.append(result2.getString("name"));
            ownerContactBuilder.append(result2.getString("email")).append(", ").append("0").append(result2.getInt("phone"));
        }
         ownerName += ownerNameBuilder.toString();
         ownerContact += ownerContactBuilder.toString();

        String output2=ownerData+"\n"+ownerName+"\n"+ownerContact;
        logger.info(output2);
    }

}