package Cau5;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketPool(String roomName, int quantity) {
        this.roomName = roomName;
        for (int i = 1; i <= quantity; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", i)));
        }
    }

    public synchronized Ticket holdTicket(String counterName) {
        for (Ticket t : tickets) {
            if (t.isAvailable()) {
                t.setHeld(true);
                t.setHoldExpiryTime(System.currentTimeMillis() + 5000);
                return t;
            }
        }
        return null;
    }

    public synchronized boolean sellHeldTicket(Ticket t) {
        if (t != null && t.isHeld() && !t.isSold()) {
            t.setSold(true);
            t.setHeld(false);
            return true;
        }
        return false;
    }

    public synchronized void releaseExpiredTickets() {
        long now = System.currentTimeMillis();
        for (Ticket t : tickets) {
            if (t.isHeld() && now > t.getHoldExpiryTime()) {
                t.setHeld(false);
                t.setHoldExpiryTime(0);
                System.out.println("\n[TimeoutManager]: Vé " + t.getTicketId() + " hết hạn giữ, đã trả lại kho.");
                this.notifyAll();
            }
        }
    }

    public String getRoomName() { return roomName; }
}