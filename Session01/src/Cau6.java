import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cau6 {
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    static class User {
        private String name;
        private int age;

        public void displayName() {
            if (this.name != null && !this.name.isEmpty()) {
                System.out.println("Tên người dùng: " + this.name.toUpperCase());
            } else {
                System.out.println("[WARNING] Tên chưa được thiết lập hoặc đang để trống.");
            }
        }

        public void setAge(int age) throws InvalidAgeException {
            if (age < 0 || age > 120) {
                throw new InvalidAgeException("Tuổi " + age + " nằm ngoài phạm vi cho phép (0-120).");
            }
            this.age = age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static void logError(String message) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            System.err.println("[ERROR] " + timestamp + " - " + message);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = new User();

        try {
            System.out.println("=== HỆ THỐNG QUẢN LÝ NGƯỜI DÙNG V2.0 ===");

            System.out.print("Nhập tên: ");
            user.setName(sc.nextLine());

            System.out.print("Nhập năm sinh: ");
            String inputYear = sc.nextLine();

            int birthYear = Integer.parseInt(inputYear);
            int currentAge = 2026 - birthYear;

            user.setAge(currentAge);

            user.displayName();

            System.out.println("Đăng ký thành công!");

        } catch (NumberFormatException e) {
            User.logError("Dữ liệu nhập vào không phải là số: " + e.getMessage());
        } catch (InvalidAgeException e) {
            User.logError("Vi phạm quy tắc nghiệp vụ: " + e.getMessage());
        } catch (Exception e) {
            User.logError("Lỗi hệ thống không xác định: " + e.toString());
        } finally {
            System.out.println("Giải phóng tài nguyên hệ thống...");
            sc.close();
        }
    }
}
