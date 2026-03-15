package BTTH;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool poolA, poolB;
    private int totalSold = 0;
    private static volatile boolean supplierFinished = false;

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.counterName = name;
        this.poolA = a;
        this.poolB = b;
    }

    public static void setSupplierFinished(boolean finished) { supplierFinished = finished; }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (true) {
                // Điều kiện dừng: Hết vé cả 2 phòng và nhà cung cấp đã xong
                if (poolA.getAvailableCount() == 0 && poolB.getAvailableCount() == 0 && supplierFinished) break;

                int mode = rand.nextInt(3); // 0,1: Bán thường | 2: Bán Combo

                if (mode < 2) {
                    sellNormal(rand.nextInt(2) == 0 ? poolA : poolB);
                } else {
                    sellCombo();
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(counterName + " bị gián đoạn.");
        }
    }

    private void sellNormal(TicketPool target) {
        Ticket t = target.sellTicket();
        if (t == null) {
            // Thử phòng còn lại
            target = (target == poolA) ? poolB : poolA;
            t = target.sellTicket();
        }

        if (t != null) {
            totalSold++;
            System.out.println(counterName + " bán vé thường: " + t.getTicketId());
        }
    }

    // PHÒNG CHỐNG DEADLOCK: Luôn khóa poolA trước, poolB sau
    private void sellCombo() {
        synchronized (poolA) {
            synchronized (poolB) {
                if (poolA.getAvailableCount() > 0 && poolB.getAvailableCount() > 0) {
                    Ticket t1 = poolA.sellTicket();
                    Ticket t2 = poolB.sellTicket();
                    totalSold += 2;
                    System.out.println(counterName + " BÁN COMBO: " + t1.getTicketId() + " & " + t2.getTicketId());
                }
            }
        }
    }

    public int getTotalSold() { return totalSold; }
}
