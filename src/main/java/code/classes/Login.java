package code.classes;

import java.sql.*;
import java.util.logging.Logger;

public class Login {

    private static Logger logger = Logger.getLogger(Login.class.getName());
    private boolean isLoggedin;
    private int ownerID;
    private String ownerName;

    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="memesa32002@";
    public void setLoggedin(boolean loggedin) {
        isLoggedin = loggedin;
    }

    public Login() {
        setLoggedin(false);
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }



    public boolean isLogIn() {
        return isLoggedin;
    }

    public void login(){
        isLoggedin=true;
    }

    public void logout() {
        this.isLoggedin = false;
    }
    public void logInCheck(String username, String password, String userChoice) throws SQLException {
       /* boolean flag1=false;
        boolean flag2=false;
        String userName="username";
        String password2="password";*/
            Connection con = DriverManager.getConnection(url,user,p);
            Statement stmt=con.createStatement();

        String query = "";
        switch (userChoice) {
            case "1":
                query = "SELECT * FROM admin";
                break;
            case "2":
                query = "SELECT * FROM tenant";
                break;
            case "3":
                query = "SELECT * FROM owner";
                break;
            default:
                break;
        }
        if(!query.equals("")){
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                if (result.getString("username").equals(username) && result.getString("password").equals(password)) {
                    login();
                    if(userChoice.equals("3")){
                        ownerID=result.getInt("idowner");
                        ownerName=result.getString("name");
                    }
                    break;
                }
            }
        }




           /* ResultSet result=stmt.executeQuery("select * from admin");
            while (result.next()){
                if(result.getString(userName).equals(username) && result.getString(password2).equals(password) && userChoice.equals("1"))
                {
                    login();
                    flag1=true;
                    break;
                }
            }

            if(!flag1){
                ResultSet resTenant=stmt.executeQuery("select * from tenant");
                while (resTenant.next()){
                    if(resTenant.getString(userName).equals(username) && resTenant.getString(password2).equals(password) && userChoice.equals("2"))
                    {
                        login();
                        flag2=true;
                        break;
                    }
                }
            }

            if(!flag1 && !flag2){
                ResultSet resOwner=stmt.executeQuery("select * from owner");
                while (resOwner.next()){
                    if(resOwner.getString(userName).equals(username) && resOwner.getString(password2).equals(password) && userChoice.equals("3"))
                    {
                        ownerID=resOwner.getInt("idowner");
                        ownerName=resOwner.getString("name");
                        login();
                        break;
                    }
                }
            }
*/

    }

    public void reasonFalseLogin() {
        logger.info("The username or password is not correct");
    }
}
