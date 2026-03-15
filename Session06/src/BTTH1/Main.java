package BTTH1;

public class Main {
    public static void main(String[] args) {
        // Tạo các đối tượng thực thi
        EvenRunnable evenTask = new EvenRunnable();
        OddRunnable oddTask = new OddRunnable();

        // Tạo các Thread và truyền nhiệm vụ vào
        Thread thread1 = new Thread(evenTask);
        Thread thread2 = new Thread(thread1); // Oops, truyền nhầm! Phải là oddTask nhé Lợi

        // Sửa lại cho chuẩn:
        Thread t1 = new Thread(evenTask);
        Thread t2 = new Thread(oddTask);

        // Khởi chạy
        t1.start();
        t2.start();

        // Thông báo từ luồng chính
        System.out.println(">>> Thread chính kết thúc <<<");
    }
}
