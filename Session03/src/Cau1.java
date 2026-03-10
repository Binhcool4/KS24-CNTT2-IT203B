import java.util.List;

public class Cau1 {

    // Tạo record User
    record User(String username, String email, String status) {}

    public static void main(String[] args) {

        // Tạo 3 user
        List<User> users = List.of(
                new User("alice", "alice@gmail.com", "ACTIVE"),
                new User("bob", "bob@gmail.com", "INACTIVE"),
                new User("charlie", "charlie@gmail.com", "ACTIVE")
        );

        // In danh sách user
        users.forEach(user ->
                System.out.println(user.username() + " - " + user.status())
        );
    }
}