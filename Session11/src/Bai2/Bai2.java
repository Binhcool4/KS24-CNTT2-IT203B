package Bai2;

import Bai1.DBContext;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bai2 {
    public void printPharmacyCatalogue() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBContext.getHospitalConn();
            stmt = (Statement) conn.createStatement();

            String sql = "SELECT edicine_name, stock FROM Pharmacy";
            rs = ((java.sql.Statement) stmt).executeQuery(sql);

            System.out.println("--- DANH MỤC THUỐC TRONG KHO ---");
            System.out.printf("| %-20s | %-10s |\n", "Tên Thuốc", "Số Lượng");
            System.out.println("------------------------------------------");

            int count = 0;
            while (rs.next()) {
                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");
                System.out.printf("| %-20s | %-10d |\n", name, stock);
                count++;
            }

            if (count == 0) {
                System.out.println("Thông báo: Hiện tại không có thuốc nào trong kho!");
            } else {
                System.out.println("------------------------------------------");
                System.out.println("Tổng cộng: " + count + " loại thuốc.");
            }
        } catch (SQLException e) {
            System.err.println("Lỗi truy xuất danh mục thuốc!");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) ((java.sql.Statement) stmt).close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Bai2 bai2 = new Bai2();
        bai2.printPharmacyCatalogue();
    }
}
