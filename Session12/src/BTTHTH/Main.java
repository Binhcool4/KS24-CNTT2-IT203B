package BTTHTH;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PharmacyManager manager = new PharmacyManager();
        Scanner sc = new Scanner(System.in);

        manager.updateMedicineStock(1, 50);
        manager.findMedicinesByPriceRange(10000, 500000);

        System.out.print("\nNhập ID đơn thuốc cần tính tiền: ");
        int pId = Integer.parseInt(sc.nextLine());
        manager.getPrescriptionTotal(pId);

        System.out.print("Nhập ngày thống kê doanh thu (yyyy-MM-dd): ");
        String date = sc.nextLine();
        manager.getDailyRevenue(date);
    }
}
