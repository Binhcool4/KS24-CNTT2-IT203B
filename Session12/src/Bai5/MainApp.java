package Bai5;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        PatientDAO dao = new PatientDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Danh sách | 2. Tiếp nhận | 3. Tính phí | 4. Thoát");
            System.out.print("Chọn: ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> dao.findAll().forEach(System.out::println);
                    case 2 -> {
                        System.out.print("Tên bệnh nhân: ");
                        String name = sc.nextLine();
                        System.out.print("Tuổi: ");
                        int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Khoa điều trị: ");
                        String dep = sc.nextLine();
                        dao.add(name, age, dep);
                        System.out.println("Tiếp nhận thành công");
                    }
                    case 3 -> {
                        System.out.print("Nhập ID bệnh nhân xuất viện: ");
                        int id = Integer.parseInt(sc.nextLine());
                        double fee = dao.getDischargeFee(id);
                        System.out.printf("Tổng viện phí: %,.2f VND\n", fee);
                    }
                    case 4 -> System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
