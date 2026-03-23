package Bai2;

// Chuyển đổi kiểu dữ liệu
// đảm bảo tính nhất quán

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Bai2 {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456#";

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.print("Nhập ID bệnh nhân: ");
            int patientId = Integer.parseInt(sc.nextLine());

            double temp = 37.5;

            String sql = "UPDATE Vitals SET temperature = ? WHERE p_id = ?";
            try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
                PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setDouble(1, temp);
                ps.setInt(2, patientId);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Cập nhật chỉ số sinh tồn thành công cho bệnh nhân ID: " + patientId);
                } else {
                    System.out.println("Không tìm thấy bệnh nhân có ID: " + patientId);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
