package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    public static Connection openConnection(){
        String url = "jdbc:postgresql://localhost:5432/calculator_db";
        String username = "user";
        String pass = "pass";
        try{
            return DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            System.out.println("NE RABOTAIET");
            throw new RuntimeException("EXP");
        }
    }
}
