package Bai5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Medical_Care?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456#"; // Pass của Lợi

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Lỗi kết nối DB: " + e.getMessage());
            return null;
        }
    }
}
