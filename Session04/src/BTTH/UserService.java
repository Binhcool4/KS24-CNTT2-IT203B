package BTTH;

import java.util.ArrayList;
import java.util.List;

record User(int id, String username, String email) {}

public class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user == null || user.username() == null || user.username().trim().isEmpty()) {
            throw new IllegalArgumentException("Username không được để trống");
        }
        users.add(user);
    }

    public User findUserById(int id) {
        return users.stream()
                .filter(u -> u.id() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && email.contains("@");
    }

    public int getUserCount() {
        return users.size();
    }
}
