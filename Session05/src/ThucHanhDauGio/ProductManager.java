package ThucHanhDauGio;

import java.util.*;

public class ProductManager {

    private ArrayList<Product> products = new ArrayList<>();

    // CREATE
    public void addProduct(Product product) throws InvalidProductException {

        boolean exists = products.stream()
                .anyMatch(p -> p.getId() == product.getId());

        if (exists) {
            throw new InvalidProductException("ID đã tồn tại!");
        }

        products.add(product);
    }

    // READ
    public void displayProducts() {

        if (products.isEmpty()) {
            System.out.println("Danh sách trống");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-10s %-15s\n",
                "ID", "Name", "Price", "Quantity", "Category");

        products.forEach(p ->
                System.out.printf("%-5d %-15s %-10.2f %-10d %-15s\n",
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getQuantity(),
                        p.getCategory())
        );
    }


    // DELETE
    public void removeOutOfStock() {

        products.removeIf(p -> p.getQuantity() == 0);

        System.out.println("Đã xóa các sản phẩm hết hàng.");
    }
}
