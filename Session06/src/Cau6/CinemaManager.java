package Cau6;

import java.util.*;
import java.util.concurrent.*;

public class CinemaManager {
    private List<TicketPool> pools = new ArrayList<>();
    private ExecutorService executor;
    private List<Future<?>> futures = new ArrayList<>();

    public void startSimulation(int numRooms, int numTickets, int numCounters) {
        pools.clear();
        for (int i = 0; i < numRooms; i++) {
            pools.add(new TicketPool("Phòng " + (char)('A' + i), numTickets));
        }

        executor = Executors.newFixedThreadPool(numCounters + 1);
        executor.execute(new DeadlockDetector()); // Chạy detector ngầm

        for (int i = 1; i <= numCounters; i++) {
            executor.execute(new BookingCounter("Quầy " + i, pools));
        }
        System.out.println("Đã khởi tạo hệ thống với " + numRooms + " phòng, " + (numRooms * numTickets) + " vé, " + numCounters + " quầy.");
    }

    public void pause() { BookingCounter.setPaused(true); System.out.println("Đã tạm dừng mô phỏng."); }
    public void resume() { BookingCounter.setPaused(false); System.out.println("Đã tiếp tục mô phỏng."); }

    public void showStats() {
        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");
        long totalRevenue = 0;
        for (TicketPool p : pools) {
            System.out.println(p.getName() + ": Đã bán " + p.getSoldCount() + "/" + p.getTotalTickets());
            totalRevenue += p.getRevenue();
        }
        System.out.println("Tổng doanh thu: " + totalRevenue + " VNĐ");
    }

    public void stop() {
        if (executor != null) executor.shutdownNow();
        System.out.println("Đang dừng hệ thống...");
    }
}
