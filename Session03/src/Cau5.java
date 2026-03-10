import java.util.List;
import java.util.Comparator;

record User(String username, String email, String status) {}

public class Cau5 {
    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alexander", "alex@gmail.com", "ACTIVE"),
                new User("bob", "bob@yahoo.com", "INACTIVE"),
                new User("charlotte", "charlotte@gmail.com", "ACTIVE"),
                new User("benjamin", "ben@gmail.com", "ACTIVE"),
                new User("tom", "tom@gmail.com", "ACTIVE")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username()));
    }
}