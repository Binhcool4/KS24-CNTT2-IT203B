package Bai5;

import java.sql.*;
import java.util.*;

public class PatientDAO {
    public List<Patient> findAll() throws SQLException {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM Patients";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Patient(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        }
        return list;
    }

    public void add(String name, int age, String dep) throws SQLException {
        String sql = "INSERT INTO Patients (p_name, p_age, department) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, dep);
            pstmt.executeUpdate();
        }
    }

    public double getDischargeFee(int id) throws SQLException {
        String sql = "{call CALCULATE_DISCHARGE_FEE(?, ?)}";
        try (Connection conn = DBConnection.getConnection();
             CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, Types.DECIMAL);
            cstmt.execute();
            return cstmt.getDouble(2);
        }
    }
}
