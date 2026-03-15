package Cau1;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String counterName;
    private TicketPool roomA;
    private TicketPool roomB;
    private int soldCount = 0;

    public BookingCounter(String counterName, TicketPool roomA, TicketPool roomB) {
        this.counterName = counterName;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (true) {
                TicketPool selectedPool = (random.nextInt(2) == 0) ? roomA : roomB;

                Ticket t = selectedPool.sellTicket(counterName);

                if (t != null) {
                    soldCount++;
                    System.out.println(counterName + " đã bán vé " + t.getTicketId());
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(counterName + " ngừng hoạt động.");
        }
    }

    public int getSoldCount() { return soldCount; }
}