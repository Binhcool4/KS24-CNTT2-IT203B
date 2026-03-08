import java.util.Scanner;

public class Cau1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try{
            System.out.println("Nhập năm sinh: ");
            int namsinh = Integer.parseInt(sc.nextLine());

            int tuoi = 2026 - namsinh;
            System.out.println("Tuổi của bạn là: " + tuoi);
        }catch (NumberFormatException ex){
            System.out.println("Lỗi: Bạn phải nhập năm sinh bằng số!");
        }finally {
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
            sc.close();
        }
    }
}
