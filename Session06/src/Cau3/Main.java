package Cau3;

import Cau1.BookingCounter;
import Cau1.TicketPool;
import Cau2.TicketSupplier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool poolA = new TicketPool("A", 3);
        TicketPool poolB = new TicketPool("B", 3);

        BookingCounter c1 = new BookingCounter("Quầy 1", poolA, poolB);
        BookingCounter c2 = new BookingCounter("Quầy 2", poolA, poolB);

        TicketSupplier supplier = new TicketSupplier(poolA, poolB, 3, 5000, 2);

        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(supplier).start();
    }
}