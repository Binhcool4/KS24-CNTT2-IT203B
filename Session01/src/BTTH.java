import java.io.FileNotFoundException;
import java.util.*;

public class BTTH {
    static class InvalidAgeException extends Exception {
        public InvalidAgeException(String message) {
            super(message);
        }
    }

    static class InvalidEmailException extends Exception {
        public InvalidEmailException(String message) {
            super(message);
        }
    }

    static class RegistrationService {

        // Phương thức đăng ký: Chuyển đổi dữ liệu và kiểm tra nghiệp vụ
        public void registerUser(String name, String ageInput, String email)
                throws InvalidAgeException, InvalidEmailException {

            int age;
            try {
                // Chuyển đổi String -> int (Có thể gây NumberFormatException - Unchecked)
                age = Integer.parseInt(ageInput);
            } catch (NumberFormatException e) {
                // Ném lại một thông báo dễ hiểu hơn
                throw new NumberFormatException("Tuổi phải là một con số nguyên hợp lệ!");
            }

            // Kiểm tra nghiệp vụ tuổi
            if (age < 18) {
                throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi (" + age + ") không đủ để đăng ký hệ thống.");
            }

            // Kiểm tra nghiệp vụ Email
            if (!email.contains("@")) {
                throw new InvalidEmailException("Lỗi nghiệp vụ: Email không hợp lệ (thiếu ký tự '@').");
            }

            System.out.println(">>> Kiểm tra thông tin cho " + name + " thành công!");
        }

        // Phương thức giả lập lưu file (Checked Exception)
        public void saveUserToFile(String userData) throws FileNotFoundException {
            System.out.println("Đang kết nối tới hệ thống lưu trữ...");

            // Giả lập lỗi không tìm thấy file
            boolean isFileSystemReady = false;
            if (!isFileSystemReady) {
                throw new FileNotFoundException("Hệ thống lỗi: Không tìm thấy file lưu trữ database.txt!");
            }

            System.out.println("Đã lưu dữ liệu: " + userData);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationService service = new RegistrationService();

        try {
            System.out.println("=== HỆ THỐNG ĐĂNG KÝ NGƯỜI DÙNG AN TOÀN ===");

            System.out.print("Nhập tên: ");
            String name = sc.nextLine();

            System.out.print("Nhập tuổi: ");
            String ageInput = sc.nextLine();

            System.out.print("Nhập email: ");
            String email = sc.nextLine();

            // Gọi các phương thức có nguy cơ ném Exception
            service.registerUser(name, ageInput, email);
            service.saveUserToFile("User: " + name + " | Age: " + ageInput);

            System.out.println("CHÚC MỪNG! BẠN ĐÃ ĐĂNG KÝ THÀNH CÔNG.");

        } catch (NumberFormatException e) {
            System.out.println(">>> THÔNG BÁO: " + e.getMessage());
        } catch (InvalidAgeException e) {
            System.out.println(">>> THÔNG BÁO: " + e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println(">>> THÔNG BÁO: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(">>> LỖI HỆ THỐNG: " + e.getMessage());
        } finally {
            // Luôn thực thi dù có lỗi hay không
            System.out.println("------------------------------------");
            System.out.println("Hoàn tất luồng xử lý đăng ký.");
            if (sc != null) {
                sc.close();
                System.out.println("Đã đóng Scanner an toàn.");
            }
        }
    }
}
