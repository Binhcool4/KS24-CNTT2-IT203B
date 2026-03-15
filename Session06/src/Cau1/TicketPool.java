package Cau1;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {
    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int ticketCounter = 0;

    public TicketPool(String roomName, int quantity) {
        this.roomName = roomName;
        addTickets(quantity);
    }

    // Khi thêm vé xong, gọi notifyAll() để đánh thức các quầy đang chờ
    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            ticketCounter++;
            String id = roomName + "-" + String.format("%03d", ticketCounter);
            tickets.add(new Ticket(id, roomName));
        }
        System.out.println("\n[Hệ thống]: Đã thêm " + count + " vé vào phòng " + roomName);

        // Đánh thức tất cả các quầy đang đợi vé của phòng này
        this.notifyAll();
    }

    // Nếu hết vé, Thread gọi hàm này sẽ rơi vào trạng thái chờ (wait)
    public synchronized Ticket sellTicket(String counterName) throws InterruptedException {
        while (getRemainingCount() == 0) {
            System.out.println(counterName + ": Hết vé phòng " + roomName + ", đang chờ...");
            this.wait(); // Ngủ tại đây cho đến khi có notifyAll()
        }

        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }

    public synchronized int getRemainingCount() {
        int count = 0;
        for (Ticket t : tickets) {
            if (!t.isSold()) count++;
        }
        return count;
    }

    public synchronized Ticket sellTicketDirectly() {
        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }
        return null;
    }
}