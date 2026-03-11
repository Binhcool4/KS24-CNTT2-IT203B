import java.time.LocalDate;
import java.util.List;

record UserProfile(String email, LocalDate dob) {}
record User1(String id, UserProfile profile) {}

public class Cau6 {
    public User1 updateProfile(User1 existingUser, UserProfile newProfile, List<User1> allUsers) {
        if (newProfile.dob().isAfter(LocalDate.now())) {
            return null;
        }

        boolean emailExists = allUsers.stream()
                .anyMatch(u -> !u.id().equals(existingUser.id()) &&
                        u.profile().email().equalsIgnoreCase(newProfile.email()));

        if (emailExists) {
            return null;
        }

        return new User1(existingUser.id(), newProfile);
    }
}