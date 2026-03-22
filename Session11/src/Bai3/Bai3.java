package Bai3;

import Bai1.DBContext;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Bai3 {
    public void updateBedStatusLegacy(String bedId) {
        Connection conn = null;
        Statement stm = null;

        try {
            conn = DBContext.getHospitalConn();
            stm = conn.createStatement();

            String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = '" + bedId + "'";

            int rowsAffected = stm.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Thông báo: Giường " + bedId + " đã chuyển sang trạng thái 'Đang sử dụng'.");
            } else {
                System.err.println("Cảnh báo: Không tìm thấy giường có mã '" + bedId + "'. Vui lòng kiểm tra lại!");
            }

        } catch (SQLException e) {
            System.err.println("Lỗi hệ thống: Không thể thực thi câu lệnh cập nhật.");
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) stm.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
