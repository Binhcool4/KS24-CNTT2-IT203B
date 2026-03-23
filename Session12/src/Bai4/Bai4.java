package Bai4;

// vì mỗi câu lệnh insert có giá trị chuỗi khác nhau nên Database coi chúng như 1000 câu lệnh khác nhau nên khi chạy phần mềm bị chậm

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bai4 {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?CreateDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456#";
    private static String[] testResults;

    static void main(String[] args) {
        String sql = "INSERT INTO Results (data) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return;

            long startTime = System.currentTimeMillis();

            for (String data : testResults) {
                pstmt.setString(1, data);

                pstmt.executeUpdate();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("Đã nạp xong kết quả.");
            System.out.println("⏱Thời gian thực hiện: " + (endTime - startTime) + " ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
