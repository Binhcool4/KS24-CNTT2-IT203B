package Bai4;

import Bai1.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bai4 {
    public void searchPatientSecurely(String inputName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBContext.getHospitalConn();
            stmt = conn.createStatement();

            if (inputName != null) {
                inputName = inputName.replace("'", "''")
                        .replace("--", "")
                        .replace(";", "");
            }

            String sql = "SELECT * FROM Patients WHERE full_name = '" + inputName + "'";
            System.out.println("SQL thực thi: " + sql);

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Bệnh nhân: " + rs.getString("full_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
