package Cau6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CinemaManager manager = new CinemaManager();

        while (true) {
            System.out.println("\n--- MENU RẠP CHIẾU PHIM ---");
            System.out.println("1. Bắt đầu mô phỏng\n2. Tạm dừng\n3. Tiếp tục\n4. Thống kê\n5. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Số phòng: "); int r = sc.nextInt();
                    System.out.print("Số vé/phòng: "); int v = sc.nextInt();
                    System.out.print("Số quầy: "); int q = sc.nextInt();
                    manager.startSimulation(r, v, q);
                    break;
                case 2: manager.pause(); break;
                case 3: manager.resume(); break;
                case 4: manager.showStats(); break;
                case 5: manager.stop(); System.exit(0);
            }
        }
    }
}
