package BTTHTH;

import java.sql.*;
import java.util.Scanner;

public class PharmacyManager {
    public void updateMedicineStock(int id, int quantity) {
        String sql = "UPDATE medicines SET stock = stock + ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, quantity);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Đã cập nhật kho thành công!");
            else System.out.println("Không tìm thấy thuốc có ID: " + id);

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void findMedicinesByPriceRange(double min, double max) {
        String sql = "SELECT * FROM medicines WHERE price BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, min);
            pstmt.setDouble(2, max);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d | Tên: %s | Giá: %.2f | Kho: %d\n",
                        rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("stock"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void getPrescriptionTotal(int prescriptionId) {
        String sql = "{call CalculatePrescriptionTotal(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setInt(1, prescriptionId);
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();
            double total = cstmt.getDouble(2);
            System.out.printf("Tổng tiền đơn thuốc #%d: %,.2f VND\n", prescriptionId, total);

        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void getDailyRevenue(String dateStr) {
        String sql = "{call GetDailyRevenue(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setDate(1, Date.valueOf(dateStr));
            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();
            double revenue = cstmt.getDouble(2);
            System.out.printf("Doanh thu ngày %s: %,.2f VND\n", dateStr, revenue);

        } catch (SQLException e) {
            System.err.println("Lỗi: Định dạng ngày không đúng hoặc lỗi DB!");
        }
    }
}
