package Cau6;

import java.util.ArrayList;
import java.util.List;

class Ticket {
    String id;
    boolean sold = false;
    Ticket(String id) { this.id = id; }
}

public class TicketPool {
    private String name;
    private List<Ticket> tickets = new ArrayList<>();
    private int soldCount = 0;
    private final int price = 50000;

    public TicketPool(String name, int qty) {
        this.name = name;
        for (int i = 1; i <= qty; i++) tickets.add(new Ticket(name + "-" + i));
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.sold) {
                t.sold = true;
                soldCount++;
                return t;
            }
        }
        return null;
    }

    public String getName() { return name; }
    public int getSoldCount() { return soldCount; }
    public int getTotalTickets() { return tickets.size(); }
    public long getRevenue() { return (long) soldCount * price; }
}
