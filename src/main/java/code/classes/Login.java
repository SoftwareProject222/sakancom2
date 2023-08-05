package code.classes;

import model.classes.ConectionClass;

import java.sql.*;
import java.util.logging.Logger;

public class Login {

    private static Logger logger = Logger.getLogger(Login.class.getName());
    private boolean isLoggedin;
    private int ownerID;
    private String ownerName;
    private int tenantID;
    private String tenantName;

    public int getTenantID() {
        return tenantID;
    }

    public String getTenantName() {
        return tenantName;
    }

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
        ConectionClass c=new ConectionClass();

        String query = "";
        switch (userChoice) {
            case "1":
                query = "SELECT * FROM admin";
                break;
            case "2":
                query = "SELECT * FROM tenant"; break;  case "3":  query = "SELECT * FROM owner"; break;
            default:
                break;
        }
        if(!query.equals("")){
            ResultSet result = c.getStmt().executeQuery(query);

            while (result.next()) {
                if (result.getString("username").equals(username) && result.getString("password").equals(password)) {
                    login();
                    if(userChoice.equals("3")){ ownerID=result.getInt("idowner"); ownerName=result.getString("name");
                    }
                    else if(userChoice.equals("2")){ tenantID=result.getInt("idtenant"); tenantName=result.getString("name");
                    }
                    break;
                }
            }
        }

    }

    public void reasonFalseLogin() {
        logger.info("The username or password is not correct");
    }
}
