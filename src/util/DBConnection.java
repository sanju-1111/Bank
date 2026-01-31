package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/simple_bank?useSSL=false&serverTimezone=UTC",
                "root",
                "Sanju@2005"
            );

        } catch (Exception e) {
            System.out.println("❌ Database connection failed");
            e.printStackTrace();
            return null;
        }
    }
}
