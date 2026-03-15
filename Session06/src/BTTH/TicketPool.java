package BTTH;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 0;

    public TicketPool(String roomName, int initialQty) {
        this.roomName = roomName;
        addTickets(initialQty);
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            ticketCounter++;
            String id = roomName + "-" + String.format("%03d", ticketCounter);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println("\n[Nhà cung cấp]: Đã thêm " + count + " vé vào " + roomName);
        this.notifyAll(); // Đánh thức các quầy đang đợi vé
    }

    public synchronized Ticket sellTicket() {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public synchronized int getAvailableCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }

    public String getRoomName() { return roomName; }
}
