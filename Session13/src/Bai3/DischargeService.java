package Bai3;

import java.sql.*;

public class DischargeService {

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            // ===== BƯỚC 1: LẤY SỐ DƯ =====
            String sqlGetBalance = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            ps = conn.prepareStatement(sqlGetBalance);
            ps.setInt(1, maBenhNhan);
            rs = ps.executeQuery();

            if (!rs.next()) {
                throw new Exception("Bệnh nhân không tồn tại");
            }

            double balance = rs.getDouble("balance");

            // ===== BẪY 1: KIỂM TRA THIẾU TIỀN =====
            if (balance < tienVienPhi) {
                throw new Exception("Không đủ tiền");
            }

            // ===== BƯỚC 2: TRỪ TIỀN =====
            String sqlUpdateWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            ps = conn.prepareStatement(sqlUpdateWallet);
            ps.setDouble(1, tienVienPhi);
            ps.setInt(2, maBenhNhan);

            int row1 = ps.executeUpdate();

            // ===== BẪY 2: ROW = 0 =====
            if (row1 == 0) {
                throw new Exception("Không cập nhật được ví");
            }

            // ===== BƯỚC 3: GIẢI PHÓNG GIƯỜNG =====
            String sqlUpdateBed = "UPDATE Beds SET status = 'EMPTY' WHERE patient_id = ?";
            ps = conn.prepareStatement(sqlUpdateBed);
            ps.setInt(1, maBenhNhan);

            int row2 = ps.executeUpdate();

            // ===== BẪY 2 =====
            if (row2 == 0) {
                throw new Exception("Không cập nhật được giường");
            }

            // ===== BƯỚC 4: CẬP NHẬT BỆNH NHÂN =====
            String sqlUpdatePatient = "UPDATE Patients SET status = 'DISCHARGED' WHERE patient_id = ?";
            ps = conn.prepareStatement(sqlUpdatePatient);
            ps.setInt(1, maBenhNhan);

            int row3 = ps.executeUpdate();

            // ===== BẪY 2 =====
            if (row3 == 0) {
                throw new Exception("Không cập nhật được bệnh nhân");
            }

            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công");

        } catch (Exception e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Thất bại: " + e.getMessage());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
