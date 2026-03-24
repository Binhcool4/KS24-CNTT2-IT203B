package Bai5;

import java.util.Scanner;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);
    private final PatientController controller = new PatientController();

    public void start() {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem giường trống");
            System.out.println("2. Tiếp nhận bệnh nhân");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewBeds();
                    break;
                case 2:
                    admitPatient();
                    break;
                case 3:
                    System.out.println("Thoát chương trình...");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
            }
        }
    }

    private void viewBeds() {
        System.out.println("Chức năng xem giường trống (có thể làm thêm nếu cần)");
    }

    private void admitPatient() {
        try {
            System.out.print("Nhập tên bệnh nhân: ");
            String name = scanner.nextLine();

            System.out.print("Nhập tuổi: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập mã giường: ");
            int bedId = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số tiền tạm ứng: ");
            double amount = Double.parseDouble(scanner.nextLine());

            if (amount <= 0) {
                System.out.println("Số tiền phải lớn hơn 0");
                return;
            }

            controller.admitPatient(name, age, bedId, amount);

        } catch (Exception e) {
            System.out.println("Dữ liệu nhập không hợp lệ, vui lòng nhập lại");
        }
    }
}
