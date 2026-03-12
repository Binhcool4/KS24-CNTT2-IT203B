package ThucHanhDauGio;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager();

        while (true) {

            System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng");
            System.out.println("5. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Lựa chọn của bạn: ");

            int choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Nhập ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();

                        System.out.print("Nhập giá: ");
                        double price = sc.nextDouble();

                        System.out.print("Nhập số lượng: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nhập category: ");
                        String category = sc.nextLine();

                        Product p = new Product(id, name, price, quantity, category);

                        manager.addProduct(p);
                        System.out.println("Thêm sản phẩm thành công!");
                        break;

                    case 2:
                        manager.displayProducts();
                        break;

                    case 3:
                        System.out.print("Dang phat trien");
                        break;

                    case 4:
                        manager.removeOutOfStock();
                        break;

                    case 5:
                        System.out.println("Thoát chương trình.");
                        return;

                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }

            } catch (InvalidProductException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
    }
}
