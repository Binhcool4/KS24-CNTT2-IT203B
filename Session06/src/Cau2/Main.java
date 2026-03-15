package Cau2;

import Cau1.BookingCounter;
import Cau1.TicketPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool poolA = new TicketPool("A", 5);
        TicketPool poolB = new TicketPool("B", 5);

        BookingCounter c1 = new BookingCounter("Quầy 1", poolA, poolB);
        BookingCounter c2 = new BookingCounter("Quầy 2", poolA, poolB);
        TicketSupplier supplier = new TicketSupplier(poolA, poolB, 3, 3000, 2);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread tSup = new Thread(supplier);

        t1.start(); t2.start(); tSup.start();

        tSup.join();
        Thread.sleep(2000);

        System.out.println("\nKết thúc chương trình");
        System.out.println("Quầy 1 bán được: " + c1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + c2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + poolA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + poolB.getRemainingCount());

        System.exit(0);
    }
}