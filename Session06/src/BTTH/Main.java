package BTTH;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool poolA = new TicketPool("Room A", 10);
        TicketPool poolB = new TicketPool("Room B", 10);

        BookingCounter q1 = new BookingCounter("Quầy 1", poolA, poolB);
        BookingCounter q2 = new BookingCounter("Quầy 2", poolA, poolB);
        BookingCounter q3 = new BookingCounter("Quầy 3", poolA, poolB);
        TicketSupplier supplier = new TicketSupplier(poolA, poolB);

        Thread t1 = new Thread(q1);
        Thread t2 = new Thread(q2);
        Thread t3 = new Thread(q3);
        Thread ts = new Thread(supplier);

        ts.start();
        t1.start(); t2.start(); t3.start();

        t1.join(); t2.join(); t3.join();

        System.out.println("\n--- TỔNG KẾT ---");
        System.out.println("Quầy 1 bán: " + q1.getTotalSold());
        System.out.println("Quầy 2 bán: " + q2.getTotalSold());
        System.out.println("Quầy 3 bán: " + q3.getTotalSold());
        System.out.println("Vé còn lại A: " + poolA.getAvailableCount());
        System.out.println("Vé còn lại B: " + poolB.getAvailableCount());
    }
}
