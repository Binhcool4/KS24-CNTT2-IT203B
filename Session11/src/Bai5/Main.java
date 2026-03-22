package Bai5;

import Bai5.Doctor;
import Bai5.DoctorService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();

        while (true) {
            System.out.println("\n===== RIKKEI CARE =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    List<Doctor> list = service.getAll();
                    System.out.printf("%-10s %-20s %-20s\n", "ID", "Name", "Specialty");
                    for (Doctor d : list) {
                        System.out.printf("%-10s %-20s %-20s\n",
                                d.getId(), d.getName(), d.getSpecialty());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Nhập ID: ");
                        String id = sc.nextLine();

                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập chuyên khoa: ");
                        String sp = sc.nextLine();

                        Doctor d = new Doctor(id, name, sp);

                        if (service.addDoctor(d)) {
                            System.out.println("Thêm thành công!");
                        } else {
                            System.out.println("Thêm thất bại!");
                        }

                    } catch (Exception e) {
                        System.out.println("Lỗi: " + e.getMessage());
                    }
                    break;

                case 3:
                    Map<String, Integer> map = service.stats();
                    for (String key : map.keySet()) {
                        System.out.println(key + ": " + map.get(key));
                    }
                    break;

                case 4:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Chọn sai!");
            }
        }
    }
}
