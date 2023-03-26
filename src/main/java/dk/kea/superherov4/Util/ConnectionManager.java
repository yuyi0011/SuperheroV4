package dk.kea.superherov4.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection conn=null;

    public static Connection getConnectoin(String db_url, String uid, String pwd){
        if (conn !=null) return conn;

        try {
            conn= DriverManager.getConnection(db_url, uid, pwd);

        } catch (SQLException e){
            System.out.println("Coundn't connect to db");
            e.printStackTrace();
        }return conn;
    }
}
