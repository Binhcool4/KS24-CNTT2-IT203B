package BTTH;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        User singleUser = UserManagement.userFactory.get();
        System.out.println("--- Kiểm tra User đơn lẻ ---");
        String testName = "LoiBuiDuc";

        if (IUserAccount.isStandardLength(testName)) {
            System.out.println(testName + " là Username hợp lệ.");
        }

        List<User> users = new ArrayList<>();
        users.add(new User("admin_01", "admin@gmail.com", "ADMIN", "ACTIVE"));
        users.add(new User("loi_bui", "loi@gmail.com", "USER", "ACTIVE"));
        users.add(new User("test_user", "test@gmail.com", "USER", "INACTIVE"));
        users.add(new User("guest_99", "guest@gmail.com", "GUEST", "ACTIVE"));

        System.out.println("\n--- Toàn bộ danh sách User ---");
        users.forEach(System.out::println);

        System.out.println("\n--- Trích xuất Email của User đầu tiên ---");
        String firstEmail = UserManagement.getEmailFunc.apply(users.get(0));
        System.out.println("Email: " + firstEmail);

        System.out.println("\n--- Danh sách User đang ACTIVE ---");
        users.stream()
                .filter(UserManagement.isActive)
                .forEach(System.out::println);
    }
}
