import java.util.*;
import java.util.function.*;
class User {
    private String username;
    public User() {
        this.username = "default_user";
    }
    public User(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}

public class Cau4 {
    public static void main(String[] args) {
        // Danh sách users
        List<User> users = new ArrayList<>();
        users.add(new User("binh"));
        users.add(new User("admin"));
        users.add(new User("guest"));
        // 1. (user) -> user.getUsername()
        Function<User, String> getName = User::getUsername;
        // 2. (s) -> System.out.println(s)
        Consumer<String> printer = System.out::println;
        // 3. () -> new User()
        Supplier<User> createUser = User::new;
        // Lấy username từ danh sách user
        for (User u : users) {
            String name = getName.apply(u);
            printer.accept(name);
        }
        // Tạo user mới
        User newUser = createUser.get();
        printer.accept("New user: " + newUser.getUsername());
    }
}
