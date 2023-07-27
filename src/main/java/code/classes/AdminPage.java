package code.classes;
import io.cucumber.java.sl.In;
import model.classes.Tenant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdminPage {
    private int idhouse;
    private String acceptance;
    static Tenant tenant;
    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String password="memesa32002@";

    private static Logger logger = Logger.getLogger(AdminPage.class.getName());

    public int getIdhouse() {
        return idhouse;
    }

//    public void setIdhouse(int idhouse) {
//        this.idhouse = idhouse;
//    }

//    public void setAcceptance(String acceptance) {
//        this.acceptance = acceptance;
//    }

    public String getAcceptance() {
        return acceptance;
    }

    public AdminPage(int idhouse, String acceptance) {
        this.idhouse = idhouse;
        this.acceptance = acceptance;
    }

    public static void acceptReject(AdminPage ad) {
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            String updateAccep="UPDATE owner_advertisements SET accept= '"+ad.getAcceptance()+"' WHERE idhouse_adv='"+ad.getIdhouse()+"' ";
            stmt.executeUpdate(updateAccep);
            con.close();

        } catch (Exception e) {
        }
    }


    public static List<Tenant> seeReservations() {
        List<Tenant> tenantList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select id_house,id_apart,idtenant,name,phone,email from tenant");
            while (result.next()) {
                tenant=new Tenant(result.getInt("id_house"),result.getInt("id_apart"),result.getInt("idtenant"), result.getString("name"),result.getInt("phone"),result.getString("email"));
                tenantList.add(tenant);
            }

        } catch (Exception e) {
        }
        return tenantList;

    }

    private static void displayReservation(Tenant t) {
        logger.info(t.getIdHouse()+"\t\t\t"+t.getIdApart()+"\t\t"+t.getIdTenant()+"\t"+t.getName()+"\t\t 0"+t.getPhone()+","+t.getEmail()+"\n");
    }

    public static void displayReservations(List<Tenant> tenant) {
        String tab="\t\tـــ\t\t";
        String tab2="\tـــ";
        logger.info("House id\t Apart id\t Tenant id\t Tenant name\t contact Info of tenant (Phone & Email)\n ");
        for(Tenant t:tenant)
        {
            displayReservation(t);
        }
        try {
            Connection con = DriverManager.getConnection(url,user,password);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("select idhouse from house where no_tenant=0");
            int houseId=result.getInt("idhouse");
            while (result.next()) {
                logger.info(houseId+tab+tab+tab+tab2);
            }

        } catch (Exception e) {
        }

    }




}
