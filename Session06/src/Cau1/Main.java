package Cau1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TicketPool poolA = new TicketPool("A", 10);
        TicketPool poolB = new TicketPool("B", 10);

        BookingCounter counter1 = new BookingCounter("Quầy 1", poolA, poolB);
        BookingCounter counter2 = new BookingCounter("Quầy 2", poolA, poolB);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nKết thúc chương trình");
        System.out.println("Quầy 1 bán được: " + counter1.getSoldCount() + " vé");
        System.out.println("Quầy 2 bán được: " + counter2.getSoldCount() + " vé");
        System.out.println("Vé còn lại phòng A: " + poolA.getRemainingCount());
        System.out.println("Vé còn lại phòng B: " + poolB.getRemainingCount());
    }
}
