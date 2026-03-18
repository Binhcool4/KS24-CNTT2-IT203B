package KiemTraDauGio;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n ---------------------- QUẢN LÝ SẢN PHẨM ----------------------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Chọn loại sản phẩm (1: Physical, 2: Digital): ");
                    int type = Integer.parseInt(sc.nextLine());

                    Product p = ProductFactory.createProduct(type, sc);
                    if (p != null) {
                        db.addProduct(p);
                        System.out.println("Thêm thành công!");
                    }
                    break;

                case 2:
                    List<Product> list = db.getAllProducts();
                    if (list.isEmpty()) {
                        System.out.println("Danh sách trống!");
                    } else {
                        for (Product pr : list) {
                            pr.displayInfo();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập ID cần cập nhật: ");
                    String updateId = sc.nextLine();
                    Product updateP = db.findById(updateId);

                    if (updateP != null) {
                        System.out.print("Tên mới: ");
                        updateP.setName(sc.nextLine());

                        System.out.print("Giá mới: ");
                        updateP.setPrice(Double.parseDouble(sc.nextLine()));

                        if (updateP instanceof PhysicalProduct) {
                            System.out.print("Weight mới: ");
                            ((PhysicalProduct) updateP)
                                    .setWeight(Double.parseDouble(sc.nextLine()));
                        } else if (updateP instanceof DigitalProduct) {
                            System.out.print("Size mới: ");
                            ((DigitalProduct) updateP)
                                    .setSize(Double.parseDouble(sc.nextLine()));
                        }

                        System.out.println("Cập nhật thành công!");
                    } else {
                        System.out.println("Không tìm thấy!");
                    }
                    break;

                case 4:
                    System.out.print("Nhập ID cần xoá: ");

                    System.out.println("Đã xoá!");
                    break;

                case 5:
                    System.out.println("Thoát chương trình!");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
