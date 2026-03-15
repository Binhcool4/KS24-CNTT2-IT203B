package Cau5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TicketPool poolA = new TicketPool("A", 2);
        TicketPool poolB = new TicketPool("B", 2);

        Thread timeout = new Thread(new TimeoutManager(Arrays.asList(poolA, poolB)));
        timeout.setDaemon(true);
        timeout.start();

        new Thread(new BookingCounter("Quầy 1", poolA)).start();
        new Thread(new BookingCounter("Quầy 2", poolA)).start();
        new Thread(new BookingCounter("Quầy 3", poolB)).start();
    }
}