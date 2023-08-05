package model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectionClass {
    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="mydatabase@L1";
    private Statement stmt;
    private Connection con;

    public Statement getStmt() {
        return stmt;
    }

    public  Connection getCon() {
        return con;
    }

    public ConectionClass() throws SQLException {
        con = DriverManager.getConnection(url,user,p);
        stmt = con.createStatement();
    }

}
