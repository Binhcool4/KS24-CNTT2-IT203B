@FunctionalInterface
interface Authenticatable {
    // Phương thức trừu tượng
    String getPassword();
    // Default method - kiểm tra mật khẩu có rỗng không
    default boolean isAuthenticated() {
        String password = getPassword();
        return password != null && !password.isEmpty();
    }
    // Static method - mô phỏng mã hóa mật khẩu
    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword.hashCode();
    }
}
class User implements Authenticatable {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
public class Cau3 {
    public static void main(String[] args) {
        User u1 = new User("binh", "123456");
        User u2 = new User("guest", "");
        System.out.println("User1 authenticated: " + u1.isAuthenticated());
        System.out.println("User2 authenticated: " + u2.isAuthenticated());
        String encrypted = Authenticatable.encrypt("123456");
        System.out.println("Encrypted password: " + encrypted);
    }
}
