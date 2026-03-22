package BTTH;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    public void addAppointment(Appointment app) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES ('"
                + app.getPatientName() + "', '" + app.getAppointmentDate() + "', '"
                + app.getDoctorName() + "', '" + app.getStatus() + "')";
        executeNonQuery(sql, "Thêm mới thành công!");
    }

    public void updateAppointment(Appointment app) {
        String sql = "UPDATE appointments SET patient_name='" + app.getPatientName()
                + "', appointment_date='" + app.getAppointmentDate()
                + "', doctor_name='" + app.getDoctorName()
                + "', status='" + app.getStatus() + "' WHERE id=" + app.getId();
        executeNonQuery(sql, "Cập nhật thành công!");
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id = " + id;
        executeNonQuery(sql, "Xóa thành công!");
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getString("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private void executeNonQuery(String sql, String message) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            int rows = stmt.executeUpdate(sql);
            if (rows > 0) System.out.println(message);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
