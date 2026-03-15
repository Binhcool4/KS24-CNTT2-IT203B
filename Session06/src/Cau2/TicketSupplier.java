package Cau2;

import Cau1.TicketPool;

public class TicketSupplier implements Runnable {
    private TicketPool roomA;
    private TicketPool roomB;
    private int supplyCount;
    private int interval;
    private int rounds;

    public TicketSupplier(TicketPool roomA, TicketPool roomB, int supplyCount, int interval, int rounds) {
        this.roomA = roomA;
        this.roomB = roomB;
        this.supplyCount = supplyCount;
        this.interval = interval;
        this.rounds = rounds;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= rounds; i++) {
                Thread.sleep(interval);
                roomA.addTickets(supplyCount);
                roomB.addTickets(supplyCount);
                System.out.println("\nHệ thống: Đã nạp thêm vé mới!");
            }
        } catch (InterruptedException e) { e.printStackTrace(); }
    }
}