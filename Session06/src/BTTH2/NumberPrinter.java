package BTTH2;


public class NumberPrinter {
    private int count = 1;
    private final int MAX = 10;
    private boolean isOddTurn = true; // Cờ hiệu: true là lượt lẻ, false là lượt chẵn

    public synchronized void printOdd() {
        while (count <= MAX) {
            // Nếu không phải lượt lẻ, hãy đi ngủ (wait)
            while (!isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Kiểm tra lại điều kiện dừng sau khi tỉnh dậy
            if (count <= MAX) {
                System.out.println("Thread lẻ: " + count);
                count++;
                isOddTurn = false; // Đổi lượt sang chẵn
                notify(); // Đánh thức Thread chẵn
            }
        }
    }

    public synchronized void printEven() {
        while (count <= MAX) {
            // Nếu không phải lượt chẵn, hãy đi ngủ (wait)
            while (isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Kiểm tra lại điều kiện dừng sau khi tỉnh dậy
            if (count <= MAX) {
                System.out.println("Thread chẵn: " + count);
                count++;
                isOddTurn = true; // Đổi lượt sang lẻ
                notify(); // Đánh thức Thread lẻ
            }
        }
    }
}
