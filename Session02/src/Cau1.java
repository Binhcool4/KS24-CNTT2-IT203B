import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Cau1 {
    static class User {
        private String name;
        private int age;
        private String role;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
            this.role = "USER";
        }

        public String getName() {
            return name;
        }
        public int getAge() {
            return age;
        }
        public String getRole() {
            return role;
        }
        @Override
        public String toString() {
            return "User{" + "name=" + name + ", age=" + age + ", role=" + role + '}';
        }
    }

    public static void main(String[] args) {
        // 1. Predicate: Kiểm tra
        Predicate<User> isAdmin = user -> user.getRole().equals("ADMIN");

// 2. Function: Biến đổi (User -> String)
        Function<User, String> getUsername = user -> user.getName();

// 3. Consumer: Thực hiện hành động (In ra)

        Consumer<User> printUser = user -> System.out.println(user.toString());

// 4. Supplier: Cung cấp/Khởi tạo
        Supplier<User> defaultUser = () -> new User("Guest", 18);
    }
}
