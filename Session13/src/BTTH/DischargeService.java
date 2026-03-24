package BTTH;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DischargeService {

    public void dischargePatient(int patientId, double amount) {

        Connection conn = null;

        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            // 1. Tạo hóa đơn
            String insertInvoice = "INSERT INTO Invoices(patient_id, amount, created_date) VALUES(?, ?, NOW())";
            PreparedStatement ps1 = conn.prepareStatement(insertInvoice);
            ps1.setInt(1, patientId);
            ps1.setDouble(2, amount);
            ps1.executeUpdate();

            // 2. Cập nhật bệnh nhân
            String updatePatient = "UPDATE Patients SET status = 'Đã xuất viện' WHERE patient_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(updatePatient);
            ps2.setInt(1, patientId);
            int row2 = ps2.executeUpdate();

            if (row2 == 0) {
                throw new Exception("Không tìm thấy bệnh nhân");
            }

            // 3. Giải phóng giường
            String updateBed = "UPDATE Beds SET status = 'Trống', patient_id = NULL WHERE patient_id = ?";
            PreparedStatement ps3 = conn.prepareStatement(updateBed);
            ps3.setInt(1, patientId);
            int row3 = ps3.executeUpdate();

            if (row3 == 0) {
                throw new Exception("Không tìm thấy giường");
            }

            conn.commit();
            System.out.println("Xuất viện thành công");

        } catch (Exception e) {

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Thất bại: " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
