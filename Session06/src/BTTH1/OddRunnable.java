package BTTH1;

public class OddRunnable implements Runnable {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 9; i += 2) {
                System.out.println("Số lẻ: " + i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Luồng đếm lẻ bị gián đoạn.");
        }
    }
}
