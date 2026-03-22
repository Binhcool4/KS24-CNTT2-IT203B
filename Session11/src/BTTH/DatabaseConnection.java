package BTTH;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/MedicalAppointmentDB";
    private static final String USER = "root";
    private static final String PASS = "123456#";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            return conn;
        } catch (Exception e) {
            System.err.println("Lỗi kết nối: " + e.getMessage());
            return null;
        }
    }
}
