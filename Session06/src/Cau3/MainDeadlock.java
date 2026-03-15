package Cau3;

import Cau1.TicketPool;

public class MainDeadlock {
    public static void main(String[] args) {
        TicketPool poolA = new TicketPool("A", 10);
        TicketPool poolB = new TicketPool("B", 10);

//        BookingCounter q1 = new BookingCounter("Quầy 1", poolA, poolB, true);
//        BookingCounter q2 = new BookingCounter("Quầy 2", poolA, poolB, false);


        BookingCounter q1 = new BookingCounter("Quầy 1", poolA, poolB, true);
        BookingCounter q2 = new BookingCounter("Quầy 2", poolA, poolB, true);


        Thread t1 = new Thread(q1);
        Thread t2 = new Thread(q2);

        System.out.println("--- BẮT ĐẦU CHẠY HỆ THỐNG BÁN COMBO ---");
        t1.start();
        t2.start();
    }
}