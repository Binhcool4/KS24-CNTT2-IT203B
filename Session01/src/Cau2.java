import java.util.Scanner;

public class Cau2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tổng số người dùng: ");
        int tongNguoi = sc.nextInt();

        System.out.print("Nhập số nhóm muốn chia: ");
        int soNhom = sc.nextInt();

        try {
            int moiNhom = tongNguoi / soNhom;
            System.out.println("Mỗi nhóm có: " + moiNhom + " người");

        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");
        }

        System.out.println("Chương trình vẫn tiếp tục chạy...");

        sc.close();
    }
}