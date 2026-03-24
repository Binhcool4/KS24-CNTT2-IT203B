package Bai5;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientController {

    public void admitPatient(String name, int age, int bedId, double amount) {

        Connection conn = null;

        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);

            // Kiểm tra giường
            String checkBed = "SELECT status FROM Beds WHERE bed_id = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkBed);
            psCheck.setInt(1, bedId);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new Exception("Giường không tồn tại");
            }

            if (!rs.getString("status").equals("EMPTY")) {
                throw new Exception("Giường đã có người");
            }

            // Thêm bệnh nhân
            String insertPatient = "INSERT INTO Patients(name, age) VALUES(?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(insertPatient, PreparedStatement.RETURN_GENERATED_KEYS);
            ps1.setString(1, name);
            ps1.setInt(2, age);
            ps1.executeUpdate();

            ResultSet generatedKeys = ps1.getGeneratedKeys();
            int patientId = 0;
            if (generatedKeys.next()) {
                patientId = generatedKeys.getInt(1);
            }

            // Cập nhật giường
            String updateBed = "UPDATE Beds SET status = 'OCCUPIED' WHERE bed_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(updateBed);
            ps2.setInt(1, bedId);
            int row2 = ps2.executeUpdate();

            if (row2 == 0) {
                throw new Exception("Không thể cập nhật trạng thái giường");
            }

            // Lưu thanh toán
            String insertPayment = "INSERT INTO Payments(patient_id, amount) VALUES(?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(insertPayment);
            ps3.setInt(1, patientId);
            ps3.setDouble(2, amount);
            ps3.executeUpdate();

            conn.commit();
            System.out.println("Tiếp nhận bệnh nhân thành công");

        } catch (Exception e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
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
