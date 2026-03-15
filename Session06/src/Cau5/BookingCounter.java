package Cau5;

import java.util.Random;

public class BookingCounter implements Runnable {
    private String name;
    private TicketPool pool;

    public BookingCounter(String name, TicketPool pool) {
        this.name = name;
        this.pool = pool;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (true) {
                Ticket t = pool.holdTicket(name);

                if (t != null) {
                    System.out.println(name + ": Đã giữ vé " + t.getTicketId() + ". Chờ thanh toán (5s)...");

                    Thread.sleep(3000);
                    if (rand.nextInt(10) < 6) {
                        if (pool.sellHeldTicket(t)) {
                            System.out.println(name + ": ===> THANH TOÁN THÀNH CÔNG: " + t.getTicketId());
                        }
                    } else {
                        System.out.println(name + ": Khách rời đi. Vé " + t.getTicketId() + " sẽ chờ hết hạn.");
                    }
                } else {
                    synchronized (pool) {
                        System.out.println(name + ": Phòng " + pool.getRoomName() + " hết vé/đang chờ, ngủ 2s...");
                        pool.wait(2000);
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}