package BTTH;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentRepository repo = new AppointmentRepository();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- QUẢN LÝ LỊCH KHÁM ---");
            System.out.println("1. Danh sách | 2. Thêm mới | 3. Sửa | 4. Xóa | 5. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> repo.getAllAppointments().forEach(System.out::println);
                case 2 -> {
                    System.out.print("Tên BN: "); String name = sc.nextLine();
                    System.out.print("Ngày (yyyy-mm-dd): "); String date = sc.nextLine();
                    System.out.print("Bác sĩ: "); String doc = sc.nextLine();
                    repo.addAppointment(new Appointment(0, name, date, doc, "Pending"));
                }
                case 3 -> {
                    System.out.print("Nhập ID cần sửa: "); int id = Integer.parseInt(sc.nextLine());
                    System.out.print("Tên mới: "); String name = sc.nextLine();
                    System.out.print("Ngày mới: "); String date = sc.nextLine();
                    System.out.print("Bác sĩ mới: "); String doc = sc.nextLine();
                    System.out.print("Trạng thái mới: "); String status = sc.nextLine();
                    repo.updateAppointment(new Appointment(id, name, date, doc, status));
                }
                case 4 -> {
                    System.out.print("Nhập ID cần xóa: ");
                    int id = Integer.parseInt(sc.nextLine());
                    repo.deleteAppointment(id);
                }
                case 5 -> System.exit(0);
            }
        }
    }
}
