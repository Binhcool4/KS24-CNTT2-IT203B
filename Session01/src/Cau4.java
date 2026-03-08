import java.io.IOException;

public class Cau4 {
    public static void main(String[] args) {
        System.out.println("Hàm A (Main): Bắt đầu chương trình.");

        try {
            processUserData();
            System.out.println("Hàm A: Lưu dữ liệu thành công!");
        } catch (IOException e) {
            System.out.println("Hàm A (Catch): Đã tóm được lỗi từ hàm C truyền qua hàm B.");
            System.out.println("Chi tiết lỗi: " + e.getMessage());
        }

        System.out.println("Hàm A: Chương trình kết thúc êm đẹp, không bị crash.");
    }

    public static void processUserData() throws IOException {
        System.out.println("Hàm B: Đang xử lý dữ liệu người dùng...");
        saveToFile();
    }

    public static void saveToFile() throws IOException {
        System.out.println("Hàm C: Đang cố gắng ghi vào file cứng...");

        boolean errorHappened = true;

        if (errorHappened) {
            throw new IOException("Lỗi: Không tìm thấy ổ đĩa hoặc file bị từ chối truy cập!");
        }
    }
}
