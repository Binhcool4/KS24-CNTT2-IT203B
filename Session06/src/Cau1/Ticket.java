package Cau1;

public class Ticket {
    private String ticketId;
    private String roomName;
    private boolean isSold = false;

    public Ticket(String ticketId, String roomName) {
        this.ticketId = ticketId;
        this.roomName = roomName;
    }

    public boolean isSold() { return isSold; }
    public void setSold(boolean sold) { isSold = sold; }
    public String getTicketId() { return ticketId; }
}
