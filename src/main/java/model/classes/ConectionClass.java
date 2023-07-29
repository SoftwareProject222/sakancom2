package model.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConectionClass {
    static String url="jdbc:mysql://localhost:3306/sakancom";
    static String user="root";
    static String p="memesa32002@";
    private Statement stmt1;
    private Connection con1;

    public Statement getStmt1() {
        return stmt1;
    }

    public  Connection getCon1() {
        return con1;
    }

    public ConectionClass() throws SQLException {
        con1  = DriverManager.getConnection(url,user,p);
        stmt1 = con1.createStatement();
    }




}
