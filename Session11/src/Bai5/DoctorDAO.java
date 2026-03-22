package Bai5;

import java.sql.*;
import java.util.*;

public class DoctorDAO {

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty")
                );
                list.add(d);
            }

        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách: " + e.getMessage());
        }

        return list;
    }

    public boolean insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setString(3, doctor.getSpecialty());

            return ps.executeUpdate() > 0;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Lỗi: Trùng mã bác sĩ!");
        } catch (Exception e) {
            System.out.println("Lỗi thêm: " + e.getMessage());
        }

        return false;
    }

    public Map<String, Integer> countBySpecialty() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                map.put(rs.getString("specialty"), rs.getInt("total"));
            }

        } catch (Exception e) {
            System.out.println("Lỗi thống kê: " + e.getMessage());
        }

        return map;
    }
}