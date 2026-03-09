package BTTH;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class UserManagement {
    public static Supplier<User> userFactory = User::new;

    public static Predicate<User> isActive = u -> "ACTIVE".equalsIgnoreCase(u.getStatus());

    public static Function<User, String> getEmailFunc = User::getEmail;
}
