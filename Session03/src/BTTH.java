import java.time.LocalDate;
import java.util.List;
import java.time.Period;
public class User {

    private String id;
    private String email;
    private String password;
    private boolean verified;
    private LocalDate createdAt;

    public User(String id, String email, String password, boolean verified, LocalDate createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.verified = verified;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVerified() {
        return verified;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", createdAt=" + createdAt +
                '}';
    }
}
record PublicUser(String id, String email, Tier tier) {}
abstract class Tier {

    protected String name;

    public Tier(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
class Gold extends Tier {

    public Gold() {
        super("Gold");
    }
}
class Silver extends Tier {

    public Silver() {
        super("Silver");
    }
}
class Bronze extends Tier {

    public Bronze() {
        super("Bronze");
    }
}
class UserService {

    // Lọc user đã xác thực
    public List<User> getVerifiedUsers(List<User> users) {
        return users.stream()
                .filter(User::isVerified)
                .toList();
    }

    // Phân loại tier
    public Tier classifyTier(long months) {

        return switch ((int) months / 12) {
            case 2,3,4,5,6 -> new Gold();
            case 1 -> new Silver();
            default -> new Bronze();
        };
    }
}


public class BTTH {

    public static void main(String[] args) {

        UserService service = new UserService();

        // Bước 1: tạo danh sách user
        List<User> users = List.of(
                new User("U01", "a@gmail.com", "123", true, LocalDate.of(2020, 1, 10)),
                new User("U02", "b@gmail.com", "123", false, LocalDate.of(2024, 1, 10)),
                new User("U03", "c@gmail.com", "123", true, LocalDate.of(2023, 5, 10)),
                new User("U04", "d@gmail.com", "123", true, LocalDate.of(2025, 1, 10)),
                new User("U05", "e@gmail.com", "123", false, LocalDate.of(2022, 3, 10))
        );

        // Bước 2: lọc user verified
        List<User> verifiedUsers = service.getVerifiedUsers(users);

        // Bước 3: chuyển sang PublicUser
        List<PublicUser> publicUsers = verifiedUsers.stream()
                .map(user -> {

                    long months = Period.between(
                            user.getCreatedAt(),
                            LocalDate.now()
                    ).toTotalMonths();

                    Tier tier = service.classifyTier(months);

                    return new PublicUser(
                            user.getId(),
                            user.getEmail(),
                            tier
                    );

                })
                .toList();

        // Bước 4: in báo cáo
        System.out.println("""
                
                ===== USER REPORT =====
                
                """);

        publicUsers.forEach(u -> {

            String report = """
                    ID      : %s
                    Email   : %s
                    Tier    : %s
                    ------------------------
                    """.formatted(
                    u.id(),
                    u.email(),
                    u.tier()
            );

            System.out.println(report);

        });

    }
}