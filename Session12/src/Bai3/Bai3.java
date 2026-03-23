package Bai3;

// JDBC cần biết trước kiểu dữ liệu mà Database sẽ trả về là gì
//phải đăng ký bằng hằng số: java.sql.Types.DECIMAL hoặc java.sql.Types.NUMERIC

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bai3 {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?CreateDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456#";

    static void main(String[] args) {
        int surgeryId = 505;

        String sql = "{call GET_SURGERY_FEE(?, ?)}";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASS);
            CallableStatement cstmt = conn.prepareCall(sql)) {
            cstmt.setInt(1, surgeryId);

            cstmt.registerOutParameter(2, java.sql.Types.DECIMAL);

            cstmt.execute();

            double totalCost = cstmt.getDouble(2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
