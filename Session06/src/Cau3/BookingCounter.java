package Cau3;

import Cau1.Ticket;
import Cau1.TicketPool;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;
    private boolean lockAFirst;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB, boolean lockAFirst) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
        this.lockAFirst = lockAFirst;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sellCombo();
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println(counterName + " ngừng hoạt động.");
        }
    }

    private void sellCombo() throws InterruptedException {

        if (lockAFirst) {
            synchronized (roomA) {
                System.out.println(counterName + ": Đã giữ vé phòng A. Đang chờ vé phòng B...");
                Thread.sleep(100);
                synchronized (roomB) {
                    executeSale();
                }
            }
        } else {
            // Thứ tự ngược lại gây Deadlock
            synchronized (roomB) {
                System.out.println(counterName + ": Đã giữ vé phòng B. Đang chờ vé phòng A...");
                Thread.sleep(100);
                synchronized (roomA) {
                    executeSale();
                }
            }
        }
    }

    private void executeSale() {
        Ticket t1 = roomA.sellTicketDirectly();
        Ticket t2 = roomB.sellTicketDirectly();
        if (t1 != null && t2 != null) {
            soldCount += 2;
            System.out.println(counterName + " bán combo thành công: " + t1.getTicketId() + " & " + t2.getTicketId());
        } else {
            System.out.println(counterName + ": Hết vé, bán combo thất bại.");
        }
    }

    public int getSoldCount() { return soldCount; }
}
