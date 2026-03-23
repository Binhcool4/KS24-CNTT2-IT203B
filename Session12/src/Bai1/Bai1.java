package Bai1;
/* Vì khi dùng PreparedStatement thì câu lệnh SQL đc gửi lên Database để biên dịch khung xương trước
* Database sẽ hiểu đây là một cái "mật khẩu" cực kỳ kỳ quặc có chứa ký tự lạ, chứ nó không coi đó là một phần của lệnh logic */

import java.sql.*;
import java.util.Scanner;

public class Bai1 {
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "123456#";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }

     static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã bác sĩ: ");
        String code = sc.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String pass = sc.nextLine();

        String sql = "SELECT * FROM Doctors WHERE code = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Đăng nhập thành công");
            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
