package BTTH;

public class TicketSupplier implements Runnable {
    private TicketPool poolA, poolB;

    public TicketSupplier(TicketPool a, TicketPool b) {
        this.poolA = a;
        this.poolB = b;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 2; i++) {
                Thread.sleep(5000); // Cách nhau 5 giây
                poolA.addTickets(5);
                poolB.addTickets(5);
            }
            BookingCounter.setSupplierFinished(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
