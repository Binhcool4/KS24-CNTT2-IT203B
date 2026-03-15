package BTTH1;

public class EvenRunnable implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("Số chẵn: " + i);
                Thread.sleep(100); // Tạo độ trễ để thấy sự xen kẽ
            }
        } catch (InterruptedException e) {
            System.out.println("Luồng đếm chẵn bị gián đoạn.");
        }
    }
}
