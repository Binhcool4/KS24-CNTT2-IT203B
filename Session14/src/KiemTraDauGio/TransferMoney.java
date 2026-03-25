package KiemTraDauGio;

import java.sql.*;

public class TransferMoney {
    private static final String URL = "jdbc:mysql://localhost:3306/session14?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456#";

    public static void main(String[] args) {

        String senderId = "ACC01";
        String receiverId = "ACC02";
        double amount = 1000;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Tắt auto commit
            conn.setAutoCommit(false);

            // 1. Kiểm tra số dư tài khoản gửi
            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            try (PreparedStatement psCheck = conn.prepareStatement(checkSql)) {

                psCheck.setString(1, senderId);
                ResultSet rs = psCheck.executeQuery();

                if (!rs.next()) {
                    throw new SQLException("Tài khoản gửi không tồn tại!");
                }

                double balance = rs.getDouble("Balance");

                if (balance < amount) {
                    throw new SQLException("Không đủ số dư!");
                }
            }

            // 2. Gọi Stored Procedure
            String callSql = "{call sp_UpdateBalance(?, ?)}";

            try (CallableStatement cs = conn.prepareCall(callSql)) {

                // Trừ tiền người gửi
                cs.setString(1, senderId);
                cs.setDouble(2, -amount);
                cs.execute();

                // Cộng tiền người nhận
                cs.setString(1, receiverId);
                cs.setDouble(2, amount);
                cs.execute();
            }

            // 3. Commit
            conn.commit();
            System.out.println("Chuyển khoản thành công!");

            // 4. Hiển thị kết quả
            String resultSql = "SELECT * FROM Accounts WHERE AccountId IN (?, ?)";

            try (PreparedStatement psResult = conn.prepareStatement(resultSql)) {

                psResult.setString(1, senderId);
                psResult.setString(2, receiverId);

                ResultSet rs = psResult.executeQuery();

                System.out.println("\n=== KẾT QUẢ SAU CHUYỂN ===");
                while (rs.next()) {
                    System.out.println(
                            rs.getString("AccountId") + " | " +
                                    rs.getString("FullName") + " | " +
                                    rs.getDouble("Balance")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Có lỗi xảy ra");
        }
    }
}
