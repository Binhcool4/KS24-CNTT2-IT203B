package KiemTraDauGio;

import java.util.Scanner;

class ProductFactory {
    public static Product createProduct(int type, Scanner sc) {
        System.out.print("Nhập ID: ");
        String id = sc.nextLine();

        System.out.print("Nhập tên: ");
        String name = sc.nextLine();

        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(sc.nextLine());

        if (type == 1) {
            System.out.print("Nhập trọng lượng: ");
            double weight = Double.parseDouble(sc.nextLine());
            return new PhysicalProduct(id, name, price, weight);
        } else if (type == 2) {
            System.out.print("Nhập dung lượng (MB): ");
            double size = Double.parseDouble(sc.nextLine());
            return new DigitalProduct(id, name, price, size);
        }

        return null;
    }
}
