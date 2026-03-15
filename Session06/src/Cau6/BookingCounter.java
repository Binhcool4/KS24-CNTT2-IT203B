package Cau6;

import java.util.List;

public class BookingCounter implements Runnable {
    private String name;
    private List<TicketPool> pools;
    private static volatile boolean paused = false; // Biến dùng chung để tạm dừng
    private int count = 0;

    public BookingCounter(String name, List<TicketPool> pools) {
        this.name = name;
        this.pools = pools;
    }

    public static void setPaused(boolean p) { paused = p; }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (paused) {
                    Thread.sleep(500); // Ngủ đông khi tạm dừng
                    continue;
                }

                TicketPool pool = pools.get(new java.util.Random().nextInt(pools.size()));
                Ticket t = pool.sellTicket();
                if (t != null) {
                    count++;
                    System.out.println(name + " đã bán vé: " + t.id);
                    Thread.sleep(1000); // Tốc độ bán
                } else {
                    Thread.sleep(1000); // Hết vé thì đợi
                }
            }
        } catch (InterruptedException e) {
            System.out.println(name + " đã dừng.");
        }
    }
}